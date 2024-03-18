package com.yape_mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPage extends BasePage {

    private AppiumDriver<MobileElement> driver;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickOnXButton() {
        driver.findElementByAccessibilityId("Navigate up").click();    
    }
}