package com.testproject.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String PROPERTY_FILE = "cucumber.properties";

    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            if (input == null) {
                throw new RuntimeException(PROPERTY_FILE + " bulunamadı");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Yapılandırma dosyası okunamadı: " + ex.getMessage());
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " özelliği bulunamadı");
        }
        return value;
    }

    public static String getPlatform() {
        return getProperty("platform");
    }

    public static boolean isAndroid() {
        return getPlatform().equalsIgnoreCase("android");
    }

    public static boolean isIOS() {
        return getPlatform().equalsIgnoreCase("ios");
    }

    public static boolean isWeb() {
        return getPlatform().equalsIgnoreCase("web");
    }
} 