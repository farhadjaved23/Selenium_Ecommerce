package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Random;

public class productPage {
    public WebDriver driver;

    private By sizeField = By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div[1]/div");
    private By sizeLable = By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[1]/span[2]");
    private By colorField = By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div");
    private By colorLable = By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[2]/span[2]");
    private By cartBtn = By.xpath("//button[@title=\"Add to Cart\"]");
    private By cartLink = By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div/a");

    public productPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void assertInvalidMsg()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div")));
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div"));
        Assert.assertTrue(element.isDisplayed());
    }

    public int getRandomSize(int size)
    {
        Random random = new Random();
        int randomInt = random.nextInt(size) + 1;
        return randomInt;
    }

    public int getSizeLength()
    {
        int size = driver.findElements(sizeField).size();
        return size;
    }

    public String getSizeText()
    {
        int sizeLength = this.getRandomSize(this.getSizeLength());
        driver.findElement(By.xpath("(//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div[1]/div)["+sizeLength+"]")).click();
        String text = driver.findElement(By.xpath("(//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div[1]/div)["+sizeLength+"]")).getText();
        return text;
    }
    public String getSizeTextLabel()
    {
        String sizeLabel = driver.findElement(sizeLable).getText();
        return sizeLabel;
    }
    public int getColorLength()
    {
        int color = driver.findElements(colorField).size();
        return color;
    }
    public String getLengthText()
    {
        int sizeLength = this.getRandomSize(this.getColorLength());
        driver.findElement(By.xpath("(//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div)["+sizeLength+"]")).click();
        String text = driver.findElement(By.xpath("(//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div)["+sizeLength+"]")).getAttribute("option-label");
        return text;
    }
    public String getLengthTextLabel()
    {
        String colorLabel = driver.findElement(colorLable).getText();
        return colorLabel;
    }
    public void assertSizeAndColor()
    {
        Assert.assertEquals(this.getSizeText(),this.getSizeTextLabel());
        Assert.assertEquals(this.getLengthText(),this.getLengthTextLabel());
    }
    public void clickAddToCartBtn()
    {
        driver.findElement(cartBtn).click();
    }
    public void clickViewCart()
    {
        driver.findElement(cartLink).click();
    }
}