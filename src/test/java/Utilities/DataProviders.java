package Utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

/**
 * This class provides test data for login functionality using TestNG's DataProvider.
 * The data is fetched from an Excel file containing login information.
 */
public class DataProviders {

    /**
     * DataProvider method to supply login data for testing.
     * The method reads login credentials from an Excel file and returns a 2D array of strings.
     *
     * @return A 2D array containing login data: email, password, and expected outcome.
     * @throws IOException if there is an error reading the Excel file.
     */
    @DataProvider(name = "loginData")
    public String[][] getData() throws IOException {
        // Define the path to the Excel file containing the login data
        String path = ".\\testData\\Opencart_Login_Information.xlsx";

        // Create an instance of ExcelUtility to handle Excel operations
        ExcelUtility xlutil = new ExcelUtility(path);

        // Get the total number of rows and columns in the specified sheet
        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        // Initialize a 2D array to hold the login data
        String[][] loginData = new String[totalRows][totalCols];

        // Loop through the rows of the Excel sheet to read the login data
        for (int i = 1; i <= totalRows; i++) {
            loginData[i - 1][0] = xlutil.getCellData("Sheet1", i, 0); // Email
            loginData[i - 1][1] = xlutil.getCellData("Sheet1", i, 1); // Password
            loginData[i - 1][2] = xlutil.getCellData("Sheet1", i, 2); // Expected outcome (Valid/Invalid)
        }

        // Return the populated 2D array containing the login data
        return loginData;
    }
}
