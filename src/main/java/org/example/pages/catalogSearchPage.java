package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class catalogSearchPage {
    public WebDriver driver;

    private By searchedProdElements =  By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li/div/div/strong");
    private By searchedCountElement = By.xpath("(//p[@class=\"toolbar-amount\"])[1]");
    private By priceDD = By.xpath("(//select[@class=\"sorter-options\"])[1]");
    private By prodPriceElement = By.xpath("//div[@class=\"product-item-info\"]//div[@class=\"price-box price-final_price\"]");
    private By firstProdPriceElement = By.xpath("(//span[@data-price-type=\"finalPrice\"])[1]");
    private By priceFilterElement = By.xpath("//div[contains(text(), 'Price')]");
    private By firstPriceFilerElement = By.xpath("//*[@id=\"narrow-by-list\"]/div[11]/div[2]/ol/li[1]/a/span[1]");
    private By secondPriceElement = By.xpath("//*[@id=\"narrow-by-list\"]/div[11]/div[2]/ol/li[1]/a/span[2]");


    public catalogSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductsName()
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<WebElement> dropdownValues=driver.findElements(searchedProdElements);
        for(WebElement element : dropdownValues){
            String dropddowntext=element.getText();
            System.out.println(dropddowntext);
        }
        return null;
    }

    public String getProductsCount()
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<WebElement> dropdownValues=driver.findElements(searchedProdElements);
        String prodSize = String.valueOf(dropdownValues.size());
        System.out.println(dropdownValues.size());
        return prodSize;
    }

    public String getCountText()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String msg = driver.findElement(searchedCountElement).getText();
        return msg.substring(0,1);
    }

    public void assertProdCount()
    {
        Assert.assertEquals(getProductsCount(),getCountText());
    }

    public void selectDD()
    {
        Select objSelect =new Select(driver.findElement(priceDD));
        objSelect.selectByIndex(1);
    }

    public String getSortedProd() {
        ArrayList<String> price = null;
        try {
            price = new ArrayList<>();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            for (int i = 0; i < 5; i++) {
                String msg = driver.findElements(prodPriceElement).get(i).getText();
                msg = msg.substring(11, 16);
                price.add(msg);
            }
            Collections.sort(price);
        } catch (NoSuchElementException e) {
            System.out.println("Error: Element not found.");
        }
        return price.get(0);
    }
    public void assertSortedProd()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String firstValueText = driver.findElement(firstProdPriceElement).getText();
        firstValueText=firstValueText.replaceAll("[^\\d.]", "");
        Assert.assertEquals(firstValueText,getSortedProd());
    }
    public void selectPrice()
    {
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
        driver.findElement(priceFilterElement).click();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
        driver.findElement(firstPriceFilerElement).click();
    }
    public void assertPricee()
    {
        ArrayList<String> price = new ArrayList<>();
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            for (int i = 0; i < 4; i++) {
                String msg = driver.findElements(prodPriceElement).get(i).getText();
                msg = msg.replaceAll("[^\\d.]", "");
                price.add(msg);
            }
            for (String priceText : price) {
                double priceValue = Double.parseDouble(priceText);
                Assert.assertTrue(priceValue >= 40 && priceValue <= 50, "Price is not between 40 and 50 pounds. Price is: " + priceValue);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: Element not found.");
        }
    }
}