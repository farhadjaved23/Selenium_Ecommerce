package org.example.tests;

import org.example.base.baseTest;
import org.example.config.Config;
import org.example.pages.accountPage;
import org.example.pages.catalogSearchPage;
import org.example.pages.forgetPwdPage;
import org.example.pages.loginPage;
import org.example.utils.excelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class productFilter extends baseTest {
    private static final Logger log = LoggerFactory.getLogger(productFilter.class);
    public Config config = new Config();
    loginPage loginObj;
    catalogSearchPage catalogObj;
    excelUtils excel = new excelUtils();

    private static final String EXCEL_FILE_PATH = "src/main/resources/testData.xlsx";
    private static final String SHEET_NAME = "Sheet2"; // Your sheet name here
    String[] productData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 1);
    String shirtProduct = productData[0];

    @Test (priority = 1)
    public void testSortedProduct() throws InterruptedException {
        loginObj = new loginPage(driver);
        catalogObj = new catalogSearchPage(driver);
        driver.get(config.getLoginUrl());
        loginObj.enterTextinSearchField(shirtProduct);
        catalogObj.assertSortedProd();
        catalogObj.selectDD();
    }

    @Test (priority = 1)
    public void testPriceProduct() throws InterruptedException {
        catalogObj = new catalogSearchPage(driver);
        driver.get(config.getProductUrl());
        catalogObj.selectPrice();
        catalogObj.assertPricee();

    }
}
