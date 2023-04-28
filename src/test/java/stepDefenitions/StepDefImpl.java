package stepDefenitions;

import Pages.*;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefImpl extends BaseTest {


    public LandingPage landingPage;
    public ProductCatalogPage productCatalogPage;
    public CartPage cartPage;
    public  CheckOutPage checkOutPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on the Ecommerce Web Page")
    public void i_landed_on_the_Ecommorce_WebPage() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^an user enters the (.+) and (.+)$")
    public void an_user_enters_the_username_and_password(String username, String password) {
        productCatalogPage = landingPage.loginToApplication(username, password);

    }

    @When("^adds product to the cart (.+)$")
    public void adds_product_to_the_cart(String productName) {
        cartPage = productCatalogPage.addProductTocart(productName);
        Boolean cartFlag = cartPage.verifyProductAddedToCart(productName);
        Assert.assertTrue(cartFlag);
    }

    @When("clicks on checkout with product Selected")
    public void and_clicks_on_checkout_with_product_selected(){
        checkOutPage = cartPage.clickonBuyOrder();
        checkOutPage.fillingCheckOutDeatils("Ind","India");
        confirmationPage =checkOutPage.placeOrder();
    }

    @Then("{string} message will be displayed to the user")
    public void then_confirmationMsg_will_be_displayed_to_the_user(String confirmationMsg){
        String confirmationTxt = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmationTxt,confirmationMsg);
        System.out.println("test case Completed");

    }
}
