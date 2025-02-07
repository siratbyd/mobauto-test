package com.example.core;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverManager {
    private static AppiumDriver driver;
    
    public static AppiumDriver getDriver() {
        if (driver == null) {
            DesiredCapabilities caps = getCapabilities();
            
            try {
                driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), caps);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RuntimeException("Driver başlatılamadı: " + e.getMessage());
            }
        }
        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigReader.getPlatform());
        caps.setCapability(MobileCapabilityType.APP, ConfigReader.getProperty("appPath"));
        // Diğer ayarlar...
        return caps;
    }
} 