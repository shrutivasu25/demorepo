package com.invoke.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver getDriver(String browser, Logger log) {
		WebDriver driver;
		log.info("Starting " + browser + " driver");
		
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;	
			
		default:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		//driver.manage().window().maximize();
		return driver;
	}
}
