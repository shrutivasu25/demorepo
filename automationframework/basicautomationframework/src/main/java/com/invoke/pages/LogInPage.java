package com.invoke.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.invoke.base.BasePageObject;

public class LogInPage extends BasePageObject<LogInPage>{
	private static final String URL = "http://app.invoke.com/a/ui/login";
	
	private By usernameField = By.xpath("//input[@name='username']");
	private By passwordField = By.xpath("//input[@type='password']");
	private By clientField = By.xpath("//input[@name='loginClient']");
	private By signInButton = By.xpath("//button[@type='submit']");
	private By errorMessage = By.xpath("//div[@class='errors-wrapper']");
	
	public LogInPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	public void openLogInPage() {
		getPage(URL);
	}
	
	public void enterUsernamePassword(String username, String password, String client) {
		log.info("Entering login details");
		typeInput(username, usernameField);
		typeInput(password, passwordField);
		typeInput(client, clientField);
	}
	
	public UserDashboardPage clickSignInButton() {
		log.info("Siging In");
		click(signInButton);
		return new UserDashboardPage(driver, log);
	}

	public String getLogInErrorMessage() {
		log.info("Waiting for error message");
		waitForVisibilityOfElement(errorMessage, 20);
		return getText(errorMessage);
	}
}
