package Utilities;

import TestBase.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This class manages the generation and configuration of the Extent report during the test execution.
 * It implements the TestNG ITestListener interface to listen to the events of test execution and log the results.
 */
public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;  // Reporter for generating HTML report
    public ExtentReports extent;               // Main report object
    public ExtentTest test;                    // Represents a single test in the report
    String reportName;                         // Name of the report file

    /**
     * This method is invoked at the start of the test execution and initializes the Extent report configuration.
     *
     * @param testContext The context of the test, providing access to test parameters, included groups, etc.
     */
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";  // Generate unique report name

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", "Swagat Acharya");
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("br");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", String.join(", ", includedGroups));
        }
    }

    /**
     * This method is invoked when a test method passes successfully.
     *
     * @param result The result of the executed test method, providing information such as the method name, status, etc.
     */
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " executed successfully.");
    }

    /**
     * This method is invoked when a test method fails.
     * It logs the failure and attempts to capture a screenshot of the failure state.
     *
     * @param result The result of the failed test method, providing details such as the failure cause.
     */
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " execution failed.");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new Base().captureScreen(result.getName());
            if (imgPath != null && !imgPath.isEmpty()) {
                test.addScreenCaptureFromPath(imgPath); // Attach the screenshot
            } else {
                test.log(Status.INFO, "Screenshot capture failed or screenshot path is invalid.");
            }
        } catch (IOException e) {
            test.info("Failed to capture screenshot: " + e.getMessage());
        }
    }

    /**
     * This method is invoked when a test method is skipped.
     * It logs the skip reason and any associated exception message.
     *
     * @param result The result of the skipped test method, providing details such as the skip cause.
     */
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped.");
        test.log(Status.INFO, result.getThrowable() != null ? result.getThrowable().getMessage() : "No exception message available.");
    }

    /**
     * This method is invoked at the end of the test execution and finalizes the Extent report.
     * It attempts to automatically open the report in the browser.
     *
     * @param testContext The context of the test execution, providing details of all executed tests.
     */
    public void onFinish(ITestContext testContext) {
        extent.flush(); // Save the report

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
        File extentReport = new File(pathOfExtentReport);

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(extentReport.toURI());
            } else {
                System.out.println("Desktop is not supported. Please open the report manually: " + extentReport.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Failed to open report: " + e.getMessage());
        }
    }

    // Email functionality commented out; consider adding back if needed
//        try {
//            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + reportName);
//            ImageHtmlEmail email = new ImageHtmlEmail();
//            email.setDataSourceResolver(new DataSourceUrlResolver(url));
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
//            email.setSSLOnConnect(true);
//            email.setFrom("pavanoltraining@gmail.com"); // Sender
//            email.setSubject("Test Results");
//            email.setMsg("Please find Attached Report....");
//            email.addTo("pavankumar.busyqa@gmail.com"); // Receiver
//            email.attach(url, "extent report", "please check report...");
//            email.send(); // Send the email
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}
