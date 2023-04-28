package org.example;

import Pages.CartPage;
import Pages.ProductCatalogPage;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrormessageScenariosTest extends BaseTest {

    @Test(groups={"ErrorHandling"})
    public void errorMsgValidation() throws IOException {
        landingPage.loginToApplication("apurvanaik42@gmail.com","Terst@1234");
        String errorMsg =landingPage.getErrorMessage();
        System.out.println(errorMsg);
        Assert.assertEquals(errorMsg,"Incorrect email or password.");

    }

    @Test(retryAnalyzer = Retry.class)
    public void productCartvalidation(){
        String productName ="ZARA COAT 3";
        ProductCatalogPage productCatalogPage = landingPage.loginToApplication("apurvanaik42@academy.com","Improve@1234");
        productCatalogPage.getProductList();
        CartPage cartPage =productCatalogPage.addProductTocart(productName);
        Boolean flag =cartPage.verifyProductAddedToCart("ZARA COAT 38");
        Assert.assertTrue(flag);
    }
}
