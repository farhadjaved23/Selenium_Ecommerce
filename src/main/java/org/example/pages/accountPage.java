package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class accountPage {
    public WebDriver driver;

    private By contactInfoField = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/div[2]/div/div[1]/p");
    private By inValidMsgField = By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div");

    public accountPage(WebDriver driver) throws NullPointerException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getContactInfo() throws NullPointerException
    {
        String msg = driver.findElement(contactInfoField).getText();
        msg = msg.substring(10).trim();
        return msg;
    }
    public void assertUserAfterogin(String actual) throws NullPointerException
    {
        Assert.assertTrue(getContactInfo().contains("testLumaqa@mailinator.com"),actual);
    }

}