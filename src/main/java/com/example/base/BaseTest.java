package com.example.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected DriverManager driverManager;
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driverManager = DriverManager.getInstance();
        driverManager.initializeDriver();
        this.driver = driverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driverManager != null) {
            driverManager.quitDriver();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
} 