package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class forgetPwdPage {
    public WebDriver driver;

    private By usernameField = By.xpath("//input[@id=\"email_address\"]");
    private By resetBtn = By.xpath("(//button[@type=\"submit\"])[2]");


    public forgetPwdPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username)
    {
        driver.findElement(usernameField).sendKeys(username);
    }
    public void clickReset()
    {
        driver.findElement(resetBtn).click();
    }
    public void assertInvalidMsg()
    {
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div"));
        Assert.assertTrue(element.isDisplayed());
    }

}