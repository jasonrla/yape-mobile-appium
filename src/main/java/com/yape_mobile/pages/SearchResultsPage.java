package com.yape_mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.yape_mobile.model.Reservation;

public class SearchResultsPage extends BasePage {

    private final Reservation reservation;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[2]")
    private WebElement propertiesList;

    private String propertiesModal= "//androidx.recyclerview.widget.RecyclerView";
    private String imageViewSelector = "//androidx.recyclerview.widget.RecyclerView//android.widget.ImageView";

    //private String propertyItem = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[REPLACE-TEXT]";
    private String propertyItem = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[REPLACE-TEXT]";
    private String stayDetails = "//./android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[5]/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[1]"; //"(//android.widget.TextView[@resource-id='com.booking:id/price_view_stay_details'])[REPLACE-TEXT]";
    private String originalAmount = "(//android.widget.TextView[@resource-id='com.booking:id/price_view_rack_rate'])[REPLACE-TEXT]";
    private String amount = "//./android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[5]/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView";//"(//android.widget.TextView[@resource-id='com.booking:id/price_view_price'])[REPLACE-TEXT]";
    private String taxesAndCharges = "//./android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[5]/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[2]"; //"(//android.widget.TextView[@resource-id='com.booking:id/price_view_taxes_and_charges'])[REPLACE-TEXT]";
    private String secondOption = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[REPLACE-TEXT]";

    public SearchResultsPage(AppiumDriver<MobileElement> driver, Reservation reservation) {
        super(driver);
        this.reservation = reservation;
        PageFactory.initElements(driver, this);
    }

    public boolean isPropertiesListDisplayed() {
        return propertiesList.isDisplayed();
    }

    public boolean isDesiredElementDisplayed() {
        return driver.findElement(By.xpath(propertyItem.replace("REPLACE-TEXT", String.valueOf(reservation.getItemNumber())))).isDisplayed();
    }

    public void scrollUntilDesiredItem(){

        Utils.scrollDown(driver, By.xpath(propertiesModal));
        // System.out.println("Scrolling");
        // Utils.scrollUntilThreeImagesFound(
        //     driver,
        //     By.xpath(propertiesModal),
        //     By.xpath(imageViewSelector)
        // );
        // System.out.println("Item found");
        //Utils.selectItemAtIndex(driver, By.xpath(propertiesModal), reservation.getItemNumber());

    }

    public String extractStayDetailsText() {
        return driver.findElement(
            Utils.getElementBy(stayDetails, String.valueOf(reservation.getItemNumber()))).getText();
    }
    
    public String extractOriginalAmountText() {
        return driver.findElement(
            Utils.getElementBy(originalAmount, String.valueOf(reservation.getItemNumber()))).getText();
    }

    public boolean isOriginalAmountAvailable() {
        return Utils.getElement(
            driver, originalAmount, String.valueOf(reservation.getItemNumber())).isDisplayed();
    }

    public String extractAmountText() {
        return driver.findElement(
            Utils.getElementBy(amount, String.valueOf(reservation.getItemNumber()))).getText();
    }
    
    public String extractTaxesText() {
        return driver.findElement(
            Utils.getElementBy(taxesAndCharges, String.valueOf(reservation.getItemNumber()))).getText();
    }
    
    public void selectDesiredOption(){
        driver.findElement(
            Utils.getElementBy(secondOption, String.valueOf(reservation.getItemNumber()))).click();
    }

}  