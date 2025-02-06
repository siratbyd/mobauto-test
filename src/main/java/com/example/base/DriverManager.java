package com.example.base;

import com.example.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;

public class DriverManager {
    private static DriverManager instance;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {}

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public void initializeDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            String platform = ConfigReader.getPlatform();

            switch (platform.toLowerCase()) {
                case "android":
                    setupAndroidDriver(capabilities);
                    break;
                case "ios":
                    setupIOSDriver(capabilities);
                    break;
                case "web":
                    setupWebDriver();
                    break;
                default:
                    throw new RuntimeException("Geçersiz platform: " + platform);
            }
        } catch (Exception e) {
            throw new RuntimeException("Driver başlatılamadı: " + e.getMessage());
        }
    }

    private void setupAndroidDriver(DesiredCapabilities capabilities) throws Exception {
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", ConfigReader.getProperty("android.device.name"));
        capabilities.setCapability("platformVersion", ConfigReader.getProperty("android.platform.version"));
        capabilities.setCapability("appPackage", ConfigReader.getProperty("android.app.package"));
        capabilities.setCapability("appActivity", ConfigReader.getProperty("android.app.activity"));
        capabilities.setCapability("automationName", ConfigReader.getProperty("android.automation.name"));
        
        driver.set(new AndroidDriver(
            new URI(ConfigReader.getProperty("appium.server.url")).toURL(),
            capabilities
        ));
    }

    private void setupIOSDriver(DesiredCapabilities capabilities) throws Exception {
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", ConfigReader.getProperty("ios.device.name"));
        capabilities.setCapability("platformVersion", ConfigReader.getProperty("ios.platform.version"));
        capabilities.setCapability("bundleId", ConfigReader.getProperty("ios.bundle.id"));
        capabilities.setCapability("automationName", ConfigReader.getProperty("ios.automation.name"));
        
        driver.set(new IOSDriver(
            new URI(ConfigReader.getProperty("appium.server.url")).toURL(),
            capabilities
        ));
    }

    private void setupWebDriver() {
        // Web driver kurulumu burada yapılacak
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
} 