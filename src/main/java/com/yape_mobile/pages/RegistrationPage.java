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

public class RegistrationPage extends BasePage{

    private final User user;
    private final Reservation reservation;

    public RegistrationPage(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    public void enterFirstName(){
        //driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.booking:id/bui_input_container_content'])[1]")).sendKeys("Jason");
        driver.findElement(
            By.xpath("//android.widget.TextView[contains(@text,'First Name')]/following-sibling::android.widget.EditText"))
            .sendKeys(user.getFirstName());
    }

    public void enterLastName(){
        //driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.booking:id/bui_input_container_content'])[2]")).sendKeys("Lopez");
        driver.findElement(
            By.xpath("//android.widget.TextView[contains(@text,'Last Name')]/following-sibling::android.widget.EditText"))
            .sendKeys(user.getLastName());
    }

    public void enterEmail(){
        //driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id='com.booking:id/bui_input_container_content']")).sendKeys("jasonrla@gmail.com");
        driver.findElement(
            By.xpath("//android.widget.TextView[contains(@text,'Email')]/following-sibling::android.widget.AutoCompleteTextView"))
            .sendKeys(user.getEmail());
    }

    public void enterAddress(){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

        //driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.booking:id/bui_input_container_content'])[3]")).sendKeys("Testing ave");
        By addressFieldSelector = By.xpath("//android.widget.TextView[contains(@text,'Address')]/following-sibling::android.widget.EditText");
        List<MobileElement> addressFields = driver.findElements(addressFieldSelector);

        if (!addressFields.isEmpty()) {
            addressFields.get(0).sendKeys(user.getAddress());
        } else {
            System.out.println("Address field is not present");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    //valida que si no tiene @ no es email -> boton Add missing details
    //id com.booking:id/bui_input_container_helper_label -> mensaje error: Please enter your email address.


    public void enterZipCode(){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

        //driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.booking:id/bui_input_container_content'])[4]")).sendKeys("15048");
        By zipCodeFieldSelector = By.xpath("//android.widget.TextView[contains(@text,'Zip Code')]/following-sibling::android.widget.EditText");
        List<MobileElement> zipCodeFields = driver.findElements(zipCodeFieldSelector);

        if (!zipCodeFields.isEmpty()) {
            zipCodeFields.get(0).sendKeys(user.getZipCode());
        } else {
            System.out.println("Zip Code field is not present");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void enterCity(){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

        //driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.booking:id/bui_input_container_content'])[5]")).sendKeys("Lima");
        By cityFieldSelector = By.xpath("//android.widget.TextView[contains(@text,'City')]/following-sibling::android.widget.EditText");
        List<MobileElement> cityFields = driver.findElements(cityFieldSelector);
    
        if (!cityFields.isEmpty()) {
            cityFields.get(0).sendKeys(user.getCity());
        } else {
            System.out.println("City field is not present");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void scrollForm(){
        Utils.scrollUntilElementFound(
            driver,
            By.xpath("//android.widget.LinearLayout[@resource-id='com.booking:id/bstage1_contact_layout']"),
            By.id("com.booking:id/business_purpose_leisure")
        );
    }

    public void selectCountry(){
        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Country/Region')]/following-sibling::android.widget.AutoCompleteTextView"));
        element.clear();
        element.sendKeys(user.getCountry());
    }

    public void enterPhoneNumber(){
        //driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.booking:id/bui_input_container_content'])[4]")).sendKeys("987654321");
        driver.findElement(
            By.xpath("//android.widget.TextView[contains(@text,'Mobile Phone')]/following-sibling::android.widget.EditText"))
            .sendKeys(user.getPhoneNumber());
    }


    public void selectPurpouseOption(){
        //driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.booking:id/informative_click_to_action_container']/android.widget.LinearLayout/android.widget.LinearLayout"));
        //driver.findElement(By.id("com.booking:id/business_purpose_leisure")).click();
        driver.findElement(
            By.xpath("//android.widget.RadioButton[@text='"+reservation.getPurpose()+"']"))
            .click();
        System.out.println("Purpose: " + reservation.getPurpose() + " selected");
    }

    public boolean isTotalAmountInFillInfoDetailsCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/title"));
        System.out.println("total amount Fill Info: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountInFillInfoDetailsCorrect(String taxes){
        WebElement elem = driver.findElement(By.id("com.booking:id/subtitle"));
        System.out.println("taxe amount Fill Info: " + elem.getText() + " expected: " + taxes);
        return elem.getText().equals(taxes);   
    }

    public boolean isAddMissingDetailsButtonDisplayed() {
        String buttonText = driver.findElement(By.id("com.booking:id/action_button")).getText();   
        System.out.println("button text: " + buttonText);
        return buttonText.equals("Add missing details");
    }

    // com.booking:id/action_button   actionbutton Add missing details -> Next step
    public boolean isNextStepButtonEnabled() {
        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.booking:id/informative_click_to_action_container']/android.widget.LinearLayout/android.widget.LinearLayout"));
        WebElement elem = driver.findElement(By.id("com.booking:id/action_button"));   
        return elem.isEnabled();
    }

    public boolean isNextStepButtonDisplayed() {
        String buttonText = driver.findElement(By.id("com.booking:id/action_button")).getText();   
        System.out.println("button text: " + buttonText);
        return buttonText.equals("Next step");
    }

    public void clickOnNextStepButton(){
        driver.findElement(By.id("com.booking:id/action_button")).click();
    }

}
