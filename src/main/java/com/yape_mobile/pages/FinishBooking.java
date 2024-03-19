package com.yape_mobile.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class FinishBooking extends BasePage{

    private final User user;
    private final Reservation reservation;

    @FindBy (id = "com.booking:id/new_credit_card_number_edit")
    private WebElement cardNumberField;

    @FindBy (id = "com.booking:id/new_credit_card_holder_edit")
    private WebElement cardHolderField;

    @FindBy (id = "com.booking:id/new_credit_card_expiry_date_edit")
    private WebElement expirationDateField;

    @FindBy (xpath = "(//android.widget.TextView[@resource-id='com.booking:id/title'])[2]")
    private WebElement totalAmountFooter;
    
    @FindBy (id = "com.booking:id/subtitle") 
    private WebElement taxesAmountFooter;

    private String finishBookingModal = "android:id/content";

    private String checkinDate = "com.booking:id/checkin_date";

    @FindBy (id = "com.booking:id/checkin_date")
    private WebElement checkinDateElement;

    @FindBy (id = "com.booking:id/checkout_date")
    private WebElement checkoutDateElement;

    private String taxes = "com.booking:id/bp_price_summary_taxes_and_charges";

    @FindBy (id = "com.booking:id/name")
    private WebElement nameElement;

    @FindBy (id = "com.booking:id/email")
    private WebElement emailElement;

    @FindBy (id = "com.booking:id/address_zip")
    private WebElement addressElement;

    @FindBy (id = "com.booking:id/city_country")
    private WebElement cityCountryElement;

    @FindBy (id = "com.booking:id/phone")
    private WebElement phoneElement;

    @FindBy (id = "com.booking:id/bp_price_summary_total_price_value")
    private WebElement totalAmountElement;

    @FindBy (id = "com.booking:id/bp_price_summary_taxes_and_charges")
    private WebElement taxesElement;

    @FindBy (id = "com.booking:id/action_button")
    private WebElement bookNowButton;

    @FindBy (id = "com.booking:id/textinput_error")
    private WebElement cardNumberErrorMessage;

    public FinishBooking(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
        PageFactory.initElements(driver, this);
    }

    public void enterCardNumber(){
        cardNumberField.sendKeys(user.getCardNumber());
    }

    public void enterHolderName(String cardHolder){
        cardHolderField.sendKeys(cardHolder);
    }

    public boolean checkHolderNameIsCorrect(){
        return cardHolderField.getText().equals(String.format("%s %s", user.getFirstName(), user.getLastName()));
    }

    public void enterExpirationDate(){
        expirationDateField.sendKeys(user.getExpirationDate());
    }

    public boolean isTotalAmountFooterCorrect(String totalAmount){
        return totalAmountFooter.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountFooterCorrect(String taxesAmount){
        return taxesAmountFooter.getText().equals(taxesAmount);   
    }

    public void scrollToDates(){
        Utils.scrollUntilElementFound(
            driver,
            By.id(finishBookingModal),
            By.id(checkinDate)
        );
    }

    public boolean isCheckInDate2Correct() throws Exception {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = originalFormat.parse(reservation.getStartDate());
        String formattedCheckInDate = targetFormat.format(date);
    
        return checkinDateElement.getText().equals(formattedCheckInDate);  
    }

    public boolean isCheckOutDate2Correct() throws Exception {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date date = originalFormat.parse(reservation.getEndDate());
        String formattedCheckOutDate = targetFormat.format(date);
    
        return checkoutDateElement.getText().equals(formattedCheckOutDate);   
    }

    public void scrollFinishBookingDetails(){
        Utils.scrollUntilElementFound(
            driver,
            By.id(finishBookingModal),
            By.id(taxes)
        );
    }

    
    public boolean isNameCorrect() {
        String expectedValue = String.format("%s %s", user.getFirstName(), user.getLastName());
        return nameElement.getText().equals(expectedValue);
        }
    
    public boolean isEmailCorrect() {
        return emailElement.getText().equals(user.getEmail());
    }
    

    public boolean isAddressCorrect(){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

        List<MobileElement> addressFields = driver.findElements(By.id("com.booking:id/address_zip"));

        if (!addressFields.isEmpty()) {
            String expectedValue = String.format("%s, %s", user.getAddress(), user.getZipCode());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return addressFields.get(0).getText().equals(expectedValue);
        } else {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        }
        
    }

    public boolean isCityCountryCorrect() {

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

        List<MobileElement> cityCountryElement = driver.findElements(By.id("com.booking:id/city_country"));

        if (!cityCountryElement.isEmpty()) {
            String expectedValue = String.format("%s, %s", user.getCity(), user.getCountry());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return cityCountryElement.get(0).getText().equals(expectedValue) || cityCountryElement.get(0).getText().contains(user.getCountry());
        } else {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        }
    }
    
    public boolean isPhoneNumberCorrect() {
        return phoneElement.getText().equals(user.getPhoneNumber());
    }

    public boolean isTotalAmountCorrect(String totalAmount){
        return totalAmountElement.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountCorrect(String taxesAmount){
        return taxesElement.getText().equals(taxesAmount);   
    }

    public boolean isBookNowButtonEnabled() {        
        return bookNowButton.isEnabled();
    }

    public void clickOnBookNowButton(){
        bookNowButton.click();
    }

    public String checkErrorMessage(){
        return cardNumberErrorMessage.getText();
    }
    
}
