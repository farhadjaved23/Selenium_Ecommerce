package org.example.tests;

import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class login {

    WebDriver driver;
    loginPage loginObj;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\farha\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginObj = new loginPage(driver);
        }

    @Test
    public void testValidLogin() {
        loginObj.goToLoginPage();
    }

    @AfterTest
    public void close()
    {
        driver.quit();
    }

    }
