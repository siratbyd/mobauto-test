package com.example.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import java.util.Objects;
import com.example.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage {
    
    private By welcomeMessage() {
        return Objects.equals(ConfigReader.getPlatform(), "android") ?
            AppiumBy.xpath("//android.widget.TextView[@resource-id='welcome']") :
            AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Welcome']");
    }

    public String getWelcomeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage())).getText();
    }
} 