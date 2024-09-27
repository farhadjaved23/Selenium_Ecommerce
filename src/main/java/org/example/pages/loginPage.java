package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.PrimitiveIterator;
import java.util.concurrent.TimeUnit;

public class loginPage {
    public WebDriver driver;

    private By usernameField = By.xpath("//input[@name=\"login[username]\"]");
    private By passwordField = By.xpath("//input[@name=\"login[password]\"]");
    private By loginButton = By.xpath("(//button[@type='submit'])[2]");
    private By searchField = By.xpath("//input[@placeholder=\"Search entire store here...\"]");

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username)
    {
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin()
    {
        driver.findElement(loginButton).click();
    }
    public void assertInvalidMsg()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div")));
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div"));
        Assert.assertTrue(element.isDisplayed());
    }
    public void assertForgotPwdMsg()throws  NullPointerException
    {
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div"));
        Assert.assertTrue(element.isDisplayed());
    }
    public void enterTextinSearchField(String searchText)
    {
        driver.findElement(searchField).sendKeys(searchText+ Keys.ENTER);
    }

}