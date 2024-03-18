package com.yape_mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class RoomSelectionPage extends BasePage {

    private final User user;
    private final Reservation reservation;

    public RoomSelectionPage(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    public boolean isTotalAmountInRoomDetailsCorrect(String totalAmount){
        WebElement elem = driver.findElement(By.id("com.booking:id/recommended_block_subtotal_price_view"));
        System.out.println("total amount Room: " + elem.getText() + " expected: " + totalAmount);
        return elem.getText().equals(totalAmount);   
    }

    public boolean isTaxesAmountInRoomDetailsCorrect(String taxes){
        WebElement elem = driver.findElement(By.id("com.booking:id/recommended_block_subtotal_taxes_charges_view"));
        System.out.println("taxe amount Room: " + elem.getText() + " expected: " + taxes);
        return elem.getText().equals(taxes);   
    }

    //validar fechas room details
    //xpath -> //android.widget.TextView[contains(@text,"Choose Your Stay")]
    // Choose Your Stay
    //May 7 - May 14
    
    public void clickOnReserveButton(){
        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.booking:id/rooms_recycler_view']/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]"));
        driver.findElement(By.id("com.booking:id/recommended_block_reserve_button")).click();
    }
}
