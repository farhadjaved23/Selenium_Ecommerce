package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties properties;
    private final String propertyFilePath = "src/main/resources/config.properties";

    public Config() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(propertyFilePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getLoginUrl() {
        return properties.getProperty("login.url");
    }

    public String getForgetPwdUrl() {
        return properties.getProperty("forgetPwd.url");
    }

    public String getProductUrl() {
        return properties.getProperty("product.url");
    }

    public String getShirtUrl() {
        return properties.getProperty("product2.url");
    }
    public String getCartUrl() {
        return properties.getProperty("cart.url");
    }
    public String getAccountUrl() {
        return properties.getProperty("account.url");
    }
    public String getAddressUrl() {
        return properties.getProperty("address.url");
    }

}