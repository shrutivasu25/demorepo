package com.automation.base;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	protected static WebDriver driver;
	protected static Logger log;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected static Properties prop;
	protected static Properties config;
	protected static JavascriptExecutor jse;
	protected static SoftAssert assertion;
	
	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	protected void setUpClass(String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = Logger.getLogger(testName);
		prop = ObjectRepository.loadObjectRepository(log);
		config = TestConfiguration.loadTestConfigurations(log);
		log.info("Test set up");
		extent = ExtentReportFactory.extentReportManager(log, config);
		test = extent.startTest(ctx.getCurrentXmlTest().getName());
		driver = BrowserFactory.getDriver(browser, log);
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "Browser launched successfully");
		assertion = new SoftAssert();
		jse = (JavascriptExecutor)driver;
	}
	
	@AfterTest(alwaysRun = true)
	protected void tearDown() {
		log.info("Test tear down");
		driver.close();
		assertion.assertAll();
	}
	
	@AfterSuite(alwaysRun = true)
	protected void closeExtentReport() {
		extent.flush();
		//extent.close();
	}
}
