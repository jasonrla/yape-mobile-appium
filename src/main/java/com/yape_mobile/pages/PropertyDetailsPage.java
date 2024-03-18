package com.yape_mobile.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PropertyDetailsPage extends BasePage{

    private final User user;
    private final Reservation reservation;

    public PropertyDetailsPage(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    public boolean isSelectRoomModalOpened() {
        WebElement elem = driver.findElement(By.id("com.booking:id/fragment_container"));   
        return elem.isDisplayed();
    }

    public boolean isTotalAmountInPropertyDetailsCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/price_view_price"));  
        //cuando hay oferta:
        // (//android.widget.TextView[@resource-id='com.booking:id/price_view_price])[2]
        System.out.println("total amount Property details: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountInPropertyDetailsCorrect(String taxes){
        WebElement elem = driver.findElement(By.id("com.booking:id/price_view_taxes_and_charges"));
        System.out.println("taxe amount Propery details: " + elem.getText() + " expected: " + taxes);
        return elem.getText().equals(taxes);   
    }

    //   validar fechas 
    //   com.booking:id/toolbar_subtitle_textView   May 7 - May 14
    //   com.booking:id/checkin_display  Tue, May 07
    //   com.booking:id/checkout_display Tue, May 14


    public void selectRoom(){
        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.booking:id/property_availability_cta_facetframe']/android.view.ViewGroup")).click();
    }    
}
