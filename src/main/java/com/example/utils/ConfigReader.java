package com.example.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPlatform() {
        return properties.getProperty("platform", "android");
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
} 