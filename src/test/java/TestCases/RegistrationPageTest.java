package TestCases;

import PageObjects.HomePage;
import PageObjects.RegistrationPage;
import TestBase.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for verifying the account registration functionality.
 * This class extends the Base class to utilize its setup and teardown methods.
 */
public class RegistrationPageTest extends Base {

    /**
     * Test method to verify account registration.
     * This method tests the registration process by filling out the registration form and checking the confirmation message.
     */
    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration() {
        logger.info("**** Starting Registration Test ****"); // Log the start of the test

        try {
            // Initialize the HomePage object to access home page elements
            HomePage hp = new HomePage(driver);
            logger.info("Clicking on My Account link...");
            hp.clickMyAccount(); // Click on the 'My Account' link
            logger.info("Clicked on MyAccount Link");

            logger.info("Clicking on Register link...");
            hp.clickRegister(); // Click on the 'Register' link
            logger.info("Clicked on Register Link");

            // Initialize the RegistrationPage object to access registration page elements
            RegistrationPage rp = new RegistrationPage(driver);
            logger.info("Providing customer details...");

            // Generate and set random user details for registration
            String firstName = randomString(5).toUpperCase(); // Generate a random first name
            logger.info("First Name: {}", firstName);
            rp.setTxtFirstName(firstName); // Set the first name in the registration form

            String lastName = randomString(5).toUpperCase(); // Generate a random last name
            logger.info("Last Name: {}", lastName);
            rp.setTxtLastName(lastName); // Set the last name in the registration form

            String email = randomString(5) + "@gmail.com"; // Generate a random email address
            logger.info("Email: {}", email);
            rp.setEmail(email); // Set the email in the registration form

            String telephone = randomNumber(10); // Generate a random telephone number
            logger.info("Telephone: {}", telephone);
            rp.setTelephone(telephone); // Set the telephone number in the registration form

            String password = randomAlphaNumeric(); // Generate a random password
            logger.info("Password: {}", password);
            rp.setTxtPassword(password); // Set the password in the registration form
            rp.setConfirmPassword(password); // Confirm the password

            // Select newsletter subscription and accept terms and conditions
            logger.info("Selecting 'Yes' for newsletter subscription and accepting terms and conditions...");
            rp.setRadioYes(); // Select the 'Yes' radio button for the newsletter
            rp.setChkPolicy(); // Check the terms and conditions checkbox
            rp.setBtnContinue(); // Click the 'Continue' button
            logger.info("Clicked on Continue button");

            // Validate the confirmation message after successful registration
            logger.info("Validating expected confirmation message...");
            String confMsg = rp.getConfirmationMsg(); // Get the confirmation message
            Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Confirmation message does not match expected value."); // Assert confirmation message

            logger.info("Test Passed: {}", confMsg); // Log that the test passed with the confirmation message
        } catch (Exception e) {
            logger.error("Test failed due to an exception: ", e); // Log any exceptions that occur
            Assert.fail("Registration test failed: " + e.getMessage()); // Fail the test with the exception message
        } finally {
            logger.info("**** Finished Registration Test ****"); // Log the end of the test
        }
    }
}
