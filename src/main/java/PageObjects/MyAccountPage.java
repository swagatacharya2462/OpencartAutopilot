package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * MyAccountPage represents the user's account page in the application.
 * It contains elements and actions related to the user's account,
 * such as checking if the account page is displayed and logging out.
 */
public class MyAccountPage extends BasePage {

    // Locate the heading message indicating the My Account section
    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    // Locate the Logout link on the My Account page
    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement lnkLogout;

    /**
     * Constructor for MyAccountPage.
     * Initializes the page with the provided WebDriver instance.
     *
     * @param driver WebDriver instance to interact with the browser
     */
    public MyAccountPage(WebDriver driver) {
        super(driver); // Initialize the BasePage with the provided WebDriver instance
    }

    /**
     * Checks if the My Account page is displayed by verifying the heading.
     *
     * @return true if the My Account heading is displayed, false otherwise
     */
    public boolean isMyAccountPageExists() {
        try {
            return msgHeading.isDisplayed(); // Return true if the heading is displayed
        } catch (Exception e) {
            return false; // Return false if an exception occurs (element not found)
        }
    }

    /**
     * Clicks the Logout link to log the user out of their account.
     */
    public void clickLogout() {
        lnkLogout.click(); // Perform click action on the Logout link
    }
}
