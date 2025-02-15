package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.base.baseTest;
import org.example.config.Config;
import org.example.pages.accountPage;
import org.example.pages.forgetPwdPage;
import org.example.pages.shippingPage;
import org.example.utils.excelUtils;
import org.example.pages.loginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class login extends baseTest {
    private static final Logger log = LoggerFactory.getLogger(login.class);
    public Config config = new Config();
    loginPage loginObj;
    accountPage accountObj;
    forgetPwdPage forgetObj;
    shippingPage shippingObj;
    excelUtils excel = new excelUtils();
   

    private static final String EXCEL_FILE_PATH = "src/main/resources/testData.xlsx";
    private static final String SHEET_NAME = "Sheet1"; // Your sheet name here
    String[] loginData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 1);
    String[] invalidLoginData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 2);// Change row number as needed
    String username = loginData[0];
    String invalidUsername = invalidLoginData[0];
    String password = loginData[1];

    @Test (priority = 1)
    public void testValidLogin() throws InterruptedException, NullPointerException {
        test = extent.createTest("Luma Login Test", "Testing Login functionality");
        loginObj = new loginPage(driver);
        accountObj = new accountPage(driver);
        shippingObj = new shippingPage(driver);
        driver.get(config.getLoginUrl());
        loginObj.enterUsername(username);
        loginObj.enterPassword(password);
        loginObj.clickLogin();
        accountObj.assertUserAfterogin(username);
        test.pass("User logged in successfully");
    }

    @Test (priority = 2)
    public void testInvalidLogin() throws InterruptedException, NullPointerException  {
        test = extent.createTest("Luma Invalid Login Test", "Testing Login functionality");
        loginObj = new loginPage(driver);
        driver.get(config.getLoginUrl());
        loginObj.enterUsername(invalidUsername);
        loginObj.enterPassword(password);
        loginObj.clickLogin();
        loginObj.assertInvalidMsg();
        test.pass("User will get error Msg");
    }

    @Test (priority = 3)
    public void forgetPwd() throws InterruptedException, NullPointerException  {
        test = extent.createTest("Luma forgetPwd Test", "Testing Login functionality");
        forgetObj = new forgetPwdPage(driver);
        loginObj = new loginPage(driver);
        driver.get(config.getForgetPwdUrl());
        forgetObj.enterUsername(invalidUsername);
        forgetObj.clickReset();
        loginObj.assertForgotPwdMsg();
        test.pass("Email sent msg should be displayed");
    }
}
