package com.yape_mobile.pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Utils {
    
    public static void scrollUntilElementFound(AppiumDriver<MobileElement> driver, By modalSelector, By elementToFindSelector){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

        while (!isElementVisible(driver, elementToFindSelector)) {
            scrollDown(driver, modalSelector);
        }   

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    }

    public static boolean isElementVisible(WebDriver driver, By elementToFindSelector) {
        List<WebElement> elements = driver.findElements(elementToFindSelector);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }
    
    public static void scrollDown(WebDriver driver, By modalSelector) {
        WebElement modal = driver.findElement(modalSelector);
    
        int startX = modal.getLocation().getX() + (modal.getSize().getWidth() / 2);
        int startY = modal.getLocation().getY() + (modal.getSize().getHeight() / 2);
        int endY = startY - (modal.getSize().getHeight() / 3);

        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
    
        touchAction.press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(startX, endY))
            .release()
            .perform();
    }
}
