package TestCases;

import PageObjects.AddToCartPage;
import PageObjects.HomePage;
import PageObjects.SearchPage;
import TestBase.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for verifying the "Add to Cart" functionality.
 * It checks if a product can be added to the cart after being searched.
 */
public class AddToCartPageTest extends Base {

    /**
     * Test method to search for a product and add it to the cart.
     * It checks if the product is available, then attempts to add it to the cart.
     */
    @Test(priority = 1, groups = {"Smoke", "Master"})
    public void clickProductToCart() {
        logger.info("**** Starting Add to Cart Test ****");

        try {
            // Retrieve the product name from the properties file
            String productName = prop.getProperty("productName");
            logger.info("Attempting to search for product: " + productName);

            // Initialize HomePage object to access home page elements
            HomePage hp = new HomePage(driver);
            logger.info("HomePage initialized successfully");

            // Search for the product and submit the search
            hp.sendSearch(productName);
            hp.clickEnter();
            logger.info("Search submitted for product: " + productName);

            // Wait for the search results to load
            waitForTwoSeconds();

            // Initialize SearchPage object to access search page elements
            SearchPage sp = new SearchPage(driver);
            logger.info("SearchPage initialized successfully, verifying product existence");

            // Verify if the target product is displayed
            if (sp.isProductExists()) {
                logger.info("Product '" + productName + "' found in search results, attempting to add to cart");
                sp.clickAddToCart();
                logger.info("Product added to cart successfully");

                // Verify if the success message is displayed after adding to cart
                if (sp.verifySuccessMsg()) {
                    waitForTwoSeconds();
                    sp.clickShoppingCart();
                    logger.info("Navigated to the shopping cart page successfully");
                } else {
                    logger.error("Success message not displayed after adding product to cart");
                    Assert.fail("Failed to add the product to the cart");
                }
            } else {
                logger.error("Product '" + productName + "' not found in search results");
                Assert.fail("Product not displayed: " + productName);
            }

        } catch (InterruptedException e) {
            logger.error("Test interrupted unexpectedly: ", e);
            Thread.currentThread().interrupt();
            Assert.fail("Test interrupted due to an exception: " + e.getMessage());

        } catch (Exception e) {
            logger.error("An unexpected error occurred during the add to cart process: ", e);
            Assert.fail("Add to cart test failed due to an exception: " + e.getMessage());

        } finally {
            logger.info("**** Finished Add to Cart Test ****");
        }
    }

    /**
     * Helper method to wait for search results.
     * Handles InterruptedException and uses a Thread.sleep to simulate waiting.
     */
    private void waitForTwoSeconds() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     * Helper method to update cart details like quantity and shipping.
     * Reads shipping details from properties.
     */
    @Test(priority = 2, groups = {"Smoke", "Master"})
    public void updateCartDetails() {
        logger.info("**** Starting Cart Update Test ****");

        try {
            AddToCartPage ap = new AddToCartPage(driver);
            logger.info("AddToCartPage initialized successfully");

            String quantity = prop.getProperty("quantity");
            String country = prop.getProperty("country");
            String state = prop.getProperty("state");
            String postcode = prop.getProperty("postcode");

            ap.clickProductQuantity(quantity);
            logger.info("Product quantity updated to: " + quantity);

            ap.clickShippingTax();
            ap.clickCountry(country);
            ap.clickState(state);
            ap.clickPostcode(postcode);
            logger.info("Shipping details set - Country: " + country + ", State: " + state + ", Postcode: " + postcode);

            ap.clickQuotes();
            logger.info("Shipping quote retrieved successfully");

            ap.clickShippingMethod();
            ap.clickApplyShipping();
            ap.verifyApplySuccessMsg();
            logger.info("Shipping method applied successfully");

            boolean availableMsg = ap.verifyAvailableMsg();

            if (availableMsg) {
                ap.clickContinueShipping();
                logger.info("Continued shopping after updating cart details");
            } else {
                ap.clickCheckout();
                logger.info("Navigated to checkout after updating cart details");
            }

        } catch (Exception e) {
            logger.error("Error occurred while updating cart details: ", e);
            Assert.fail("Failed to update cart details due to an exception: " + e.getMessage());

        } finally {
            logger.info("**** Finished Cart Update Test ****");
        }
    }
}
