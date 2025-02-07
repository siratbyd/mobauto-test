package com.example.steps;

import com.example.pages.LoginPage;
import io.cucumber.java.en.*;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage();
    
    @Given("I launch the application")
    public void launchApp() {
        // Uygulama zaten başlatıldı
    }
    
    @When("I enter username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }
    
    // Diğer step tanımları...
} 