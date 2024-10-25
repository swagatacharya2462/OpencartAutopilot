package TestCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for verifying the login functionality of the application.
 * This class extends the Base class to utilize its setup and teardown methods.
 */
public class LoginPageTest extends Base {

    /**
     * Test method to verify account login functionality.
     * It checks if the user can log in with valid credentials.
     */
    @Test(groups = {"Sanity", "Master"})
    public void verify_account_login() {
        logger.info("**** Starting Login Test ****"); // Log the start of the test

        try {
            // Initialize the HomePage object to access home page elements
            HomePage hp = new HomePage(driver);
            logger.info("Clicking on My Account link...");
            hp.clickMyAccount(); // Click on the 'My Account' link
            logger.info("Clicked on MyAccount Link");

            logger.info("Clicking on Login link...");
            hp.clickLogin(); // Click on the 'Login' link
            logger.info("Clicked on Login Link");

            // Initialize the LoginPage object to access login page elements
            LoginPage lp = new LoginPage(driver);
            logger.info("Providing customer email and password");

            // Set the email and password from properties file
            lp.setEmail(prop.getProperty("email"));
            logger.info("Email provided: {}", prop.getProperty("email")); // Log the email provided
            lp.setPwd(prop.getProperty("password"));
            logger.info("Password provided: {}", prop.getProperty("password")); // Log the password provided

            logger.info("Clicking on Login button...");
            lp.clickLogin(); // Click on the 'Login' button
            logger.info("Clicked on Login button");

            // Initialize the MyAccountPage object to verify if the login was successful
            MyAccountPage mp = new MyAccountPage(driver);
            logger.info("Checking if My Account page exists...");
            Boolean targetPage = mp.isMyAccountPageExists(); // Check if the 'My Account' page is displayed

            // Assert that the 'My Account' page is displayed, indicating a successful login
            Assert.assertEquals(targetPage, true, "Login Failed");
            Assert.assertTrue(targetPage); // Log success if the login was successful
            logger.info("Login successful, My Account page is displayed.");

        } catch (Exception e) {
            // Log error and fail the test if an exception occurs
            logger.error("Test failed due to an exception: ", e);
            Assert.fail("Login test failed: " + e.getMessage());
        } finally {
            logger.info("**** Finished Login Test ****"); // Log the end of the test
        }
    }
}
