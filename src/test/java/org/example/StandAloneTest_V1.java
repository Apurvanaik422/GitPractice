package org.example;

import Pages.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest_V1 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor exceutor = (JavascriptExecutor)driver ;
        Actions action =new Actions(driver);

        LandingPage LP =new LandingPage(driver);
        LP.email.sendKeys("apurvanaik42@academy.com");
        LP.pwd.sendKeys("Improve@1234");
        LP.loginBtn.click();

       List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='card-body']")));

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
        WebElement prod =products.stream().filter(product->product.findElement(By.xpath("//h5")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);


        WebElement addTocart = prod.findElement(By.xpath("//button[contains(text(),'Add To Cart')]"));
        addTocart.click();

        //Waiting for Added to cart message

        Boolean flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));

        if(flag =true) {
            WebElement cartLink = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
            cartLink.click();
        }

        List<WebElement> cartList =driver.findElements(By.xpath("//div[@class='cart']//ul//div[@class='cartSection']//h3"));
        Boolean cartFlag =cartList.stream().anyMatch(cartBuy->cartBuy.getText().equals("ZARA COAT 3"));
        Assert.assertTrue(cartFlag);

        WebElement buyBgtn = driver.findElement(By.xpath("//button[contains(text(),'Buy Now')]"));
        buyBgtn.click();

        WebElement countryChBx =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Country']")));
        exceutor.executeScript("window.scrollBy(0,500)");
        countryChBx.sendKeys("Ind");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class,'ta-results')]")));
        List<WebElement> countryList =driver.findElements(By.xpath("//section[contains(@class,'ta-results')]//button//span"));
        WebElement contry =countryList.stream().filter(country->country.getText().equals("India")).findFirst().orElse(null);
        contry.click();

        WebElement placeOrder  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='actions']//a")));
        exceutor.executeScript("window.scrollBy(0,900)");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='actions']//a")));

      try{
          action.moveToElement(placeOrder).click().build().perform();
      }catch (ElementClickInterceptedException e){
          action.moveToElement(placeOrder).click().build().perform();
      }



        WebElement successmsg =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),' Thankyou for the order. ')]")));
       String successtxt = successmsg.getText();
       Assert.assertEquals(successtxt,"THANKYOU FOR THE ORDER.");
       System.out.println("test case Completed");
    }}

