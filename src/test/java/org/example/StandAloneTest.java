package org.example;

import Pages.*;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StandAloneTest extends BaseTest {
    String productName="ZARA COAT 3";
    @Test(groups={"purchase"},dataProvider = "getDataJson")
    public  void addToCartTest(HashMap<String,String> input) throws InterruptedException, IOException {

        ProductCatalogPage productCatalogPage = landingPage.loginToApplication(input.get("email"),input.get("password"));
        List<WebElement> itemList =productCatalogPage.getProductList();
        CartPage cartPage =productCatalogPage.addProductTocart(input.get("productName"));
        Boolean cartFlag =cartPage.verifyProductAddedToCart(input.get("productName"));
        Assert.assertTrue(cartFlag);
        CheckOutPage checkOutPage = cartPage.clickonBuyOrder();
        checkOutPage.fillingCheckOutDeatils("Ind","India");
        ConfirmationPage confirmationPage =checkOutPage.placeOrder();
        String confirmationTxt = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmationTxt,"THANKYOU FOR THE ORDER.");
        System.out.println("test case Completed");
    }

    @Test(dependsOnMethods = {"addToCartTest"})
    public void verifyOrderPlacedTest(){
        ProductCatalogPage productCatalogPage = landingPage.loginToApplication("apurvanaik42@academy.com","Improve@1234");
        Orderpage orderpage = productCatalogPage.clickOrderLink();
        Boolean flag =orderpage.verifyproductAddedtocart(productName);
        Assert.assertTrue(flag);
    }


    @DataProvider
    public Object[][] getData(){
        Object[][] data ={{"apurvanaik42@academy.com","Improve@1234","ZARA COAT 3"},{"apurvanaik42@academy.com","Improve@1234","ZARA COAT 3"}};
        return data;
    }

    @DataProvider
    public Object[][] getDataHash(){

        HashMap<String,String> map =new HashMap<String,String>();
        //Set 1 data
        map.put("email","apurvanaik42@academy.com");
        map.put("password","Improve@1234");
        map.put("productName","ZARA COAT 3");

        //Set 2 data
        HashMap<String,String> map1 =new HashMap<String,String>();
        map1.put("email","apurvanaik42@academy.com");
        map1.put("password","Improve@1234");
        map1.put("productName","ZARA COAT 3");

        return new Object[][] {{map},{map1}};
    }

    @DataProvider
    public Object[][] getDataJson() throws IOException{
        List<HashMap<String,String>> data =readData();
        return new Object [][] {{data.get(0)},{data.get(1)}};

    }
}

