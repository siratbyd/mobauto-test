package com.example.utils;

public class ConfigReader {
    public static String getProperty(String key) {
        return System.getProperty(key, System.getenv().getOrDefault(key, "default-value"));
    }
    
    public static String getPlatform() {
        return getProperty("platform");
    }
    
    public static String getAppPath() {
        return System.getProperty("appPath", "/path/to/default/app.apk");
    }
} 