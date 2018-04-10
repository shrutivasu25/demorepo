package com.invoke.base;

import java.io.File;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	protected WebDriver driver;
	protected Logger log;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	
	@BeforeSuite(alwaysRun = true)
	protected void extentReportManager() {
		extent = new ExtentReports("test-output/InvokeExtentReport.html", true);
		extent.loadConfig(new File("src/main/resources/extent-config.xml"));
		extent.addSystemInfo("Environment", "Prod");	
	}
	
	@BeforeClass(alwaysRun = true)
	protected void setUpClass(ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = Logger.getLogger(testName);
	}
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	protected void methodSetUp(String browser, ITestContext ctx) {
		test = extent.startTest(ctx.getCurrentXmlTest().getName());
		log.info("Method set up");
		driver = BrowserFactory.getDriver(browser, log);
		test.log(LogStatus.PASS, "Browser launched successfully");
	}
	
	@AfterMethod(alwaysRun = true)
	protected void methodTearDown() {
		log.info("Method tear down");
		driver.close();
	}
	
	@AfterSuite(alwaysRun = true)
	protected void closeExtentReport() {
		extent.flush();
		//extent.close();
	}
}
