package com.invoke.testcases;

import org.testng.Assert;
import java.util.Map;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.ProjectEditorPage;
import com.invoke.pages.ProjectListPage;
import com.invoke.pages.SessionEditorPage;
import com.invoke.pages.UserDashboardPage;

public class CreateSessionTest extends BaseTest {
	
	@Test(priority = 6, groups = {"verify"})
	public void verifySessionEditor() {
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		log.info("Navigate to first project listed in the table");
		//Navigate to first project in the session table on user dashboard
		ProjectEditorPage projectEditorPage = userDashboardPage.clickProjectLinkInTable();
		projectEditorPage.waitForProjectEditorToLoad();
		
		//Find values of default session options selected on project page
		String sessionTypeProject = projectEditorPage.getSessionType();
		Boolean requiredNicknameProject = projectEditorPage.getRequireNicknameCheckboxStatus();
		String sessionModeProject = projectEditorPage.getSessionModeStatus();
		
		//Click on Create New Session button
		SessionEditorPage sessionEditorPage = projectEditorPage.clickCreateSessionButton();
		sessionEditorPage.waitForSessionEditorToLoad();
		
		//Verify session type is defaulted from the option selected at project level
		String sessionTypeSession = sessionEditorPage.getSessionType();
		Assert.assertTrue(sessionTypeSession.equals(sessionTypeProject), "Session type should be defaulted from project");
		
		//Verify required nickname is defaulted from the option selected at project level
		Boolean requiredNicknameSession = sessionEditorPage.getRequireNicknameCheckboxStatus();
		Assert.assertTrue(requiredNicknameSession.equals(requiredNicknameProject), "Required Nickname selection should be defaulted from project");
		
		//Verify session mode is defaulted from the option selected at project level
		String sessionModeSession = sessionEditorPage.getSessionModeStatus();
		Assert.assertTrue(sessionModeSession.equals(sessionModeProject), "Session Mode selection should be defaulted from project");
		
		//Verify status of Save Session button
		boolean saveButtonStatusBefore = sessionEditorPage.getSaveButtonStatus();
		assertion.assertFalse(saveButtonStatusBefore, "Save Session button should be default disabled");
		
		////Verify enabling of Save button on unsaved changes in project editor page
		sessionEditorPage.enterSessionName("testing");
		boolean saveButtonStatusAfter = sessionEditorPage.getSaveButtonStatus();
		Assert.assertTrue(saveButtonStatusAfter, "Save Session button should get enabled on unsaved changes");
		
		//Navigate back to user dashboard page
		userDashboardPage = sessionEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 7, groups = {"create"})
	public void createNewSession(Map<String, String> testData) {
		String sessionType = testData.get("sessionType");
		String projectName = testData.get("projectName");
		String sessionName = testData.get("sessionName");
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		//Navigate to project List page
		ProjectListPage projectListPage = userDashboardPage.clickProjectlistLink();
		projectListPage.waitForProjectlistPageToLoad();
		
		log.info("Searching Project...");
		//Search project Name and open project editor
		projectListPage.enterProjectNameToSearch(projectName);
		ProjectEditorPage projectEditorPage = projectListPage.clickProjectLink();
		projectEditorPage.waitForProjectEditorToLoad();
		
		//Click on Create New Session button
		SessionEditorPage sessionEditorPage = projectEditorPage.clickCreateSessionButton();
		sessionEditorPage.waitForSessionEditorToLoad();
		
		log.info("Entering new session details");
		//Enter Session Name
		sessionEditorPage.enterSessionName(sessionName);
		
		//Select Session type
		sessionEditorPage.selectSessionType(sessionType);
		
		//Enter Start Date
		sessionEditorPage.enterStartDate();
		
		//Click on Save session button
		sessionEditorPage.clickSaveSessionButton();
		sessionEditorPage.waitForSessionToSave();
		
		//Verify Session is saved successfully
		boolean sessionSaveStatus = sessionEditorPage.getSessionSaveStatus();
		Assert.assertTrue(sessionSaveStatus, "Session save failed");
		
		log.info("Enrolling participant to the session");
		//Enroll participant to the session
		sessionEditorPage.clickSelectParticipantLink();
		sessionEditorPage.waitForParticipantListToLoad();
		
		//Verify Update button is disabled by default
		boolean updateButtonStatusBefore = sessionEditorPage.getUpdateButtonStatus();
		assertion.assertFalse(updateButtonStatusBefore, "Update button should be disabled untill an enrollment change");
		
		//Select a participant list to enroll
		sessionEditorPage.selectParticipantList();
		
		//Verify Update button is enabled
		boolean updateButtonStatusAfter = sessionEditorPage.getUpdateButtonStatus();
		assertion.assertTrue(updateButtonStatusAfter, "Update button should be enabled after an enrollment change");
			
		log.info("Updating participant enrollment to the session");
		//Click on update button
		sessionEditorPage.clickUpdateButton();
		sessionEditorPage.waitForSessionToUpdate();
		
		//Navigate back to user dashboard page
		userDashboardPage = sessionEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}
}
