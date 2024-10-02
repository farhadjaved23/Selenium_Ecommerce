package org.example.tests;

import org.example.base.baseTest;
import org.example.config.Config;
import org.example.pages.*;
import org.example.utils.excelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class order extends baseTest {
    private static final Logger log = LoggerFactory.getLogger(order.class);
    public Config config = new Config();
    loginPage loginObj;
    accountPage accountObj;
    shippingPage shippingObj;
    productPage productObj;
    excelUtils excel = new excelUtils();

    Random random = new Random();
    private static final String EXCEL_FILE_PATH = "src/main/resources/testData.xlsx";
    private static final String SHEET_NAME = "Sheet1"; // Your sheet name here
    String[] loginData = excel.getLoginData(EXCEL_FILE_PATH, SHEET_NAME, 1);
    String username = loginData[0];
    String password = loginData[1];

    String[] customerData = excel.getCustomerData(EXCEL_FILE_PATH, SHEET_NAME, random.nextInt(2) + 1);
    String company = customerData[0];
    String address = customerData[1];
    String city = customerData[2];
    String phone = customerData[5];


    @Test (priority = 1)
    public void testCustomerData() throws InterruptedException, NullPointerException {
        loginObj = new loginPage(driver);
        accountObj = new accountPage(driver);
        shippingObj = new shippingPage(driver);
        productObj = new productPage(driver);
        accountObj.generateRandomPassword(6);
    }
    }
