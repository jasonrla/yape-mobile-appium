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

public class HomePage extends BasePage {

    private final User user;
    private final Reservation reservation;

    public HomePage(AppiumDriver<MobileElement> driver, User user, Reservation reservation) {
        super(driver);
        this.user = user;
        this.reservation = reservation;
    }

    public boolean isMainPageOpened() {
        WebElement elem = driver.findElement(By.id("com.booking:id/facet_search_box_outline"));   
        return elem.isDisplayed();
    }

    public void enterYourDestination() {
        driver.findElement(By.xpath("//android.widget.TextView[@text='Enter your destination']")).click();
    }

    public boolean isDestinationModalOpened() {
        WebElement elem = driver.findElement(By.id("com.booking:id/facet_with_bui_free_search_booking_header_appbar_layout"));   
        return elem.isDisplayed();
    }

    public void enterCuscoText() {
        driver.findElement(By.id("com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content")).sendKeys(reservation.getDestination());        
    }

    public boolean allItemsContainSearchWord() {
        WebElement mainElement = driver.findElement(By.id("com.booking:id/facet_disambiguation_content"));
        List<WebElement> viewGroups = mainElement.findElements(By.className("android.view.ViewGroup"));
        System.out.println(viewGroups.size());
        boolean allContainCusco = true;
        for (WebElement viewGroup : viewGroups) {
            List<WebElement> textViews = viewGroup.findElements(By.xpath(".//android.widget.TextView[contains(@resource-id,'destination_title')]"));
            List<WebElement> textViewsST = viewGroup.findElements(By.xpath(".//android.widget.TextView[contains(@resource-id,'destination_subtitle') and not(contains(@text,'properties'))]"));
            boolean groupContainsCusco = false;
            for (WebElement textView : textViews) {
                String text = textView.getText();
                System.out.println(text);
                if (text.contains(reservation.getDestination())) {
                    groupContainsCusco = true;
                }
            }
            if (!groupContainsCusco) {
                for (WebElement textViewST : textViewsST) {
                    String textST = textViewST.getText();
                    System.out.println(textST);
                    if (textST.contains(reservation.getDestination())) {
                        groupContainsCusco = true;
                    }
                }
            }
        }
        return allContainCusco;
    }

    public void selectFirstItem() {
        //driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.booking:id/view_disambiguation_destination_title'])[1]")).click();
        driver.findElement(
            By.xpath("(//android.widget.TextView[@text='"+reservation.getDestination()+"'])[1]"))
            .click();
    }

    public boolean nextDateIsSelected(String element) {
        MobileElement elem = driver.findElement(MobileBy.AccessibilityId(element));
        return elem.isSelected();
    }

    public boolean isSelectDateButtonEnabled() {
        WebElement elem = driver.findElement(By.id("com.booking:id/facet_date_picker_confirm"));   
        return elem.isEnabled();
    }

    public boolean isDateRangeCorrect() throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d");
        Date start = inputFormat.parse(reservation.getStartDate());
        Date end = inputFormat.parse(reservation.getEndDate());
        String formattedStartDate = outputFormat.format(start);
        String formattedEndDate = outputFormat.format(end);
        String dateRangeExpected = formattedStartDate + " - " + formattedEndDate;
        
        String dateRange = driver.findElement(By.id("com.booking:id/facet_date_picker_selection_summary")).getText();
        return dateRange.contains(dateRangeExpected);
    }

    public boolean isNumberOfNightsCorrect() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        Date start = format.parse(reservation.getStartDate());
        Date end = format.parse(reservation.getEndDate());
        long differenceInMilliseconds = end.getTime() - start.getTime();
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
        String numNightsExpected = String.valueOf(differenceInDays);

        String numNights = driver.findElement(By.id("com.booking:id/facet_date_picker_selection_summary")).getText();
        return numNights.contains(numNightsExpected + " nights");
    }


    public boolean isCalendarOpened() {
        WebElement elem = driver.findElement(By.id("com.booking:id/facet_with_bottom_sheet_header_content"));   
        return elem.isDisplayed();
    }

    public void scrollInCalendar() {
        Utils.scrollUntilElementFound(
            driver, //By.xpath("//android.widget.FrameLayout[@resource-id='com.booking:id/design_bottom_sheet']/android.widget.LinearLayout"), 
            By.id("com.booking:id/calendar_month_list"),
            MobileBy.AccessibilityId(reservation.getEndDate())
        );
    }

    public void selectStartDate() {
        driver.findElementByAccessibilityId(reservation.getStartDate()).click();    
    }

    public void selectEndDate() {
        driver.findElementByAccessibilityId(reservation.getEndDate()).click();    
    }

    public void clickOnSelectDatesButton() {
        driver.findElement(By.id("com.booking:id/facet_date_picker_confirm")).click();    
    }

    public boolean isDestinationSet() {
        WebElement elem = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.booking:id/facet_search_box_basic_field_label' and @text='" + reservation.getDestination() + "']"));
        return elem.isDisplayed();
    }

    public boolean isRangeDateSet() throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE, MMM dd");

        Date dateObj1 = originalFormat.parse(reservation.getStartDate());
        Date dateObj2 = originalFormat.parse(reservation.getEndDate());

        String formattedStartDate = targetFormat.format(dateObj1);
        String formattedEndDate = targetFormat.format(dateObj2);

        String dateRangeExpected = formattedStartDate + " - " + formattedEndDate;
        
        WebElement elem = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.booking:id/facet_search_box_basic_field_label' and @text='" + dateRangeExpected + "']"));
        return elem.isDisplayed();
    }

    public boolean isNumberQuestsSet() {
        String quests = reservation.getNumRooms()+" room · "+reservation.getNumAdults()+" adults · "+reservation.getNumChild()+" child";
        WebElement elem = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.booking:id/facet_search_box_basic_field_label' and @text='" + quests +"']"));
        return elem.isDisplayed();
    }

    public void clickOnRoomField() {
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+reservation.getNumAdults()+" adults')]")).click();
    }

    public boolean isRoomAndGuestModalOpened() {
        WebElement elem = driver.findElement(By.id("com.booking:id/design_bottom_sheet"));   
        return elem.isDisplayed();
    }

    public void selectChildrenQuantity() {
        driver.findElement(By.xpath("(//android.widget.Button[@resource-id='com.booking:id/bui_input_stepper_add_button'])[3]")).click();
    }

    public boolean isAgeOfChildModalOpened() {
        WebElement elem = driver.findElement(By.id("android:id/parentPanel"));   
        return elem.isDisplayed();
    }

    public void scrollAge() {
        Utils.scrollUntilElementFound(
            driver,
            By.id("com.booking:id/age_picker_view"),
            By.xpath("//android.widget.EditText[@text='"+reservation.getAgeChildren()[0]+" years old']")
        );
    }

    public void clickOn5yearsOldOption() {
        driver.findElement(By.xpath("//android.widget.EditText[@text='"+reservation.getAgeChildren()[0]+" years old']")).click();
    }

    public void clickOnOKbutton(){
        driver.findElement(By.xpath("//android.widget.ScrollView[@resource-id='android:id/buttonPanel']/android.widget.LinearLayout"));
        driver.findElement(By.id("android:id/button1")).click();
    }

    public boolean isChildrensAgeAtCheckOutDisplayed() {
        WebElement elem = driver.findElement(By.id("com.booking:id/group_config_children_ages_header"));   
        return elem.isDisplayed();
    }

    public boolean isNumberOfChildrenCorrect() {
        List<MobileElement> elems = driver.findElements(By.id("com.booking:id/group_config_children_ages_recycler_view"));   
        return String.valueOf(elems.size()).equals(String.valueOf(reservation.getNumChild()));
    }

    public WebElement[] getChildrenElements(int childIndex) {
        WebElement mainElement = driver.findElement(By.id("com.booking:id/group_config_children_ages_recycler_view"));
        WebElement linearLayout = mainElement.findElements(By.className("android.widget.LinearLayout")).get(childIndex);
    
        WebElement childNumElement = linearLayout.findElement(By.xpath(".//android.widget.TextView[contains(@text,'Child')]"));
        WebElement childAgeElement = linearLayout.findElement(By.xpath(".//android.widget.TextView[contains(@text,'old')]"));
    
        return new WebElement[]{childNumElement, childAgeElement};
    }
    
    public boolean isChildrensAgeSelectedCorrect() {
        boolean childDataCorrect = true;
    
        for (int i = 0; i < reservation.getNumChild(); i++) {
            String childNumExpected = "Child " + (i + 1);
            String childAgeExpected = reservation.getAgeChildren()[i] + " years old";
    
            WebElement[] childrenElements = getChildrenElements(i);
            WebElement childNumElement = childrenElements[0];
            WebElement childAgeElement = childrenElements[1];
    
            if (!childNumElement.getText().equals(childNumExpected) || !childAgeElement.getText().equals(childAgeExpected)) {
                childDataCorrect = false;
                break;
            }
        }
    
        return childDataCorrect;
    }

    public boolean isApplyButtonEnabled() {
        WebElement elem = driver.findElement(By.id("com.booking:id/group_config_apply_button"));   
        return elem.isEnabled();
    }

    public void clickOnApplyButton(){
        driver.findElement(By.id("com.booking:id/group_config_apply_button")).click();
    }

    public void clickOnSearchButton(){
        driver.findElement(By.id("com.booking:id/facet_search_box_cta")).click();
    }
/*

    

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
 */
}  