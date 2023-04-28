package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[@class='card-body']")
    List<WebElement> cartItemList;

    @FindBy(xpath = "//div[@class='card-body']")
    List<WebElement> products;

    By itemsList = By.xpath("//div[@class='card-body']");
    By productNamePath = By.xpath("//h5");
    By addToCartPath = By.xpath("//button[contains(text(),'Add To Cart')]");
    By cartSuccessMsg =By.xpath("//div[@id='toast-container']");


    public List<WebElement> getProductList() {
        waitForElementsToLoad(itemsList);
        return cartItemList;

    }

    public WebElement getProductName(String productName) {
        WebElement prod = products.stream().filter(product -> product.findElement(productNamePath).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public CartPage addProductTocart(String productName) {
        WebElement prod = getProductName(productName);
        prod.findElement(addToCartPath).click();
        waitforInvisibilityofElement(cartSuccessMsg);
        CartPage cartPage  =goToCart();
        return cartPage;



    }

}
