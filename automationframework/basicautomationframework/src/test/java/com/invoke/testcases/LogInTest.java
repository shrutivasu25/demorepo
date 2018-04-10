package com.invoke.testcases;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.LogInPage;
import com.invoke.pages.UserDashboardPage;
import com.relevantcodes.extentreports.LogStatus;

public class LogInTest extends BaseTest {

	@Test(priority = 1, groups = { "positive" })
	public void positiveLogInTest() {
		LogInPage logInPage = new LogInPage(driver, log);
		String expectedPageTitle = "Invoke®";
		String correctUserName = "aj";

		// Open Invoke url - http://qa1.app.invoke.com/a/ui/login
		logInPage.openLogInPage();
		test.log(LogStatus.PASS, "Login Page Launched");

		// Enter username, passowrd and client
		logInPage.enterUsernamePassword("aj", "Welcome1", "test");

		// Click on Sign In button
		UserDashboardPage userDashboard = logInPage.clickSignInButton();
		userDashboard.waitForUserDashboardToLoad();
		test.log(LogStatus.PASS, "User Home Page launched");

		// Verifications
		// - Verify title of the page is correct - Invoke®
		log.info("Verifying logged in user details");
		String actualPageTitle = userDashboard.getTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualPageTitle),
				"Page title is not matching.\nExpected: " + expectedPageTitle + "\nActual: " + actualPageTitle + ".");

		// - Verify correct name on profile page
		Assert.assertTrue(userDashboard.isCorrectUserDashboardLoaded(correctUserName), "Username is not matching.");
		test.log(LogStatus.PASS, "Verified dashboard for logged-in user");
	}

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = { "negative", "broken" })
	public void negativeLogInTest(Map<String, String> testData) {
		String expectedErrorMessage = "The user name and password you entered don't match.";
		String expectedErrorMessage1 = "Sorry, domain not found.";
		String testNumber = testData.get("No");
		String userName = testData.get("Username");
		String password = testData.get("Password");
		String client = testData.get("Client");
		String description = testData.get("Description");

		log.info("Test No #" + testNumber + " for " + description + " Where\nUsername: " + userName
				+ "\nPassword: " + password + "\nClient: " + client);

		LogInPage logInPage = new LogInPage(driver, log);

		// Open Invoke url - http://qa1.app.invoke.com/a/ui/login
		logInPage.openLogInPage();
		test.log(LogStatus.PASS, "Login Page Launched");
		
		// Enter username, passowrd and client
		logInPage.enterUsernamePassword(userName, password, client);

		// Click on Sign In button
		logInPage.clickSignInButton();

		// Verifying error message for incorrect credentials
		String actualErrorMessage = logInPage.getLogInErrorMessage();
		
		if(actualErrorMessage.contains("Sorry")) {
			Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage1),
					"Validation message is not matching.\nExpected: " + expectedErrorMessage1 + "\nActual: "
							+ actualErrorMessage + ".");
			test.log(LogStatus.PASS, "Login failed due to invalid client");
		} else {
			Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
					"Validation message is not matching.\nExpected: " + expectedErrorMessage + "\nActual: "
							+ actualErrorMessage + ".");
			test.log(LogStatus.PASS, "Login failed due to invalid username/password");
		}
		
	}
}
