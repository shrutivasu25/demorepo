package com.invoke.testcases;

import java.util.Map;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.ProjectEditorPage;
import com.invoke.pages.ProjectListPage;
import com.invoke.pages.SessionEditorPage;
import com.invoke.pages.UserDashboardPage;

public class TakeSurveyTest extends BaseTest {
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 1, groups = {"takeLiveSurvey"})
	public void takeLiveSurvey(Map<String, String> testData) {
		String projectName = testData.get("projectName");

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
		
		//Launch participant survey taking url
		sessionEditorPage.clickSurveyNoShows();
		sessionEditorPage.waitForParticipantLinksToLoad();
	}
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 1, groups = {"takeOpenSurvey"})
	public void takeOpenSurvey(Map<String, String> testData) {
		String projectName = testData.get("projectName");

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
		SessionEditorPage sessionEditorPage = projectEditorPage.clickOpenSessionLink();
		sessionEditorPage.waitForSessionEditorToLoad();
		
		//Launch participant survey taking url
		sessionEditorPage.clickSurveyNoShows();
		sessionEditorPage.waitForParticipantLinksToLoad();

	}
}

