package com.yape_mobile.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class RoomSelectionPage extends BasePage {

    @FindBy(id = "com.booking:id/recommended_block_subtotal_price_view")
    private WebElement totalAmountElement;

    @FindBy(id = "com.booking:id/recommended_block_subtotal_taxes_charges_view")
    private WebElement taxesAmountElement;

    @FindBy(id = "com.booking:id/recommended_block_reserve_button")
    private WebElement reserveButton;

    @FindBy(id = "com.booking:id/select_room_cta")
    private WebElement selectRoomsButton;

    @FindBy(id = "com.booking:id/rooms_item_select_text_view")
    private WebElement selectAndCustomizeButton;

    @FindBy(id = "com.booking:id/rooms_item_select_text_view")
    private WebElement selectButton;

    @FindBy(id = "com.booking:id/room_pref_select")
    private WebElement confirmButton;

    @FindBy(xpath = "//android.widget.Button[@resource-id='com.booking:id/main_action']")
    private WebElement reserveWidgetButton;

    public RoomSelectionPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isTotalAmountCorrect(String totalAmount){
        return totalAmountElement.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountCorrect(String taxes){
        return taxesAmountElement.getText().equals(taxes);   
    }

    public boolean isReserveButtonDisplayed(){
        return reserveButton.isDisplayed();
    }

    public void clickOnReserveButton(){
        reserveButton.click();;
    }

    public boolean isSelectRoomsButtonDisplayed(){
        return selectRoomsButton.isDisplayed();
    }

    public void clickOnSelectRoomsButton(){
        selectRoomsButton.click();
    }

    public boolean isSelectAndCustomizeButtonDisplayed(){
        return selectAndCustomizeButton.isDisplayed();
    }

    public void clickOnSelectAndCustomizeButton(){
        selectAndCustomizeButton.click();
    }

    public boolean isSelectButtonDisplayed(){
        return selectButton.isDisplayed();
    }

    public void clickOnSelectButton(){
        selectButton.click();
    }

    public boolean isConfirmButtonDisplayed(){
        return confirmButton.isDisplayed();
    }

    public void clickOnConfirmButton(){
        confirmButton.click();
    }

    public boolean isReserveWidgetButtonDisplayed(){
        return reserveWidgetButton.isDisplayed();
    }

    public void clickOnReserveWidgetButton(){
        reserveWidgetButton.click();
    }

    public void selectButton(){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if(isSelectButtonDisplayed()){
            clickOnSelectButton();
        }
    }

    public void reserveButton(){
        if(isReserveButtonDisplayed()){
            clickOnReserveButton();
        }
    }

    public void confirmButton(){
        if(isConfirmButtonDisplayed()){
            clickOnConfirmButton();
        }
    }

    public void reserveWidgetButton(){
        if(isReserveWidgetButtonDisplayed()){
            clickOnReserveWidgetButton();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
