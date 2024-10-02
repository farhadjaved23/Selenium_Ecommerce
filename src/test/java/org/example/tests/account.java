package org.example.tests;

import org.example.base.baseTest;
import org.example.config.Config;
import org.example.pages.accountPage;
import org.example.pages.loginPage;
import org.example.utils.excelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class account extends baseTest {
    private static final Logger log = LoggerFactory.getLogger(account.class);
    public Config config = new Config();
    loginPage loginObj;
    accountPage accountObj;
    excelUtils excel = new excelUtils();
   

    private static final String EXCEL_FILE_PATH = "src/main/resources/testData.xlsx";
    private static final String SHEET_NAME = "Sheet1"; // Your sheet name here
    String[] loginData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 1);
    String[] invalidLoginData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 2);// Change row number as needed
    String username = loginData[0];
    String invalidUsername = invalidLoginData[0];
    String password = loginData[1];

    @Test (priority = 1)
    public void testChangeCountry() throws InterruptedException, NullPointerException {
        loginObj = new loginPage(driver);
        accountObj = new accountPage(driver);
        driver.get(config.getAddressUrl());
        loginObj.enterUsername(username);
        loginObj.enterPassword(password);
        loginObj.clickLogin();
        driver.get(config.getAddressUrl());
        accountObj.assertCountry();
    }
}
