package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class accountPage {
    public WebDriver driver;

    private By contactInfoField = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/div[2]/div/div[1]/p");
    private By pwdBtn = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/div[2]/div/div[2]/a[2]");
    private By currentPwdField = By.xpath("//*[@id=\"current-password\"]");
    private By newPwdField = By.xpath("//*[@id=\"password\"]");
    private By confirmPwdField = By.xpath("//*[@id=\"password-confirmation\"]");
    private By saveBtn = By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button");
    private By countryDD = By.xpath("//*[@id=\"country\"]");

    public accountPage(WebDriver driver) throws NullPointerException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getContactInfo() throws NullPointerException
    {
        String msg = driver.findElement(contactInfoField).getText();
        msg = msg.substring(8).trim();
        return msg;
    }
    public void assertUserAfterogin(String actual) throws NullPointerException
    {
        Assert.assertTrue(getContactInfo().contains("lumaQA@mailinator.com"),actual);
    }
    public void clickPwdBtn()
    {
        driver.findElement(pwdBtn).click();
    }
    public void changePwd(String current,String newPwd,String confirmPwd)
    {
        driver.findElement(currentPwdField).sendKeys(current);
        driver.findElement(newPwdField).sendKeys(newPwd);
        driver.findElement(confirmPwdField).sendKeys(confirmPwd);
        driver.findElement(saveBtn).click();
    }
    public  String generateRandomPassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Append a random digit (0-9)
            password.append(random.nextInt(10));
        }
        return password.toString();
    }

        public void assertCountry() {
            Select dropdown = new Select(driver.findElement(countryDD));
            boolean containsPakistan = false;
            List<WebElement> countryOptions = dropdown.getOptions();
            for (WebElement option : countryOptions) {
                String countryText = option.getText();
                if (countryText.equalsIgnoreCase("United Kingdom")) {
                    option.click();
                    System.out.println("Clicked on Pakistan");
                    containsPakistan = true;
                    Assert.assertTrue(containsPakistan);
                    break;
                }
            }
            if (!containsPakistan) {
                throw new RuntimeException("Error: Pakistan was not found in the dropdown options.");
            }
        }
    }
