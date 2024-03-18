package com.yape_mobile.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.rmi.CORBA.Util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;

import io.appium.java_client.TouchAction;

public class SearchResultsPage extends BasePage {

    private final User user;
    private final Reservation reservation;

    public SearchResultsPage(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    public boolean isPropertiesListDisplayed() {
        WebElement elem = driver.findElement(By.id("com.booking:id/results_list_facet"));   
        return elem.isDisplayed();
    }

    public void scrollUntilItemNumber(){
        Utils.scrollUntilElementFound(
            driver,
            By.xpath("//androidx.recyclerview.widget.RecyclerView"),
            By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup["+reservation.getItemNumber()+"]")
            
        );
    }

    public String extractStayDetailsText() {
        return driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.booking:id/price_view_stay_details'])["+reservation.getItemNumber()+"]")).getText();
    }
    
    public String extractAmountText() {
        By viewGroupSelector = By.xpath("(//android.view.ViewGroup[@resource-id='com.booking:id/price_view_holder'])["+reservation.getItemNumber()+"]");
        WebElement viewGroup = driver.findElement(viewGroupSelector);
        WebElement textView = viewGroup.findElement(By.className("android.widget.TextView"));
        return textView.getText();
    }
    
    public String extractTaxesAndChargesText() {
        return driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.booking:id/price_view_taxes_and_charges'])["+reservation.getItemNumber()+"]")).getText();
    }

    
    public void selectSecondOption(){
        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup["+reservation.getItemNumber()+"]")).click();
    }

}  