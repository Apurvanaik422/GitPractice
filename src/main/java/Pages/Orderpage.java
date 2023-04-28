package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orderpage extends AbstractComponent {
    WebDriver driver;

    public Orderpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr//td[2]")
    List<WebElement> orderListAddedToCart;

    By orderListPath = By.xpath("//table[contains(@class,'table')]//tbody//tr//td[2]");

    public Boolean verifyproductAddedtocart(String productName) {
        waitForElementsToLoad(orderListPath);
        boolean flag = orderListAddedToCart.stream().anyMatch(orderList -> orderList.getText().equalsIgnoreCase(productName));
        return flag;
    }

}
