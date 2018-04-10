package com.invoke.testcases;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.ProjectEditorPage;
import com.invoke.pages.UserDashboardPage;

public class CreateProjectTest extends BaseTest {
	
	@Test(priority = 3, groups = {"verify"})
	public void verifyProjectEditor() {
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		// Click on Create Project link
		ProjectEditorPage projectEditorPage = userDashboardPage.clickCreateProjectLink();
		projectEditorPage.waitForProjectEditorToLoad();

		// Verify status of Save Project button - should be default disabled
		boolean saveButtonStatusBefore = projectEditorPage.getSaveButtonStatus();
		assertion.assertFalse(saveButtonStatusBefore, "Save Project button should be default disabled");
		
		//Verify enabling of Save button on unsaved changes in project editor page
		projectEditorPage.enterProjectName("testing");
		boolean saveButtonStatusAfter = projectEditorPage.getSaveButtonStatus();
		Assert.assertTrue(saveButtonStatusAfter, "Save Project button should get enabled on unsaved changes");
		
		//Verify Public URL radio button is selected by default
		boolean publicUrlradioButtonStatus = projectEditorPage.getPublicUrlRadioButtonStatus();
		assertion.assertTrue(publicUrlradioButtonStatus, "Public URL radio button should be default selected");
		
		//Verify require nickname checkbox is not selected by default
		boolean requireNicknameCheckboxStatus = projectEditorPage.getRequireNicknameCheckboxStatus();
		assertion.assertFalse(requireNicknameCheckboxStatus, "Require Nickname checkbox should be default not-selected");
		
		//Verify default selected option in session mode
		String sessionModeStatus = projectEditorPage.getSessionModeStatus();
		assertion.assertTrue(sessionModeStatus.contains("Open"), "Default session mode should be Open");

		//Navigate back to user dashboard page
		userDashboardPage = projectEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 4, groups = {"create"})
	public void createNewProject(Map<String, String> testData) throws InterruptedException {
		String expectedPageTitle = testData.get("PageTitle");
		String projectName = testData.get("projectName");

		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		// Click on Create Project link
		ProjectEditorPage projectEditorPage = userDashboardPage.clickCreateProjectLink();
		projectEditorPage.waitForProjectEditorToLoad();
		
		// - Verify title of the page is correct - Project
		String actualPageTitle = userDashboardPage.getTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualPageTitle),
				"Page title is not matching.\nExpected: " + expectedPageTitle + "\nActual: " + actualPageTitle + ".");
		
		log.info("Entering new project information");
		// Enter Project Name
		projectEditorPage.enterProjectName(projectName);

		log.info("Mapping Participant collection to the Project");
		// Select Participant List Collection to map to this project
		projectEditorPage.clickParticipantListLink();
		projectEditorPage.waitForParticipantCollectionModal();
		projectEditorPage.clickParticipantCollectionDropdown();
		projectEditorPage.selectParticipantCollection();
		Thread.sleep(1000);
		projectEditorPage.clickSelectParticipantCollectionButton();
		Thread.sleep(1000);
		
		projectEditorPage.scrollPageDown();
		//Verify mapped Participant collection link on project editor page 
		boolean particpantCollectioLinkDisplayed = projectEditorPage.isParticipantCollectionMapLinkDisplayed();
		Assert.assertTrue(particpantCollectioLinkDisplayed, "Unable to map participant collection to Project");
		
		log.info("Mapping survey template to project");
		// Select an existing survey template to map to this project
		projectEditorPage.clickSurveyListDropdwon();
		projectEditorPage.waitForSurveysToLoad();
		projectEditorPage.selectSurveyTemplate();
		
		log.info("Selecting survey type");
		// Select survey type as Private
		projectEditorPage.selectPrivateType();
		
		log.info("Selecting required nickname option as yes");
		// Make Nickname Required for the surveys within this project
		projectEditorPage.selectNicknameCheckbox();

		Thread.sleep(1000);
		log.info("Selecting session mode");
		// Set default session type as Live
		projectEditorPage.clickSessionModeDropdown();
		projectEditorPage.waitForSessionModesToLoad();
		projectEditorPage.selectLiveSessionMode();

		log.info("Saving project");
		// Click on Save button
		projectEditorPage.clickSaveProjectButton();
		
		//Wait for Project to Save
		projectEditorPage.waitForProjectToSave();

		// Verify successful creation of the project
		boolean manageProjectPageHeaderStatus = projectEditorPage.isManageProjectPageHeaderDisplayed();
		Assert.assertTrue(manageProjectPageHeaderStatus, "Project creation failded");
		
		projectEditorPage.scrollPageUp();
		
		//Navigate back to user dashboard page
		userDashboardPage = projectEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}
}
