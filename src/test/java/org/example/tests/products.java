package org.example.tests;

import org.example.base.baseTest;
import org.example.config.Config;
import org.example.pages.cartPage;
import org.example.pages.catalogSearchPage;
import org.example.pages.loginPage;
import org.example.pages.productPage;
import org.example.utils.excelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class products extends baseTest {
    private static final Logger log = LoggerFactory.getLogger(products.class);
    public Config config = new Config();
    loginPage loginObj;
    productPage productObj;
    cartPage cartObj;
    catalogSearchPage catalogObj;
    excelUtils excel = new excelUtils();

    private static final String EXCEL_FILE_PATH = "src/main/resources/testData.xlsx";
    private static final String SHEET_NAME = "Sheet2"; // Your sheet name here
    String[] productData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 1);
    String shirtProduct = productData[0];


    @Test (priority = 1)
    public void testProductName() throws InterruptedException {
        loginObj = new loginPage(driver);
        catalogObj = new catalogSearchPage(driver);
        driver.get(config.getLoginUrl());
        String expectedProd = "[Radiant Tee, Circe Hooded Ice Fleece, Balboa Persistence Tee, Gobi HeatTec® Tee, Proteus Fitness Jackshirt]";
        loginObj.enterTextinSearchField(shirtProduct);
        catalogObj.getProductsName();
        String[] searchedProduct = new String[5];
        for(int i = 0; i < 5; i++)
        {
            String[] productData1 = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, i + 1); // i+1 to keep the loop consistent with Excel rows
            searchedProduct[i] = productData1[1];
        }
        String product = Arrays.toString(searchedProduct);
        Assert.assertEquals(product,expectedProd);
    }

    @Test (priority = 1)
    public void testProductCount() throws InterruptedException {
        loginObj = new loginPage(driver);
        catalogObj = new catalogSearchPage(driver);
        driver.get(config.getLoginUrl());
        loginObj.enterTextinSearchField(shirtProduct);
        catalogObj.assertProdCount();
    }

    @Test (priority = 1)
    public void testProductSizeAndColor() throws InterruptedException {
        productObj = new productPage(driver);
        driver.get(config.getShirtUrl());
        productObj.assertSizeAndColor();
    }

    @Test (priority = 1)
    public void testAddtoCart() throws InterruptedException {
        productObj = new productPage(driver);
        cartObj = new cartPage(driver);
        driver.get(config.getShirtUrl());
        productObj.assertSizeAndColor();
        productObj.clickAddToCartBtn();
        productObj.clickViewCart();
        cartObj.assertProdItem();
    }

    @Test (priority = 1)
    public void testUpdateQuantity() throws InterruptedException {
        productObj = new productPage(driver);
        cartObj = new cartPage(driver);
        driver.get(config.getShirtUrl());
        productObj.assertSizeAndColor();
        productObj.clickAddToCartBtn();
        productObj.clickViewCart();
        cartObj.changeQuantity();
        cartObj.assertPriceAfterQuantity();
    }
}
