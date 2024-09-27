package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class cartPage {
    public WebDriver driver;

    private By price = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[2]/span/span/span");
    private By productItem = By.xpath("//tbody[@class=\"cart item\"]");
    private By quantityField = By.xpath("//input[@title=\"Qty\"]");
    private By subTotalPrice = By.xpath("/html/body/div[2]/main/div[3]/div/div[2]/form/div[1]/table/tbody/tr[1]/td[4]/span/span/span");

    public cartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void assertProdItem()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItem));
        boolean isDisplayed = driver.findElement(productItem).isDisplayed();
        Assert.assertTrue(isDisplayed, "The product item should be displayed, but it's not.");
    }
    public String getPrice()
    {
        String priceText = driver.findElement(price).getText();
        priceText=priceText.replaceAll("[^\\d.]", "");
        return priceText;
    }
    public void changeQuantity()
    {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys("3"+ Keys.ENTER);
    }
    public double updatePrice()
    {
        double subTotalValue = Double.parseDouble(this.getPrice());
        String quantity = driver.findElement(quantityField).getAttribute("value");
        int quantityValue = Integer.parseInt(quantity); // Convert to int
        double updatedPrice = subTotalValue * quantityValue;
        return updatedPrice;
    }
    public void assertPriceAfterQuantity() throws NumberFormatException
    {
        String subTotal = driver.findElement(subTotalPrice).getText();
        subTotal = subTotal.substring(1,6);
        double subTotals = Double.parseDouble(subTotal);
        Assert.assertEquals(subTotals,this.updatePrice());
    }
}