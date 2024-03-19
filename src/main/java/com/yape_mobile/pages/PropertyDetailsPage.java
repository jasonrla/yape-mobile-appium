package com.yape_mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PropertyDetailsPage extends BasePage{

    @FindBy(id = "com.booking:id/fragment_container")
    private WebElement roomModalElement;

    @FindBy(id = "com.booking:id/price_view_taxes_and_charges")
    private WebElement taxesAmountElement;

    @FindBy(id = "com.booking:id/price_view_price")
    private WebElement totalAmountElement;

    @FindBy(id = "com.booking:id/price_view_rack_rate")
    private WebElement totalOriginalAmountElement;

    @FindBy(id = "com.booking:id/select_room_cta")
    private WebElement roomButtonElement;

    public PropertyDetailsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSelectRoomModalOpened() {
        return roomModalElement.isDisplayed();
    }

    public boolean isTotalOriginalAmountDisplayed(){
        return totalOriginalAmountElement.isDisplayed();  
    }

    public boolean isTotalOriginalAmountCorrect(String totalOriginalAmount){
        return totalOriginalAmountElement.getText().equals(totalOriginalAmount);   
    }

    public boolean isTotalAmountCorrect(String totalAmount){
        return totalAmountElement.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountCorrect(String taxes){
        return taxesAmountElement.getText().equals(taxes);   
    }

    public void clickOnSelectRoomsButton(){
        roomButtonElement.click();
    }

}
