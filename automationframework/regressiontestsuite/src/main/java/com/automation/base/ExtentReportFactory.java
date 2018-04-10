package com.automation.base;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportFactory {
	protected static ExtentReports extentReportManager(Logger log, Properties config) {
		ExtentReports extent;
		log.info("Initializing Extent Report");
		extent = new ExtentReports(config.getProperty("reportPath"), true);
		extent.loadConfig(new File("src/main/resources/extent-config.xml"));
		extent.addSystemInfo("Environment", config.getProperty("testEnv"));
		return extent;
	}
}
