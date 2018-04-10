package com.automation.base;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject<T> {
	protected WebDriver driver;
	protected Logger log;
	protected Properties prop;
	protected Properties config;
	protected JavascriptExecutor jse;

	protected BasePageObject(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		this.driver = driver;
		this.log = log;
		this.prop = prop;
		this.config = config;
		this.jse = jse;
	}
	
	@SuppressWarnings("unchecked")
	protected T getPage(String url) {
		driver.get(url);
		return (T) this;
	}
	
	protected void typeInput(String keyInput, By element) {
		clearText(element);
		find(element).sendKeys(keyInput);
	}

	private WebElement find(By element) {
		return driver.findElement(element);
	}
	
	protected void click(By element) {
		find(element).click();
	}
	
	protected void waitForVisibilityOfElement(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
			attempts++;
		}
	}
	
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : Integer.parseInt(config.getProperty("defaultExplicitWait"));
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	protected String getText(By element) {
		return find(element).getText();
	}
	
	protected void clearText(By element) {
		int len = find(element).getAttribute("value").length(); 
		for(int i = 0; i < len; i++ ) {
			find(element).sendKeys(Keys.BACK_SPACE);
		}
	}
	
	protected void clickMouseHoverLink(By elementMenu, By elementSubMenu) {
		Actions action = new Actions(driver);
		action.moveToElement(find(elementMenu)).perform();
		action.moveToElement(find(elementSubMenu));
		action.click();
		action.perform();
	}
	
	protected boolean getElementStatus(By element) {
		return find(element).isEnabled();
	}
	
	protected boolean isElementDisplayed(By element) {
		return find(element).isDisplayed();
	}
	
	protected boolean isElementSelected(By element) {
		return find(element).isSelected();
	}
	
	protected String getElementAttributeValue(By element, String attribute) {
		return find(element).getAttribute(attribute);
	}
	
	protected void tabOut(By element) {
		find(element).sendKeys(Keys.TAB);
	}
	
	protected void selectDropdownValue(By element, String value) {
		Select drpMediaType = new Select(find(element));
		drpMediaType.selectByValue(value);
	}
}
