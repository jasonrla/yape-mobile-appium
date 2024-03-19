package com.yape_mobile.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SignInPage extends BasePage {

    private AppiumDriver<MobileElement> driver;

    public SignInPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPage() {
        driver.findElementByAccessibilityId("Navigate up").click();    
    }
}