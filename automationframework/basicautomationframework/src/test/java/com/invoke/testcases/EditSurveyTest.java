package com.invoke.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.SurveyEditorPage;
import com.invoke.pages.SurveyTemplateListPage;
import com.invoke.pages.UserDashboardPage;

public class EditSurveyTest extends BaseTest {
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 1, groups = {"uploadMedia"})
	public void openSurveyTemplate(Map<String, String> testData) {
		String surveyName = testData.get("surveyName");
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		//Navigate to survey List Page
		SurveyTemplateListPage  surveyTemplateListPage = userDashboardPage.clickSurveyTemplateList();
		surveyTemplateListPage.waitForSurveyListPageToLoad();
		
		//Enter survey name to search 
		surveyTemplateListPage.enterSurveyNameToSearch(surveyName);
		
		//Navigate to survey editor page
		SurveyEditorPage surveyEditorPage = surveyTemplateListPage.clickSurveyNameLink();
		surveyEditorPage.waitForSurveyEditorToLoad();
		
		//Open Content modal
		surveyEditorPage.clickContentMenu();
		surveyEditorPage.waitForContentModalToLoad();
						
		//Switch to My Media tab
		surveyEditorPage.clickMyMediaTab();
		surveyEditorPage.waitForMyMediaToLoad();
	}
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = {"uploadMedia"})
	public void uploadMediaFile(Map<String, String> testData) throws IOException, InterruptedException {
		String mediaFileName = testData.get("mediaFileName");
		String mediaTitle = testData.get("mediaTitle");
		
		//Find absolute path for the media file
		File file = new File(mediaFileName);
		String absoluteMediaPath = file.getAbsolutePath();
		
		SurveyEditorPage surveyEditorPage = new SurveyEditorPage(driver, log, prop, config, jse);

		//Click on upload media file
		log.info("Browsing media to upload");
		surveyEditorPage.clickAddMediaDropdown();
		surveyEditorPage.clickUploadNewMedia();
		
		Thread.sleep(3000);
				
		//Upload media file using AutoIT utility
		Runtime.getRuntime().exec("src/test/resources/chromeFileUpload.exe"+" "+absoluteMediaPath);
		
		surveyEditorPage.waitForMediaTitleInput();
		
		//Add title for media and save
		surveyEditorPage.enterMediaTitle(mediaTitle);
		surveyEditorPage.clickSaveButton();
		
		Thread.sleep(5000);
	}
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 3, groups = {"uploadMedia"})
	public void uploadMediaURL(Map<String, String> testData) throws IOException, InterruptedException {
		String mediaType = testData.get("mediaType");
		String mediaTitle = testData.get("mediaTitle");
		String mediaURL = testData.get("mediaURL");
		
		SurveyEditorPage surveyEditorPage = new SurveyEditorPage(driver, log, prop, config, jse);

		//Click on upload media file
		log.info("Browsing media to upload");
		surveyEditorPage.clickAddMediaDropdown();
		surveyEditorPage.clickLinkMediaByURL();
		
		//Opening Link to Media modal
		surveyEditorPage.waitForLinkToFileModalToLoad();
		
		log.info("Entering media details to link");
		//Select flash,image or video type
		surveyEditorPage.selectFileType(mediaType);
		
		//Enter title for media to link
		surveyEditorPage.enterMediaLinkTitle(mediaTitle);
		
		//Enter url of the media to link
		surveyEditorPage.enterMediaURL(mediaURL);
		
		log.info("Adding media to library");
		//Click next to add media to library
		surveyEditorPage.clickNextButton();
		
		Thread.sleep(3000);
	}

}
