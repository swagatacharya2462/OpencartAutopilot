package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage is a base class for all page objects.
 * It initializes WebDriver and allows for the use of PageFactory
 * to manage web elements on the page.
 */
public class BasePage {

    // Instance of WebDriver for interacting with the browser
    WebDriver driver;

    /**
     * Constructor for BasePage.
     * Initializes the WebDriver and the PageFactory.
     *
     * @param driver WebDriver instance to be used for page interactions
     */
    public BasePage(WebDriver driver) {
        this.driver = driver; // Assigning the passed WebDriver instance to the class variable
        PageFactory.initElements(driver, this); // Initializing elements annotated with @FindBy in the page class
    }
}
