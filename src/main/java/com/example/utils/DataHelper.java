package com.example.utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataHelper {
    private static JSONObject testData;
    private static final String DATA_FILE = "src/test/resources/data.json";

    static {
        try {
            String content = new String(Files.readAllBytes(Paths.get(DATA_FILE)));
            testData = new JSONObject(content);
        } catch (Exception e) {
            throw new RuntimeException("Test data dosyası okunamadı: " + e.getMessage());
        }
    }

    public static String getUserData(String userType, String field) {
        return testData.getJSONObject("users")
                      .getJSONObject(userType)
                      .getString(field);
    }

    public static String getProductData(String productId, String field) {
        return testData.getJSONObject("products")
                      .getJSONObject(productId)
                      .getString(field);
    }

    public static String getUrl(String environment) {
        return testData.getJSONObject("urls")
                      .getString(environment);
    }

    public static int getTimeout(String type) {
        return testData.getJSONObject("timeouts")
                      .getInt(type);
    }

    public static String getPaymentData(String method, String field) {
        return testData.getJSONObject("test_data")
                      .getJSONObject("payment_methods")
                      .getJSONObject(method)
                      .getString(field);
    }
} 