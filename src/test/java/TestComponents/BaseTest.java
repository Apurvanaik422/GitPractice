package TestComponents;

import Pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public  WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor executor;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalProperties.properties");
        prop.load(fis);

        //Added Comments for Base Class
        System.out.println("Apurva");
        System.out.println("Naik");

        System.out.println("Ajinkya");
        System.out.println("Naik");


        String browserName =System.getProperty("Browser")!=null ? System.getProperty("Browser") :  prop.getProperty("Browser");
        //String browserName = prop.getProperty("Browser");


        if (browserName.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options =new ChromeOptions();
            if(browserName.contains("Headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browserName.equals("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        executor = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        return driver;

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToAppSite("https://rahulshettyacademy.com/client/");
        return new LandingPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();

    }

    //Data Reader from Json

    public List<HashMap<String, String>> readData() throws IOException {

//Convert Json to String
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\PurchaseOrder.json"), StandardCharsets.UTF_8);

        //Convert String to List of HashMap through JackSon Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;
    }

    public String takeScreenshoot(String testName, WebDriver driver) throws IOException {

        TakesScreenshot screenShoot =(TakesScreenshot)driver;
       File source = screenShoot.getScreenshotAs(OutputType.FILE);
       String filePath =System.getProperty("user.dir")+"\\ExtentReport\\img.png";
       FileUtils.copyFile(source,new File(filePath));
       return filePath;

    }
}

