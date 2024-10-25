package TestCases;

import PageObjects.HomePage;
import PageObjects.SearchPage;
import TestBase.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for verifying the product search functionality of the application.
 * This class extends the Base class to utilize its setup and teardown methods.
 */
public class SearchPageTest extends Base {

    /**
     * Test method to verify that a product is displayed on the Search Page.
     * It checks if the product is available when searched.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    @Test(groups = {"Gorilla", "Master"})
    public void verifyProductDisplay() throws InterruptedException {
        logger.info("**** Starting Product Search Test ****");

        try {
            // Retrieve product name from properties file
            String productName = prop.getProperty("productName");
            logger.info("Product to be searched: " + productName);

            // Initialize HomePage object to access home page elements
            HomePage hp = new HomePage(driver);
            logger.info("Searching for product '" + productName + "'...");

            // Enter the search term and press Enter
            hp.sendSearch(productName);
            logger.info("Search term entered: " + productName);
            hp.clickEnter();
            logger.info("Search submitted");

            // Wait for the search results to load
            logger.info("Waiting for search results to load...");
            waitForFiveSeconds();

            // Initialize SearchPage object to access search page elements
            SearchPage sp = new SearchPage(driver);
            logger.info("Verifying if the product '" + productName + "' exists in the search results");

            // Check if the target product is displayed
            Boolean targetProduct = sp.isProductExists();
            logger.info("Product existence check result: " + targetProduct);

            // Assert that the product is displayed
            Assert.assertEquals(targetProduct, true, "Product not found: " + productName);
            Assert.assertTrue(targetProduct);
            logger.info("Product '" + productName + "' is displayed in the search results");

        } catch (Exception e) {
            // Log error and fail the test in case of exception
            logger.error("Test failed due to an exception: ", e);
            Assert.fail("Product search test failed: " + e.getMessage());

        } finally {
            logger.info("**** Finished Product Search Test ****");
        }
    }

    private void waitForFiveSeconds() throws InterruptedException {
        Thread.sleep(5000);
    }
}
