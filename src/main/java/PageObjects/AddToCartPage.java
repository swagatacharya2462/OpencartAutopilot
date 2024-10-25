package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddToCartPage extends BasePage {
    @FindBy(xpath = "//div[@class='input-group btn-block']/input")
    WebElement lnkProductQuantity;
    @FindBy(xpath = "//a[normalize-space()='Estimate Shipping & Taxes']")
    WebElement lnkShippingTaxBtn;
    @FindBy(xpath = "//select[@id='input-country']")
    WebElement lnkCountry;
    @FindBy(xpath = "//select[@id='input-zone']")
    WebElement lnkState;
    @FindBy(xpath = "//input[@id='input-postcode']")
    WebElement lnkPostcode;
    @FindBy(xpath = "//button[@id='button-quote']")
    WebElement btnQuote;
    @FindBy(xpath = "//input[@name='shipping_method']")
    WebElement radioShippingMethod;
    @FindBy(xpath = "//input[@id='button-shipping']")
    WebElement btnShipping;
    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement applySuccessMsg;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement availableMsg;
    @FindBy(xpath = "//a[normalize-space()='Continue Shopping']")
    WebElement lnkContinueShopping;
    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement btnCheckout;

    /**
     * Constructor for BasePage.
     * Initializes the WebDriver and the PageFactory.
     *
     * @param driver WebDriver instance to be used for page interactions
     */
    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    public void clickProductQuantity(String value) {
        lnkProductQuantity.clear();
        lnkProductQuantity.sendKeys(value);
    }

    public void clickShippingTax() {
        lnkShippingTaxBtn.click();
    }

    public void clickCountry(String country) {
        Select selectCountry = new Select(lnkCountry);
        selectCountry.selectByVisibleText(country);
    }

    public void clickState(String state) {
        Select selectState = new Select(lnkState);
        selectState.selectByVisibleText(state);
    }

    public void clickPostcode(String postcode) {
        lnkPostcode.sendKeys(postcode);
    }

    public void clickQuotes() {
        btnQuote.click();
    }

    public void clickShippingMethod() {
        radioShippingMethod.click();
    }

    public void clickApplyShipping() {
        btnShipping.click();
    }

    public boolean verifyApplySuccessMsg() {
        try {
            return applySuccessMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyAvailableMsg(){
        return availableMsg.isDisplayed();
    }

    public void clickContinueShipping(){
        lnkContinueShopping.click();
    }

    public void clickCheckout(){
        btnCheckout.click();
    }
}
