package com.invoke.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.invoke.base.BasePageObject;

public class ProjectListPage extends BasePageObject<ProjectListPage> {
	protected By searchProjectName = By.xpath(prop.getProperty("searchProjectName"));
	protected By projectNameColumn = By.xpath(prop.getProperty("projectNameColumn"));
	protected By projectLink = By.xpath(prop.getProperty("projectLink"));

	protected ProjectListPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}

	public void waitForProjectlistPageToLoad() {
		log.info("Waiting for project list page to load");
		waitForVisibilityOfElement(searchProjectName);
		waitForVisibilityOfElement(projectNameColumn, 10);
	}

	public void enterProjectNameToSearch(String projectName) {
		typeInput(projectName, searchProjectName);
	}

	public ProjectEditorPage clickProjectLink() {
		log.info("Navigating to project editor page");
		click(projectLink);
		return new ProjectEditorPage(driver, log, prop, config, jse);
	}
}
