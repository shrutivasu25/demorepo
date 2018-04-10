package com.invoke.pages;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.invoke.base.BasePageObject;

public class SessionEditorPage extends BasePageObject<SessionEditorPage> {
	protected By sessionModeDropdownSession = By.xpath(prop.getProperty("sessionModeDropdownSession"));
	protected By liveSessionModeOption = By.xpath(prop.getProperty("liveSessionModeOption"));
	protected By openSessionModeOption = By.xpath(prop.getProperty("openSessionModeOption"));
	protected By sessionNameInput = By.xpath(prop.getProperty("sessionNameInput"));
	protected By startDateInput = By.xpath(prop.getProperty("startDateInput"));
	protected By saveSessionButton = By.xpath(prop.getProperty("saveSessionButton"));
	protected By todaysDate = By.xpath(prop.getProperty("todaysDate"));
	protected By saveSessionSuccess = By.xpath(prop.getProperty("saveSessionSuccess"));
	protected By selectParticipantLink = By.xpath(prop.getProperty("selectParticipantLink"));
	protected By participantListHeader = By.xpath(prop.getProperty("participantListHeader"));
	protected By updateButton = By.xpath(prop.getProperty("updateButton"));
	protected By participantListCheckbox = By.xpath(prop.getProperty("participantListCheckbox"));
	protected By publicUrlRadioButton = By.xpath(prop.getProperty("publicUrlRadioButton"));
	protected By requiredNicknameCheckbox = By.xpath(prop.getProperty("requiredNicknameCheckbox"));
	protected By homeLink = By.xpath(prop.getProperty("homeLink"));
	protected By surveyNoShowsLink = By.xpath(prop.getProperty("surveyNoShowsLink"));
	protected By participantSurveyLink = By.xpath(prop.getProperty("participantSurveyLink"));

	protected SessionEditorPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}

	public void waitForSessionEditorToLoad() {
		log.info("Waiting for Session editor page to load");
		waitForVisibilityOfElement(sessionModeDropdownSession);
	}

	public void enterSessionName(String sessionName) {
		typeInput(sessionName, sessionNameInput);
	}

	public void enterStartDate() {
		click(startDateInput);
		click(todaysDate);
	}

	public void clickSaveSessionButton() {
		log.info("Saving session");
		click(saveSessionButton);
	}

	public void waitForSessionToSave() {
		log.info("Waiting for session to save");
		waitForVisibilityOfElement(saveSessionSuccess);
	}

	public boolean getSessionSaveStatus() {
		return isElementDisplayed(selectParticipantLink);
	}

	public void clickSelectParticipantLink() {
		click(selectParticipantLink);
	}

	public void waitForParticipantListToLoad() {
		waitForVisibilityOfElement(participantListHeader);
	}

	public boolean getUpdateButtonStatus() {
		return getElementStatus(updateButton);
	}

	public void selectParticipantList() {
		click(participantListCheckbox);
	}

	public void clickUpdateButton() {
		click(updateButton);
	}

	public String getSessionType() {
		boolean isPublic = isElementSelected(publicUrlRadioButton);
		if(isPublic) {
			return "public";
		} else {
			return "private";
		}
	}

	public Boolean getRequireNicknameCheckboxStatus() {
		return isElementSelected(requiredNicknameCheckbox);
	}

	public String getSessionModeStatus() {
		return getText(sessionModeDropdownSession);
	}

	public boolean getSaveButtonStatus() {
		log.info("Verifying status of session save button");
		return getElementStatus(saveSessionButton);
	}

	public UserDashboardPage clickHomeLink() {
		log.info("Navigating back to user dashboard page");
		click(homeLink);
		return new UserDashboardPage(driver, log, prop, config, jse);
	}

	public void updateSessionName(String updatedSessionName) {
		log.info("Updating Session name");
		typeInput(updatedSessionName, sessionNameInput);
	}

	public String getSessionName() {
		return getElementAttributeValue(sessionNameInput, "value");
	}

	public void selectSessionType(String sessionType) {
		log.info("Select live/open session type");
		click(sessionModeDropdownSession);
		if(sessionType == "live") {
			click(liveSessionModeOption);
		} else {
			click(openSessionModeOption);
		}
	}

	public void waitForSessionToUpdate() {
		waitForVisibilityOfElement(homeLink);
		waitForVisibilityOfElement(saveSessionButton,10);
	}

	public void clickSurveyNoShows() {
		click(surveyNoShowsLink);
	}

	public void waitForParticipantLinksToLoad() {
		waitForVisibilityOfElement(participantSurveyLink, 100);
	}
}
