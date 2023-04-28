package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[@class='cart']//ul//div[@class='cartSection']//h3")
    List<WebElement> cartList;

    @FindBy(xpath ="//button[contains(text(),'Buy Now')]")
    WebElement buyBtn;

    public Boolean verifyProductAddedToCart(String productName){
        Boolean flag =cartList.stream().anyMatch(cartList->cartList.getText().equals(productName));
        return flag;


    }
    public CheckOutPage clickonBuyOrder(){
        buyBtn.click();
        return  new CheckOutPage(driver);

    }
}
