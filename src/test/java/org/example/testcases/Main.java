package org.example.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Main {

/*
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setDocumentTitle("Automation Test Results");
    htmlReporter.config().setReportName("My Report");
    extent.attachReporter(htmlReporter);

    ExtentTest test = extent.createTest("Test Name", "Test Description");
    test.log(Status.INFO, "Test Step 1");
    test.log(Status.PASS, "Test Step 2 Passed");
    test.log(Status.FAIL, "Test Step 3 Failed");

    String screenshotPath = "C:\\Screenshots\\screenshot.png";
    test.fail("Test Step 3 Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    extent.flush();
*/

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}