package com.yape_mobile.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class FinishBooking extends BasePage{

    private final User user;
    private final Reservation reservation;

    public FinishBooking(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    public void enterCardNumber(){
        driver.findElement(By.id("com.booking:id/new_credit_card_number_edit")).sendKeys(user.getCardNumber());
    }

    public void enterHolderName(String cardHolder){
        driver.findElement(By.id("com.booking:id/new_credit_card_holder_edit")).sendKeys(cardHolder);
    }

    public boolean checkHolderNameIsCorrect(){
        WebElement elem = driver.findElement(By.id("com.booking:id/new_credit_card_holder_edit"));
        System.out.println(elem.getText().equals(String.format("%s %s", user.getFirstName(), user.getLastName())));
        return elem.getText().equals(String.format("%s %s", user.getFirstName(), user.getLastName()));
    }

    public void enterExpirationDate(){
        driver.findElement(By.id("com.booking:id/new_credit_card_expiry_date_edit")).sendKeys(user.getExpirationDate());
    }

    //check Finish Booking
    //total price: primero: //android.widget.LinearLayout[@resource-id="com.booking:id/informative_cta_view_price_container"]/android.widget.FrameLayout[1]
    ///luego : //android.widget.TextView[@resource-id="com.booking:id/title" and @text="US$3,674"]
    //taxes: com.booking:id/subtitle

    public boolean isTotalAmountFooterCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.booking:id/title'])[2]"));
        System.out.println("total amount Finish Booking Footter: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountFooterCorrect(String taxesAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/subtitle"));
        System.out.println("taxes amount Finish Booking Footter: " + elem.getText() + " expected: " + taxesAmount);
        return elem.getText().equals(taxesAmount);   
    }

    public void scrollToDates(){
        Utils.scrollUntilElementFound(
            driver,
            By.id("android:id/content"),
            By.id("com.booking:id/checkin_date")
        );
    }

    public boolean isCheckInDate2Correct() throws Exception {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = originalFormat.parse(reservation.getStartDate());
        String formattedCheckInDate = targetFormat.format(date);
    
        WebElement elem = driver.findElement(By.id("com.booking:id/checkin_date"));
        System.out.println("check in date Finish booking: " + elem.getText() + " expected: " + formattedCheckInDate);
        return elem.getText().equals(formattedCheckInDate);  
    }

    public boolean isCheckOutDate2Correct() throws Exception {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = originalFormat.parse(reservation.getEndDate());
        String formattedCheckOutDate = targetFormat.format(date);
    
        WebElement elem = driver.findElement(By.id("com.booking:id/checkout_date"));
        System.out.println("check out date Finish Nooking: " + elem.getText() + " expected: " + formattedCheckOutDate);
        return elem.getText().equals(formattedCheckOutDate);   
    }

    public void scrollFinishBookingDetails(){
        Utils.scrollUntilElementFound(
            driver,
            By.id("android:id/content"),
            By.id("com.booking:id/bp_price_summary_taxes_and_charges")
        );
    }

    
    public boolean isNameCorrect() {
        WebElement elem = driver.findElement(By.id("com.booking:id/name"));
        String expectedValue = String.format("%s %s", user.getFirstName(), user.getLastName());
        System.out.println("name Finish Booking: " + elem.getText() + " expected: " + expectedValue);
        return elem.getText().equals(expectedValue);
        }
    
    public boolean isEmailCorrect() {
        WebElement elem = driver.findElement(By.id("com.booking:id/email"));
        System.out.println("email Finish Booking: " + elem.getText() + " expected: " + user.getEmail());
        return elem.getText().equals(user.getEmail());
    }
    
    public boolean isAddressCorrect() {
        WebElement elem = driver.findElement(By.id("com.booking:id/address_zip"));
        String expectedValue = String.format("%s, %s", user.getAddress(), user.getZipCode());
        System.out.println("address zip Finish Booking: " + elem.getText() + " expected: " + expectedValue);
        return elem.getText().equals(expectedValue);
    }
    
    public boolean isCityCountryCorrect() {
        WebElement elem = driver.findElement(By.id("com.booking:id/city_country"));
        String expectedValue = String.format("%s, %s", user.getCity(), user.getCountry());
        System.out.println("city country Finish Booking: " + elem.getText() + " expected: " + expectedValue);
        return elem.getText().equals(expectedValue);
    }
    
    public boolean isPhoneNumberCorrect() {
        WebElement elem = driver.findElement(By.id("com.booking:id/phone"));
        System.out.println("phone number Finish Booking: " + elem.getText() + " expected: " + user.getPhoneNumber());
        return elem.getText().equals(user.getPhoneNumber());
    }

    public boolean isBookingOverviewTotal2AmountCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/bp_price_summary_total_price_value"));
        System.out.println("total amount Finish Booking: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isBookingOverviewTaxes2AmountCorrect(String taxesAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/bp_price_summary_taxes_and_charges"));
        System.out.println("total amount Finish Booking: " + elem.getText() + " expected: " + taxesAmount);
        return elem.getText().equals(taxesAmount);   
    }

    public boolean isBookNowButtonEnabled() {
        WebElement elem = driver.findElement(By.id("com.booking:id/action_button"));   
        return elem.isEnabled();
    }

    public void clickOnBookNowButton(){
        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.booking:id/informative_click_to_action_container']/android.widget.LinearLayout/android.widget.LinearLayout"));
        driver.findElement(By.id("com.booking:id/action_button")).click();
    }

    public String checkErrorMessage(){
        return driver.findElement(By.id("com.booking:id/textinput_error")).getText();
    }
    
}
