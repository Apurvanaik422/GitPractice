package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;
    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryChBx;

    @FindBy(xpath = "//section[contains(@class,'ta-results')]//button//span")
    List<WebElement> searchedCountryList;

    By countryChBxpath = By.xpath("//input[@placeholder='Select Country']");
    By searchedCountryListPath =By.xpath("//section[contains(@class,'ta-results')]");
    By placeOrderPath =By.xpath("//div[@class='actions']//a");


    public void fillingCheckOutDeatils(String countryCode,String countryText){
        waitForElementToBeClickable(countryChBxpath);
        windowScrolling();
        countryChBx.sendKeys(countryCode);
        waitForElementToBeClickable(searchedCountryListPath);
       WebElement selectedCountry = searchedCountryList.stream().filter(country->country.getText().equals(countryText)).findFirst().orElse(null);
        selectedCountry.click();

    }

    public ConfirmationPage placeOrder(){
       WebElement ele =waitForElementToBeClickable(placeOrderPath);
        Actions actions =new Actions(driver);
        try{
            actions.moveToElement(ele).click().build().perform();
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
            actions.moveToElement(ele).click().build().perform();
        }
        return new ConfirmationPage(driver);

    }
}
