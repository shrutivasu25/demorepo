package com.invoke.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.invoke.base.BasePageObject;

public class SurveyNamePage extends BasePageObject<SurveyNamePage> {
	protected By surveyNameInput = By.xpath(prop.getProperty("surveyNameInput"));
	protected By getStartedButton = By.xpath(prop.getProperty("getStartedButton"));
	protected By createSurveyButton = By.xpath(prop.getProperty("createSurveyButton"));

	protected SurveyNamePage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}

	public void enterSurveyName(String surveyName) {
		log.info("Enter survey name");
		typeInput(surveyName, surveyNameInput);
	}

	public void waitForSurveyNamePageToLoad() {
		log.info("Waiting for Survey Name page to load");
		waitForVisibilityOfElement(getStartedButton);
	}

	public void clickGetStartedButton() {
		log.info("Waiting for survey name field to be displayed");
		click(getStartedButton);
	}

	public void waitForSurveyNameFieldToLoad() {
		waitForVisibilityOfElement(surveyNameInput);
	}

	public SurveyEditorPage clickCreateButton() {
		log.info("Creating new survey with given name");
		click(createSurveyButton);
		return new SurveyEditorPage(driver, log, prop, config, jse);
	}
}
