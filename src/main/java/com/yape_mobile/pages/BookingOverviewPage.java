package com.yape_mobile.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BookingOverviewPage extends BasePage{

    private final User user;
    private final Reservation reservation;

    public BookingOverviewPage(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    // check Booking Overview
    //id com.booking:id/checkin_date Tue May 07 2024
    //id com.booking:id/checkout_date  Tue May 14 2024

    public boolean isCheckInDateCorrect() throws Exception {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = originalFormat.parse(reservation.getStartDate());
        String formattedCheckInDate = targetFormat.format(date);
    
        WebElement elem = driver.findElement(By.id("com.booking:id/checkin_date"));
        System.out.println("check in date: " + elem.getText() + " expected: " + formattedCheckInDate);
        return elem.getText().equals(formattedCheckInDate);   
    }
    
    public boolean isCheckOutDateCorrect() throws Exception {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = originalFormat.parse(reservation.getEndDate());
        String formattedCheckOutDate = targetFormat.format(date);
    
        WebElement elem = driver.findElement(By.id("com.booking:id/checkout_date"));
        System.out.println("check out date: " + elem.getText() + " expected: " + formattedCheckOutDate);
        return elem.getText().equals(formattedCheckOutDate);   
    }


    //total price:  com.booking:id/bp_price_summary_total_price_value 
    //taxes: com.booking:id/bp_price_summary_taxes_and_charges

    public boolean isBookingOverviewTotalAmountCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/bp_price_summary_total_price_value"));
        System.out.println("total amount Booking Overview: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isBookingOverviewTaxesAmountCorrect(String taxesAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/bp_price_summary_taxes_and_charges"));
        System.out.println("total amount Booking Overview: " + elem.getText() + " expected: " + taxesAmount);
        return elem.getText().equals(taxesAmount);   
    }

    //total price: //android.widget.TextView[@resource-id="com.booking:id/title" and @text="US$3,674"]
    //taxes: //android.widget.TextView[@resource-id="com.booking:id/subtitle" and @text="+ US$1,566 taxes and charges"]
    public boolean isTotalAmountCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.booking:id/title'])[2]"));
        System.out.println("total amount Booking Overview Footter: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountCorrect(String taxesAmount){
        WebElement elem = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.booking:id/subtitle'])[2]"));
        System.out.println("taxes amount Booking Overview Footter: " + elem.getText() + " expected: " + taxesAmount);
        return elem.getText().equals(taxesAmount);   
    }

    public void clickOnFinalStepButton(){
        driver.findElement(By.id("com.booking:id/action_button")).click();
    }

}
