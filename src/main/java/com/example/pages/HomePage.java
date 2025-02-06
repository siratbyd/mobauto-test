package com.example.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import java.util.Objects;

public class HomePage extends BasePage {
    
    private By welcomeMessage() {
        return Objects.equals(ConfigReader.getPlatform(), "android") ?
            AppiumBy.xpath("//android.widget.TextView[@resource-id='welcome']") :
            AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Welcome']");
    }

    public String getWelcomeText() {
        return waitHelper.waitForElementVisible(getDriver().findElement(welcomeMessage())).getText();
    }
} 