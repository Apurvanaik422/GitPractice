package AbstractComponents;

import Pages.CartPage;
import Pages.Orderpage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
    WebDriver driver;

public AbstractComponent(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver,this);

}
@FindBy(xpath = "//button[contains(text(),' ORDERS')]")
        WebElement ordersLink;

By cartPath =By.xpath("//button[@routerlink='/dashboard/cart']");
    public void waitForElementsToLoad(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(findBy));
    }

    public WebElement waitForElementToBeClickable(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ele =wait.until(ExpectedConditions.elementToBeClickable(findBy));
        return ele;
    }
    public WebElement waitForElementToBeClickableByWebElemnt(WebElement ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element =wait.until(ExpectedConditions.elementToBeClickable(ele));
        return element;
    }

    public void waitforInvisibilityofElement(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));

    }

    public void windowScrolling(){
        JavascriptExecutor exceutor =(JavascriptExecutor)driver;
        exceutor.executeScript("window.scrollBy(0,500)");
    }

    public CartPage goToCart(){
        WebElement cartLink = driver.findElement(cartPath);
        cartLink.click();
        return new CartPage(driver);
    }

    public Orderpage clickOrderLink(){
        ordersLink.click();
        return new Orderpage(driver);
    }
}
