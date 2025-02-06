package com.example.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import java.util.Objects;
import com.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {
    
    public WebDriver getDriver() {
        return driver;
    }

    // Platforma özel locator'lar
    public By usernameField() {
        return Objects.equals(ConfigReader.getPlatform(), "android") ?
            AppiumBy.xpath("//android.widget.EditText[@resource-id='username']") :
            AppiumBy.xpath("//XCUIElementTypeTextField[@name='Username']");
    }

    public By passwordField() {
        return Objects.equals(ConfigReader.getPlatform(), "android") ?
            AppiumBy.xpath("//android.widget.EditText[@resource-id='password']") :
            AppiumBy.xpath("//XCUIElementTypeSecureTextField[@name='Password']");
    }

    public By loginButton() {
        return Objects.equals(ConfigReader.getPlatform(), "android") ?
            AppiumBy.xpath("//android.widget.Button[@text='Login']") :
            AppiumBy.xpath("//XCUIElementTypeButton[@name='Login']");
    }

    @Step("Kullanıcı adı ve şifre gir: {0} / {1}")
    public void enterCredentials(String username, String password) {
        sendKeys(usernameField(), username);
        sendKeys(passwordField(), password);
    }

    @Step("Login butonuna tıkla")
    public void clickLogin() {
        clickElement(loginButton());
    }
} 