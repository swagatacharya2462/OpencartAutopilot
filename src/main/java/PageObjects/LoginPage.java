package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage represents the login page of the application.
 * It contains elements and actions related to user login,
 * including entering email, password, and submitting the login form.
 */
public class LoginPage extends BasePage {

    // Locate the email input field
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    // Locate the password input field
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPwd;

    // Locate the login button
    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    /**
     * Constructor for LoginPage.
     * Initializes the page with the provided WebDriver instance.
     *
     * @param driver WebDriver instance to interact with the browser
     */
    public LoginPage(WebDriver driver) {
        super(driver); // Initialize the BasePage with the provided WebDriver instance
    }

    /**
     * Enters the specified email into the email input field.
     *
     * @param email The email address to be entered
     */
    public void setEmail(String email) {
        txtEmail.sendKeys(email); // Type the email address into the email input field
    }

    /**
     * Enters the specified password into the password input field.
     *
     * @param pwd The password to be entered
     */
    public void setPwd(String pwd) {
        txtPwd.sendKeys(pwd); // Type the password into the password input field
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickLogin() {
        btnLogin.click(); // Perform click action on the login button
    }
}
