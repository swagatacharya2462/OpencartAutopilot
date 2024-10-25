package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * RegistrationPage represents the user registration page of the application.
 * It contains elements and actions related to user registration,
 * including filling out the registration form and submitting it.
 */
public class RegistrationPage extends BasePage {

    // Locate the input field for the first name
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;

    // Locate the input field for the last name
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    // Locate the input field for the email address
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    // Locate the input field for the telephone number
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    // Locate the input field for the password
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    // Locate the input field for confirming the password
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    // Locate the "Yes" radio button for accepting marketing communications
    @FindBy(xpath = "//label[normalize-space()='Yes']")
    WebElement radioYes;

    // Locate the checkbox for agreeing to the terms and conditions
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    // Locate the Continue button to submit the registration form
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    // Locate the confirmation message displayed after successful registration
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    /**
     * Constructor for RegistrationPage.
     * Initializes the page with the provided WebDriver instance.
     *
     * @param driver WebDriver instance to interact with the browser
     */
    public RegistrationPage(WebDriver driver) {
        super(driver); // Initialize the BasePage with the provided WebDriver instance
    }

    /**
     * Enters the specified first name into the first name input field.
     *
     * @param fname The first name to be entered
     */
    public void setTxtFirstName(String fname) {
        txtFirstName.sendKeys(fname); // Type the first name into the input field
    }

    /**
     * Enters the specified last name into the last name input field.
     *
     * @param lname The last name to be entered
     */
    public void setTxtLastName(String lname) {
        txtLastName.sendKeys(lname); // Type the last name into the input field
    }

    /**
     * Enters the specified email into the email input field.
     *
     * @param email The email address to be entered
     */
    public void setEmail(String email) {
        txtEmail.sendKeys(email); // Type the email address into the input field
    }

    /**
     * Enters the specified telephone number into the telephone input field.
     *
     * @param tel The telephone number to be entered
     */
    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel); // Type the telephone number into the input field
    }

    /**
     * Enters the specified password into the password input field.
     *
     * @param pwd The password to be entered
     */
    public void setTxtPassword(String pwd) {
        txtPassword.sendKeys(pwd); // Type the password into the input field
    }

    /**
     * Enters the specified password into the confirm password input field.
     *
     * @param pwd The password to be confirmed
     */
    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd); // Type the confirm password into the input field
    }

    /**
     * Clicks the "Yes" radio button to accept marketing communications.
     */
    public void setRadioYes() {
        radioYes.click(); // Perform click action on the "Yes" radio button
    }

    /**
     * Clicks the checkbox to agree to the terms and conditions.
     */
    public void setChkPolicy() {
        chkPolicy.click(); // Perform click action on the checkbox
    }

    /**
     * Submits the registration form by clicking the Continue button.
     */
    public void setBtnContinue() {
        btnContinue.submit(); // Submit the form using the Continue button
    }

    /**
     * Retrieves the confirmation message displayed after successful registration.
     *
     * @return The confirmation message as a String, or an error message if an exception occurs
     */
    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText(); // Return the confirmation message text
        } catch (Exception e) {
            return e.getMessage(); // Return the exception message if an error occurs
        }
    }
}
