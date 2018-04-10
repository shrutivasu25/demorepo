package com.invoke.pages;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.invoke.base.BasePageObject;

public class ProjectEditorPage extends BasePageObject<ProjectEditorPage> {
	protected By pageHeaderText = By.xpath(prop.getProperty("pageHeaderText"));
	protected By projectNameInput = By.xpath(prop.getProperty("projectNameInput"));
	protected By saveButton = By.xpath(prop.getProperty("saveButton"));
	protected By participantListLink = By.xpath(prop.getProperty("participantListLink"));
	protected By participantCollectionDropdown = By.xpath(prop.getProperty("participantCollectionDropdown"));
	protected By participantCollectionName = By.xpath(prop.getProperty("participantCollectionName"));
	protected By selectCollectionButton = By.xpath(prop.getProperty("selectCollectionButton"));
	protected By mappedParticipantLink = By.xpath(prop.getProperty("mappedParticipantLink"));
	protected By surveyListDropdown = By.xpath(prop.getProperty("surveyListDropdown"));
	protected By surveyTemplateName = By.xpath(prop.getProperty("surveyTemplateName"));
	protected By privateUrlRadioButton = By.xpath(prop.getProperty("privateUrlRadioButton"));
	protected By publicUrlRadioButton = By.xpath(prop.getProperty("publicUrlRadioButton"));
	protected By requiredNicknameCheckbox = By.xpath(prop.getProperty("requiredNicknameCheckbox"));
	protected By sessionModeDropdown = By.xpath(prop.getProperty("sessionModeDropdown"));
	protected By liveSessionMode = By.xpath(prop.getProperty("liveSessionMode"));
	protected By homeLink = By.xpath(prop.getProperty("homeLink"));
	protected By manageProject = By.xpath(prop.getProperty("manageProject"));
	protected By saveProjectSuccess = By.xpath(prop.getProperty("saveProjectSuccess"));
	protected By createSessionButton = By.xpath(prop.getProperty("createSessionButton"));
	protected By liveSessionLink = By.xpath(prop.getProperty("liveSessionLink"));
	protected By openSessionLink = By.xpath(prop.getProperty("openSessionLink"));
	
	public ProjectEditorPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}
	
	public void waitForProjectEditorToLoad() {
		log.info("Waiting for project editor page to load");
		waitForVisibilityOfElement(projectNameInput);
		waitForVisibilityOfElement(requiredNicknameCheckbox, 10);
		waitForVisibilityOfElement(publicUrlRadioButton);
	}
	
	public void waitForSessionListToLoad() {
		log.info("Waiting for project editor page to load");
		waitForVisibilityOfElement(projectNameInput);
		waitForVisibilityOfElement(liveSessionLink);
	}

	public boolean getSaveButtonStatus() {
		log.info("Verifying status of project save button");
		return getElementStatus(saveButton);
	}

	public void enterProjectName(String projectName) {
		typeInput(projectName, projectNameInput);
	}

	public void clickParticipantListLink() {
		click(participantListLink);
	}

	public void clickParticipantCollectionDropdown() {
		click(participantCollectionDropdown);
	}

	public void selectParticipantCollection() {
		click(participantCollectionName);
	}

	public boolean getSelectButtonStatus() {
		log.info("Verifying status of select participant collection button");
		return getElementStatus(selectCollectionButton);
	}

	public void clickSelectParticipantCollectionButton() {
		click(selectCollectionButton);
	}

	public boolean isParticipantCollectionMapLinkDisplayed() {
		return isElementDisplayed(mappedParticipantLink);
	}

	public void clickSurveyListDropdwon() {
		click(surveyListDropdown);
	}

	public void selectSurveyTemplate() {
		click(surveyTemplateName);
	}
	
	public void selectPrivateType() {
		click(privateUrlRadioButton);
	}

	public boolean getPublicUrlRadioButtonStatus() {
		return isElementSelected(publicUrlRadioButton);
	}

	public void selectNicknameCheckbox() {
		boolean requiredNicknameStatus = getRequireNicknameCheckboxStatus(); 
		if(requiredNicknameStatus == false) {
			click(requiredNicknameCheckbox);
		}
	}

	public boolean getRequireNicknameCheckboxStatus() {
		return isElementSelected(requiredNicknameCheckbox);
	}

	public void selectLiveSessionMode() {
		click(liveSessionMode);
	}

	public String getSessionModeStatus() {
		return getText(sessionModeDropdown);
	}

	public void clickSaveProjectButton() {
		click(saveButton);
	}

	public UserDashboardPage clickHomeLink() {
		log.info("Navigating back to user dashboard page");
		click(homeLink);
		return new UserDashboardPage(driver, log, prop, config, jse);
	}

	public boolean isManageProjectPageHeaderDisplayed() {
		return isElementDisplayed(manageProject);
	}

	public void waitForProjectToSave() {
		log.info("Waiting for project to save");
		waitForVisibilityOfElement(saveProjectSuccess);
	}

	public String getProjectName() {
		return getElementAttributeValue(projectNameInput, "value");
	}

	public void updateProjectName(String updatedProjectName) {
		log.info("Updating project name");
		typeInput(updatedProjectName, projectNameInput);
	}

	public SessionEditorPage clickCreateSessionButton() {
		click(createSessionButton);
		return new SessionEditorPage(driver, log, prop, config, jse);
	}

	public String getSessionType() {
		boolean isPublic = isElementSelected(publicUrlRadioButton);
		if(isPublic) {
			return "public";
		} else {
			return "private";
		}
	}

	public SessionEditorPage clickLiveSessionLink() {
		click(liveSessionLink);
		return new SessionEditorPage(driver, log, prop, config, jse);
	}

	public SessionEditorPage clickOpenSessionLink() {
		click(openSessionLink);
		return new SessionEditorPage(driver, log, prop, config, jse);
	}

	public void waitForParticipantCollectionModal() {
		log.info("Waiting for participant collection modal to load");
		waitForVisibilityOfElement(participantCollectionDropdown);
	}

	public void waitForParticipantList() {
		log.info("Waiting for participant list to load");
		waitForVisibilityOfElement(participantCollectionName);
	}

	public void waitForSurveysToLoad() {
		log.info("Waiting for survey list to load");
		waitForVisibilityOfElement(surveyTemplateName);
	}

	public void clickSessionModeDropdown() {
		click(sessionModeDropdown);
	}

	public void waitForSessionModesToLoad() {
		log.info("Waiting for session modes to load");
		waitForVisibilityOfElement(liveSessionMode);
	}

	public void scrollPageDown() {
		jse.executeScript("scroll(0, 250);");
	}

	public void scrollPageUp() {
		jse.executeScript("scroll(0, -250);");
	}
}
