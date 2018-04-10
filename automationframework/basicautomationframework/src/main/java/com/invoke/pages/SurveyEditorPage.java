package com.invoke.pages;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.invoke.base.BasePageObject;

public class SurveyEditorPage extends BasePageObject<SurveyEditorPage> {
	protected By surveyName = By.xpath(prop.getProperty("surveyName"));
	protected By homeLink = By.xpath(prop.getProperty("homeLink"));
	protected By contentMenuOption = By.xpath(prop.getProperty("contentMenuOption"));
	protected By myMediaTab = By.xpath(prop.getProperty("myMediaTab"));
	protected By addMediaDropdown = By.xpath(prop.getProperty("addMediaDropdown"));
	protected By uploadNewMediaOption = By.xpath(prop.getProperty("uploadNewMediaOption"));
	protected By mediaTitleInput = By.xpath(prop.getProperty("mediaTitleInput"));
	protected By saveMediaButton = By.xpath(prop.getProperty("saveMediaButton"));
	protected By linkMediaByURLOtion = By.xpath(prop.getProperty("linkMediaByURLOtion"));
	protected By selectLinkTypeDropdown = By.xpath(prop.getProperty("selectLinkTypeDropdown"));
	protected By linkMediaTitleInput = By.xpath(prop.getProperty("linkMediaTitleInput"));
	protected By linkMediaURL = By.xpath(prop.getProperty("linkMediaURL"));
	protected By nextButton = By.xpath(prop.getProperty("nextButton"));
	
	public SurveyEditorPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}

	public String getSurveyName() {
		return getText(surveyName);
	}

	public void waitForSurveyEditorToLoad() {
		log.info("Wait for survey editor to load");
		waitForVisibilityOfElement(surveyName);
		waitForVisibilityOfElement(contentMenuOption, 10);
	}

	public UserDashboardPage clickHomeLink() {
		click(homeLink);
		return new UserDashboardPage(driver, log, prop, config, jse);
	}

	public void clickContentMenu() {
		log.info("Launching content modal");
		click(contentMenuOption);
	}

	public void clickMyMediaTab() {
		log.info("Navigating to my media tab");
		click(myMediaTab);
	}

	public void clickAddMediaDropdown() {
		click(addMediaDropdown);
	}

	public void clickUploadNewMedia() {
		click(uploadNewMediaOption);
	}

	public void waitForMyMediaToLoad() {
		log.info("Waiting for My Media to load");
		waitForVisibilityOfElement(addMediaDropdown);
	}

	public void enterMediaTitle(String mediaTitle) {
		typeInput(mediaTitle, mediaTitleInput);
	}

	public void waitForMediaTitleInput() {
		log.info("Waiting for Media title input to display");
		waitForVisibilityOfElement(mediaTitleInput);
	}

	public void waitForContentModalToLoad() {
		log.info("Waiting for content modal to load");
		waitForVisibilityOfElement(myMediaTab);
	}

	public void clickSaveButton() {
		click(saveMediaButton);
	}

	public void clickLinkMediaByURL() {
		click(linkMediaByURLOtion);
	}

	public void waitForLinkToFileModalToLoad() {
		log.info("Waiting for Link to Media modal to load");
		waitForVisibilityOfElement(selectLinkTypeDropdown);
	}

	public void selectFileType(String mediaType) {
		switch(mediaType) {
		case "image":
			selectDropdownValue(selectLinkTypeDropdown, "Image");
			break;
			
		case "flash":
			selectDropdownValue(selectLinkTypeDropdown, "Flash");
			break;
			
		case "video":
			selectDropdownValue(selectLinkTypeDropdown, "Video");
			break;
			
		default:
			selectDropdownValue(selectLinkTypeDropdown, "Flash");
			break;	
		}
	}

	public void enterMediaLinkTitle(String mediaTitle) {
		typeInput(mediaTitle, linkMediaTitleInput);
	}

	public void enterMediaURL(String mediaURL) {
		typeInput(mediaURL, linkMediaURL);
	}

	public void clickNextButton() {
		click(nextButton);
	}
}

