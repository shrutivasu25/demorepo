package com.invoke.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.invoke.base.BasePageObject;

public class UserDashboardPage extends BasePageObject<UserDashboardPage> {
	private By mySessionsTableHeader = By.xpath("//span[text()='My Sessions']");
	private By projectsMenuOption = By.xpath("//a[text()='Projects']");
	private By userNameText = By.xpath("//h3[text()='aj']");

	protected UserDashboardPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	public void waitForUserDashboardToLoad() {
		log.info("Waiting for user dashboard to load");
		waitForVisibilityOfElement(mySessionsTableHeader);
		waitForVisibilityOfElement(projectsMenuOption, 10);
	}

	public boolean isCorrectUserDashboardLoaded(String correctUserName) {
		if (getText(userNameText).equals(correctUserName)) {
			return true;
		}
		return false;
	}
}
