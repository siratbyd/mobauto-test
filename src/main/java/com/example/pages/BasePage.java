package com.example.pages;

import com.example.core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import com.example.utils.LoggerUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("{0} elementine tıkla")
    public void clickElement(By locator) {
        LoggerUtil.info(locator + " elementine tıklanıyor");
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    @Step("{0} alanına {1} yaz")
    public void sendKeys(By locator, String text) {
        LoggerUtil.info(locator + " alanına " + text + " değeri yazılıyor");
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public AppiumDriver getDriver() {
        return driver;
    }
} 