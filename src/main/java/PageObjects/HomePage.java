package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomePage represents the homepage of the application.
 * It contains elements and actions related to the homepage,
 * such as navigating to the account, registering, and logging in.
 */
public class HomePage extends BasePage {

    // Locate the "My Account" link on the homepage
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;
    // Locate the "Register" link on the homepage
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;
    // Locate the "Login" link on the homepage
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement lnkLogin;
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement lnkSearch;
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement lnkEnter;

    // WebDriver instance inherited from BasePage
    public HomePage(WebDriver driver) {
        super(driver); // Initialize the BasePage with the provided WebDriver instance
    }

    /**
     * Clicks on the "My Account" link.
     */
    public void clickMyAccount() {
        lnkMyAccount.click(); // Perform click action on "My Account"
    }

    /**
     * Clicks on the "Register" link.
     */
    public void clickRegister() {
        lnkRegister.click(); // Perform click action on "Register"
    }

    /**
     * Clicks on the "Login" link.
     */
    public void clickLogin() {
        lnkLogin.click(); // Perform click action on "Login"
    }

    public void sendSearch(String value) {
        lnkSearch.sendKeys(value);
    }

    public void clickEnter() {
        lnkEnter.click();
    }
}
