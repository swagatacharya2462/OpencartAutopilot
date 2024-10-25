package TestCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.Base;
import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for verifying login functionality using Data-Driven Testing (DDT).
 * This class extends the Base class to utilize its setup and teardown methods.
 */
public class LoginTestDDT extends Base {

    /**
     * Verifies the login functionality using data-driven testing (DDT).
     *
     * @param email    the email address used for logging in
     * @param password the password associated with the email
     * @param exp      the expected outcome of the login attempt ("Valid" or "Invalid")
     *
     * @throws AssertionError if the actual outcome does not match the expected outcome
     */
    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = {"Monkey", "Master"})
    public void verify_loginDDT(String email, String password, String exp) {
        logger.info("**** Starting LoginPageDDT test with email: " + email + " ****"); // Log the start of the test

        try {
            // Initialize the HomePage object to access home page elements
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount(); // Click on the 'My Account' link
            hp.clickLogin(); // Click on the 'Login' link

            // Initialize the LoginPage object to access login page elements
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email); // Set the email for login
            lp.setPwd(password); // Set the password for login
            lp.clickLogin(); // Click on the 'Login' button

            // Initialize the MyAccountPage object to verify if the login was successful
            MyAccountPage mp = new MyAccountPage(driver);
            boolean targetPage = mp.isMyAccountPageExists(); // Check if the 'My Account' page is displayed

            // Validate login results based on expected outcome
            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    logger.info("**** Login successful, validating logout ****");
                    mp.clickLogout(); // Logout after successful login
                    Assert.assertTrue(true, "Login successful, test passed."); // Assert login success
                } else {
                    logger.error("**** Login failed for valid credentials ****");
                    Assert.assertTrue(false, "Login failed for valid credentials."); // Assert failure for valid credentials
                }
            } else if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    logger.error("**** Login successful for invalid credentials, test failed ****");
                    mp.clickLogout(); // Logout after unexpected success
                    Assert.assertTrue(false, "Login successful for invalid credentials."); // Assert failure for invalid credentials
                } else {
                    logger.info("**** Login failed as expected for invalid credentials ****");
                    Assert.assertTrue(true, "Login failed as expected for invalid credentials."); // Assert expected failure
                }
            }
        } catch (Exception e) {
            logger.error("**** Exception occurred during login test: " + e.getMessage() + " ****");
            Assert.fail("An exception occurred: " + e.getMessage()); // Fail the test on exception
        }

        logger.info("**** Finished LoginPageDDT test for email: " + email + " ****"); // Log the end of the test
    }
}
