package com.example.pages;

import com.example.base.DriverManager;
import com.example.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import com.example.utils.LoggerUtil;

public class BasePage {
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getInstance().getDriver();
        this.waitHelper = new WaitHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @Step("{0} elementine tıkla")
    public void clickElement(By locator) {
        LoggerUtil.info(locator + " elementine tıklanıyor");
        waitHelper.waitForElementClickable(driver.findElement(locator)).click();
    }

    @Step("{0} alanına {1} yaz")
    public void sendKeys(By locator, String text) {
        LoggerUtil.info(locator + " alanına " + text + " değeri yazılıyor");
        WebElement element = waitHelper.waitForElementVisible(driver.findElement(locator));
        element.clear();
        element.sendKeys(text);
    }

    public WebDriver getDriver() {
        return driver;
    }
} 