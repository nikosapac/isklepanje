package sklepanjeTesti;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTestMap = new ThreadLocal<ExtentTest>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get();
    }
/*
    public static synchronized void endTest() {
        extentTestMap.get().getParent().info("Test completed");
        extentTestMap.remove();
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = ExtentReportManager.getExtentReports().createTest(testName);
        extentTestMap.set(test);
        return test;
    }
    */
}

