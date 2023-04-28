package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;
    public  ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

By confirmationMsgPath =By.xpath("//h1[contains(text(),' Thankyou for the order. ')]");

        public String getConfirmationMessage(){
            WebElement confirmMsgElement = waitForElementToBeClickable(confirmationMsgPath);
            String confirmationMsg =confirmMsgElement.getText();
            return confirmationMsg;
        }

    }

