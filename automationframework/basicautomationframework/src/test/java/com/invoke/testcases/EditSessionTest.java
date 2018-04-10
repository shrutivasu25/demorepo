package com.invoke.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.ProjectEditorPage;
import com.invoke.pages.ProjectListPage;
import com.invoke.pages.SessionEditorPage;
import com.invoke.pages.UserDashboardPage;

public class EditSessionTest extends BaseTest {
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 8, groups = {"edit"})
	public void editSession(Map<String, String> testData) {
		String projectName = testData.get("projectName");
		String sessionName = testData.get("sessionName");
		String updatedSessionName = testData.get("updatedSessionName");
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		//Navigate to project List page
		ProjectListPage projectListPage = userDashboardPage.clickProjectlistLink();
		projectListPage.waitForProjectlistPageToLoad();
		
		log.info("Searching Project name to edit session");
		//Enter project Name to search
		projectListPage.enterProjectNameToSearch(projectName);
		
		//Click on Project link
		ProjectEditorPage projectEditorPage = projectListPage.clickProjectLink();
		projectEditorPage.waitForSessionListToLoad();
		
		// Navigate to session editor page for live session
		SessionEditorPage sessionEditorPage = projectEditorPage.clickLiveSessionLink();
		sessionEditorPage.waitForSessionEditorToLoad();
		
		//Verify status of Save button - should be default disabled
		boolean saveButtonStatusBefore = sessionEditorPage.getSaveButtonStatus();
		assertion.assertFalse(saveButtonStatusBefore, "Save Session button should be default disabled");
		
		//Edit Session name
		sessionEditorPage.updateSessionName(updatedSessionName);
		
		// Verify status of Save Session button - should be enabled after unsaved changes
		boolean saveButtonStatusAfter = sessionEditorPage.getSaveButtonStatus();
		Assert.assertTrue(saveButtonStatusAfter, "Save Session button should be enabled on editing");
				
		//Click on Save button
		sessionEditorPage.clickSaveSessionButton();
		sessionEditorPage.waitForSessionToSave();
		
		// Verify status of Save Session button - should be default disabled
		boolean saveButtonStatusAgain = sessionEditorPage.getSaveButtonStatus();
		assertion.assertFalse(saveButtonStatusAgain, "Save Session button should be disabled after save");
				
		log.info("Verifying updated session name after save");
		//Verify Session save is successful
		String sessionNameUpdated = sessionEditorPage.getSessionName();
		Assert.assertTrue(sessionNameUpdated.equals(updatedSessionName), "Session save operation failed: Expected Project Name: " + sessionNameUpdated + " Actual Project Name: " + updatedSessionName );
		
		//Renaming session back to the original name
		sessionEditorPage.updateSessionName(sessionName);
		sessionEditorPage.clickSaveSessionButton();
		sessionEditorPage.waitForSessionToSave();
		
		//Navigate back to user dashboard page
		userDashboardPage = sessionEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}
}
