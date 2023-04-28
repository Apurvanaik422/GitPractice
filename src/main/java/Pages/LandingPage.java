package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(id="userEmail")
    public WebElement email;

    @FindBy(id="userPassword")
    public WebElement pwd;


    @FindBy(id="login")
    public WebElement loginBtn;

    @FindBy(xpath="//div[@id ='toast-container']")
    public WebElement errorMsg;

    public ProductCatalogPage loginToApplication(String username,String Password){
        email.sendKeys(username);
        pwd.sendKeys(Password);
        loginBtn.click();
        return new ProductCatalogPage(driver);
    }

    public void goToAppSite(String website){
        driver.get(website);
    }

    public String getErrorMessage(){
        waitForElementToBeClickableByWebElemnt(errorMsg);
        return errorMsg.getText();
    }
}
