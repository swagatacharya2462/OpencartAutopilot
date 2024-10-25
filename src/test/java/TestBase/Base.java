package TestBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Base class for setting up the WebDriver and managing test configurations.
 * This class contains methods for initializing the WebDriver, handling random
 * string generation, and capturing screenshots during tests.
 */
public class Base {

    public static WebDriver driver; // WebDriver instance for browser automation
    public Logger logger; // Logger instance for logging messages
    public Properties prop; // Properties instance for loading configuration

    String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // Characters for random string generation
    String NUMERIC = "0123456789"; // Characters for random number generation
    Random RANDOM = new Random(); // Random object for generating random values

    /**
     * Setup method that initializes the WebDriver based on provided parameters.
     * It loads configuration properties, sets timeouts, and navigates to the specified URL.
     *
     * @param os The operating system (default is "Windows")
     * @param br The browser to use for testing (default is "chrome")
     * @throws IOException If there is an error reading the configuration file
     */
    @BeforeClass(groups = {"Master", "Sanity", "Regression", "Monkey", "Gorilla", "Smoke"})
    @Parameters({"os", "br"})
    public void setUp(@Optional("Windows") String os, @Optional("chrome") String br) throws IOException {
        logger = LogManager.getLogger(this.getClass()); // Initialize logger for the current class
        logger.info("Initializing WebDriver setup...");

        try {
            // Load configuration properties from the specified file
            FileReader file = new FileReader("./src/test/resources/config.properties");
            prop = new Properties();
            prop.load(file);
            logger.info("Loaded configuration properties from config.properties");

            // Initialize the WebDriver based on the provided browser name
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    logger.info("Launching Chrome browser...");
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    logger.info("Launching Edge browser...");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    logger.info("Launching Firefox browser...");
                    break;
                default:
                    logger.error("Invalid browser name: " + br); // Log error if the browser name is invalid
                    return;
            }

            // Clear all cookies and set timeouts
            driver.manage().deleteAllCookies();
            logger.info("Deleted all cookies.");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            logger.info("Set implicit wait and page load timeout.");

            // Maximize the browser window and navigate to the specified URL
            driver.manage().window().maximize();
            logger.info("Maximized browser window.");
            driver.get(prop.getProperty("url2")); // Load the URL from properties file
            logger.info("Navigated to URL: " + prop.getProperty("url2"));
        } catch (Exception e) {
            logger.error("Error during WebDriver setup: " + e.getMessage()); // Log any setup errors
        }
    }

    /**
     * Teardown method that closes the browser after the test execution.
     */
    @AfterClass(groups = {"Master", "Sanity", "Regression", "Monkey", "Gorilla", "Smoke"})
    public void tearDown() {
        if (driver != null) {
            driver.close(); // Close the browser if the driver is not null
            logger.info("Closed the browser.");
        } else {
            logger.warn("Driver is null; unable to close the browser."); // Log a warning if the driver is null
        }
    }

    /**
     * Generates a random string of the specified length using alphabetic characters.
     *
     * @param length The length of the random string to generate
     * @return A random string of the specified length
     */
    public String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()))); // Append random characters to the string
        }
        String randomStr = sb.toString();
        logger.debug("Generated random string: " + randomStr); // Log the generated string
        return randomStr;
    }

    /**
     * Generates a random number of the specified length.
     *
     * @param length The length of the random number to generate
     * @return A random number as a string of the specified length
     */
    public String randomNumber(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(NUMERIC.charAt(RANDOM.nextInt(NUMERIC.length()))); // Append random digits to the string
        }
        String randomNum = sb.toString();
        logger.debug("Generated random number: " + randomNum); // Log the generated number
        return randomNum;
    }

    /**
     * Generates a random alphanumeric string in the format "xxx@yyy",
     * where "xxx" is a random string and "yyy" is a random number.
     *
     * @return A random alphanumeric string
     */
    public String randomAlphaNumeric() {
        String randomAlphanumeric = randomString(3) + "@" + randomNumber(3); // Combine random string and number
        logger.debug("Generated random alphanumeric: " + randomAlphanumeric); // Log the generated alphanumeric string
        return randomAlphanumeric;
    }

    /**
     * Captures a screenshot of the current browser window.
     * The screenshot is saved in the specified directory with a timestamp.
     *
     * @param tname The name of the test (used for the screenshot file name)
     * @return The file path of the saved screenshot
     * @throws IOException If there is an error saving the screenshot
     */
    public String captureScreen(String tname) throws IOException {
        // Check if driver is null
        if (driver == null) {
            logger.error("WebDriver is not initialized. Cannot capture screenshot.");
            return null;
        }
        // Create a timestamp for the screenshot filename
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        // Capture the screenshot and save it as a file
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String screenshotsDir = System.getProperty("user.dir") + "\\screenshots\\";
        File dir = new File(screenshotsDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String targetFilePath = screenshotsDir + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile); // Rename the captured file to the target file name

        return targetFilePath; // Return the file path of the saved screenshot
    }
}
