package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

    public static ExtentReports reportInitialization(){

        ExtentSparkReporter reporter =new ExtentSparkReporter(System.getProperty("user.dir")+"\\ExtentReports\\report.png");
        reporter.config().setReportName("Ecommorce Automation");
        reporter.config().setDocumentTitle("www.Ecommorce.com");
        ExtentReports extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Apurva Naik");
        extent.setSystemInfo("Environment","BTAT");
        return extent;
    }
}
