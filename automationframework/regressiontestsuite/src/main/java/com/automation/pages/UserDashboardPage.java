package com.automation.pages;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.automation.base.BasePageObject;

public class UserDashboardPage extends BasePageObject<UserDashboardPage> {
	private By signOffLink = By.xpath(prop.getProperty("signOffLink"));

	public UserDashboardPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}
	
	public void waitForUserDashboardToLoad() {
		log.info("Waiting for user dashboard to load");
		waitForVisibilityOfElement(signOffLink);
	} 

	public String getDashboardPageTitle() {
		return driver.getTitle();
	}
}
