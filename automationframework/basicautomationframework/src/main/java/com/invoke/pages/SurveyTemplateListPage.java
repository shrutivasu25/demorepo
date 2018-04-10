package com.invoke.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.invoke.base.BasePageObject;

public class SurveyTemplateListPage extends BasePageObject<SurveyTemplateListPage> {
	private By searchSurveyName = By.xpath(prop.getProperty("searchSurveyName"));
	private By surveyLink = By.xpath(prop.getProperty("surveyLink"));

	protected SurveyTemplateListPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}

	public void enterSurveyNameToSearch(String surveyName) {
		typeInput(surveyName, searchSurveyName);
	}

	public void waitForSurveyListPageToLoad() {
		log.info("Waiting for survey list to load");
		waitForVisibilityOfElement(searchSurveyName);
		waitForVisibilityOfElement(surveyLink, 10);
	}

	public SurveyEditorPage clickSurveyNameLink() {
		click(surveyLink);
		return new SurveyEditorPage(driver, log, prop, config, jse);
	}

}
