package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    @FindBy(xpath = "(//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12'])[1]")
    WebElement macValue;
    @FindBy(xpath = "//span[normalize-space()='Add to Cart']")
    WebElement lnkAddToCart;
    @FindBy(xpath = "//span[normalize-space()='Shopping Cart']")
    WebElement lnkShoppingCart;
    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successMsg;

    /**
     * Constructor for BasePage.
     * Initializes the WebDriver and the PageFactory.
     *
     * @param driver WebDriver instance to be used for page interactions
     */
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductExists() {
        try {
            return macValue.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddToCart() {
        lnkAddToCart.click();
    }

    public boolean verifySuccessMsg(){
        try{
            return successMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickShoppingCart(){
        lnkShoppingCart.click();
    }
}
