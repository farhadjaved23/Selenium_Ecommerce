package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class shippingPage {
    public WebDriver driver;

    private By firstField = By.xpath("/html/body/div[3]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[1]/div/input");
    private By stateField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[5]/div/select");
    private By companyField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[3]/div/input");
    private By addressField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/fieldset/div/div[1]/div/input");
    private By cityField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[4]/div/input");
    private By zipField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[7]/div/input");
    private By countryField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[8]/div/select");
    private By phoneField = By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[9]/div/input");
    private By checkoutBtn = By.xpath("(//button[@title=\"Proceed to Checkout\"])[last()]");
    private By nextBtn = By.xpath("//span[contains(text(), 'Next')]");
    private By customerDataElemet = By.xpath("//div[@class=\"billing-address-details\"]");


    public shippingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String randCharacter() {
        Random random = new Random();
        char character = (char) (random.nextInt(26) + 'A');
        return "" + character;
    }

    public String randNumber() {
        Random random = new Random();
        int digit = random.nextInt(10);
        return "" + digit;
    }

    public String generateZip() {
        return "" + this.randCharacter() + this.randNumber() + this.randCharacter() + " " + this.randNumber() + this.randCharacter() + this.randNumber();
    }

    public void enterCompany(String company) {
        driver.findElement(companyField).sendKeys(company);
    }

    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void enterCity(String city) {
        driver.findElement(cityField).sendKeys(city);
    }

    public void enterZip() {
        driver.findElement(zipField).sendKeys(generateZip());
    }
    public void enterCountry() {
        Select countryDD = new Select(driver.findElement(countryField));
        countryDD.selectByVisibleText("Canada");
    }
    public void enterState() {
        Select stateDD = new Select(driver.findElement(stateField));
        stateDD.selectByVisibleText("Alberta");
    }
    public void enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
    public void clickNext() {
        driver.findElement(nextBtn).click();
    }
    public void assertCustomerData() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        boolean isDisplayed = driver.findElement(customerDataElemet).isDisplayed();
        Assert.assertTrue(isDisplayed, "The product item should be displayed, but it's not.");
    }
}