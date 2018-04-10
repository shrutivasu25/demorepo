package com.invoke.testcases;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.ProjectEditorPage;
import com.invoke.pages.ProjectListPage;
import com.invoke.pages.UserDashboardPage;

public class EditProjectTest extends BaseTest {
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 5, groups = {"edit"})
	public void editProject(Map<String, String> testData) {
		String projectName = testData.get("projectName");
		String updatedProjectName = testData.get("updatedProjectName");
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		//Navigate to project List page
		ProjectListPage projectListPage = userDashboardPage.clickProjectlistLink();
		projectListPage.waitForProjectlistPageToLoad();
		
		log.info("Searching Project name to edit");
		//Enter project Name to search
		projectListPage.enterProjectNameToSearch(projectName);
		
		//Click on Project link
		ProjectEditorPage projectEditorPage = projectListPage.clickProjectLink();
		projectEditorPage.waitForProjectEditorToLoad();
		
		// Verify status of Save Project button - should be default disabled
		boolean saveButtonStatus = projectEditorPage.getSaveButtonStatus();
		assertion.assertFalse(saveButtonStatus, "Save Project button should be default disabled");
		
		//Edit Project name
		projectEditorPage.updateProjectName(updatedProjectName);
		
		// Verify status of Save Project button - should be enabled after unsaved changes
		boolean saveButtonStatusAgain = projectEditorPage.getSaveButtonStatus();
		Assert.assertTrue(saveButtonStatusAgain, "Save Project button should be enabled on editing");
				
		//Click on Save button
		projectEditorPage.clickSaveProjectButton();
		projectEditorPage.waitForProjectToSave();
		
		// Verify status of Save Project button - should be default disabled
		boolean saveButtonStatusAfter = projectEditorPage.getSaveButtonStatus();
		assertion.assertFalse(saveButtonStatusAfter, "Save Project button should be disabled after save");
				
		log.info("Verifying updated project name after save");
		//Verify project save is successful
		String projectNameUpdated = projectEditorPage.getProjectName();
		Assert.assertTrue(projectNameUpdated.equals(updatedProjectName), "Project save operation failed: Expected Project Name: " + projectNameUpdated + " Actual Project Name: " + updatedProjectName );
		
		//Renaming project back to the original name
		projectEditorPage.updateProjectName(projectName);
		projectEditorPage.clickSaveProjectButton();
		projectEditorPage.waitForProjectToSave();
		
		//Navigate back to user dashboard page
		userDashboardPage = projectEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}
}
