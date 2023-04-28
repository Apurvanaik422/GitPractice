package TestComponents;

import Resources.ExtentReportsNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    public  ExtentReports extent = ExtentReportsNG.reportInitialization();
    public  ExtentTest test;
    public ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        thread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        thread.get().log(Status.PASS,"Test Case" + result.getMethod().getMethodName() + "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        thread.get().fail(result.getThrowable());
        WebDriver driver =null;

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            System.out.println("Failure 2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filePath = null;


        try {
            filePath = takeScreenshoot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        thread.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
       /* test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());*/


    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

    }
}
