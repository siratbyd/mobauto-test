package com.example.stepdefinitions;

import com.example.base.BaseTest;
import com.example.utils.WaitHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.AppiumBy;
import java.util.Objects;
import com.example.utils.ConfigReader;
import com.example.pages.LoginPage;
import com.example.pages.HomePage;
import com.example.utils.LoggerUtil;

public class CommonSteps extends BaseTest {
    private WaitHelper waitHelper;
    private Actions actions;
    private JavascriptExecutor js;
    private LoginPage loginPage;
    private HomePage homePage;
    
    @Before
    public void setup() {
        super.setUp();
        waitHelper = new WaitHelper(super.getDriver());
        actions = new Actions(super.getDriver());
        js = (JavascriptExecutor) super.getDriver();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @After
    public void cleanup() {
        super.tearDown();
    }

    @And("click element {string}")
    public void clickElement(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            clickPath(AppiumBy.xpath("//android.widget.Button[@text='" + locator + "']"));
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            clickPath(AppiumBy.xpath("//XCUIElementTypeButton[@name='" + locator + "']"));
        }
    }

    @And("write text {string} to element {string}")
    public void writeText(String text, String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='" + locator + "']"));
            element.sendKeys(text);
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeTextField[@name='" + locator + "']"));
            element.sendKeys(text);
        }
    }

    @And("wait {string} seconds")
    public void waitSecond(String second) throws Exception {
        Thread.sleep(Integer.parseInt(second) * 1000L);
    }

    @And("element {string} should be visible")
    public void elementShouldBeVisible(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + locator + "']"));
            Assert.assertTrue(element.isDisplayed(), "Element görünür değil: " + locator);
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name='" + locator + "']"));
            Assert.assertTrue(element.isDisplayed(), "Element görünür değil: " + locator);
        }
    }

    @And("element {string} should contain text {string}")
    public void elementShouldContainText(String locator, String expectedText) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='" + locator + "']"));
            Assert.assertTrue(element.getText().contains(expectedText));
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name='" + locator + "']"));
            Assert.assertTrue(element.getText().contains(expectedText));
        }
    }

    @And("element {string} should be enabled")
    public void elementShouldBeEnabled(String locator) {
        WebElement element = waitHelper.waitForElementVisible(super.getDriver().findElement(By.xpath(locator)));
        Assert.assertTrue(element.isEnabled(), "Element etkin değil: " + locator);
    }

    @And("element {string} should be selected")
    public void elementShouldBeSelected(String locator) {
        WebElement element = waitHelper.waitForElementVisible(super.getDriver().findElement(By.xpath(locator)));
        Assert.assertTrue(element.isSelected(), "Element seçili değil: " + locator);
    }

    // Scroll işlemleri
    @And("scroll to *{string}* element")
    public void scrollToElement(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//android.widget.ScrollView"));
            ((JavascriptExecutor) super.getDriver()).executeScript(
                    "arguments[0].scrollIntoView(" + 
                    "//android.widget.TextView[@text='" + locator + "'])", element);
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeScrollView"));
            ((JavascriptExecutor) super.getDriver()).executeScript(
                    "arguments[0].scrollIntoView(" + 
                    "//XCUIElementTypeStaticText[@name='" + locator + "'])", element);
        }
    }

    @And("scroll to bottom of page")
    public void scrollToBottom() throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//android.widget.ScrollView"));
            ((JavascriptExecutor) super.getDriver()).executeScript("mobile: scrollGesture", element);
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeScrollView"));
            ((JavascriptExecutor) super.getDriver()).executeScript("mobile: scroll", element);
        }
    }

    @And("scroll to top of page")
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
    }

    // Copy-Paste işlemleri
    @And("copy text from *{string}* element")
    public void copyText(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            super.getDriver().findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='" + locator + "']"));
            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL).perform();
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            super.getDriver().findElement(
                AppiumBy.xpath("//XCUIElementTypeStaticText[@name='" + locator + "']"));
            actions.keyDown(Keys.COMMAND).sendKeys("a").sendKeys("c").keyUp(Keys.COMMAND).perform();
        }
    }

    @And("paste text to *{string}* element")
    public void pasteText(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(
                AppiumBy.xpath("//android.widget.EditText[@resource-id='" + locator + "']"));
            element.click();
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(
                AppiumBy.xpath("//XCUIElementTypeTextField[@name='" + locator + "']"));
            element.click();
            actions.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();
        }
    }

    // Bekleme işlemleri
    @And("wait *{int}* seconds")
    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Hover işlemleri
    @And("hover over *{string}* element")
    public void hoverOverElement(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement element = super.getDriver().findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='" + locator + "']"));
            actions.moveToElement(element).perform();
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement element = super.getDriver().findElement(
                AppiumBy.xpath("//XCUIElementTypeStaticText[@name='" + locator + "']"));
            actions.moveToElement(element).perform();
        }
    }

    // Frame işlemleri
    @And("switch to *{string}* frame")
    public void switchToFrame(String locator) throws Exception {
        if (Objects.equals(ConfigReader.getPlatform(), "android")) {
            WebElement frameElement = super.getDriver().findElement(
                AppiumBy.xpath("//android.widget.WebView[@resource-id='" + locator + "']"));
            super.getDriver().switchTo().frame(frameElement);
        } else if (Objects.equals(ConfigReader.getPlatform(), "ios")) {
            WebElement frameElement = super.getDriver().findElement(
                AppiumBy.xpath("//XCUIElementTypeWebView[@name='" + locator + "']"));
            super.getDriver().switchTo().frame(frameElement);
        }
    }

    @And("switch to default content")
    public void switchToDefaultContent() {
        super.getDriver().switchTo().defaultContent();
    }

    @And("write text {string} to username field")
    public void enterUsername(String username) {
        LoggerUtil.info("Kullanıcı adı alanı dolduruluyor: " + username);
        loginPage.sendKeys(loginPage.usernameField(), username);
    }

    @And("click login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @And("verify welcome message contains {string}")
    public void verifyWelcomeMessage(String expectedText) {
        Assert.assertTrue(homePage.getWelcomeText().contains(expectedText));
    }

    private void clickPath(By by) throws Exception {
        WebElement element = super.getDriver().findElement(by);
        element.click();
    }
} 