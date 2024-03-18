package stepdefinitions;

import org.junit.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;

import com.yape_mobile.pages.LoginPage;
import com.yape_mobile.pages.HomePage;
import com.yape_mobile.pages.SearchResultsPage;
import com.yape_mobile.pages.PropertyDetailsPage;
import com.yape_mobile.pages.RoomSelectionPage;
import com.yape_mobile.pages.RegistrationPage;
import com.yape_mobile.pages.BookingOverviewPage;
import com.yape_mobile.pages.FinishBooking;

import config.TestSetup;

public class LoginStepDef {
    private AppiumDriver<MobileElement> driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private PropertyDetailsPage propertyDetailsPage;
    private RoomSelectionPage roomSelectionPage;
    private RegistrationPage registrationPage;
    private BookingOverviewPage bookingOverviewPage;
    private FinishBooking finishBooking;

    private String stayDetails;
    private String totalAmount;
    private String taxesAmount;

    @Before
    public void setUp() throws Exception {
        TestSetup setup = new TestSetup();
        driver = setup.getDriver();
        loginPage = setup.getLoginPage();
        homePage = setup.getHomePage();
        searchResultsPage = setup.getSearchResultsPage();
        propertyDetailsPage = setup.getPropertyDetailsPage();
        roomSelectionPage = setup.getRoomSelectionPage();
        registrationPage = setup.getRegistrationPage();
        bookingOverviewPage = setup.getBookingOverviewPage();
        finishBooking = setup.getFinishBooking();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on the login page")
    public void I_am_on_the_login_page() {
        //Assert.assertEquals(true, loginPage.isLoginPageOpened());
    }

    @When("I click on the X button")
    public void i_click_on_the_x_button() {
        loginPage.clickOnXButton();
    }

    @Then("The main page should be opened")
    public void the_main_page_should_be_opened() {
        Assert.assertEquals(true, homePage.isMainPageOpened());
    }

    @When("I enter my destination")
    public void i_enter_my_destination() {
        homePage.enterYourDestination();
    }

    @Then("The destination modal should be opened")
    public void the_destination_modal_should_be_opened() {
        Assert.assertEquals(true, homePage.isDestinationModalOpened());
    }

    @When("I enter Cusco text")
    public void i_enter_cusco_text() {
        homePage.enterCuscoText();
    }

    @When("All items contain the search word")
    public void All_items_contain_the_search_word() {
        Assert.assertEquals(true, homePage.allItemsContainSearchWord());
    }

    @When("I select the first item")
    public void I_select_the_first_item() {
        homePage.selectFirstItem();
    }

    @Then("The calendar should be opened")
    public void The_calendar_should_be_opened() {
        Assert.assertEquals(true, homePage.isCalendarOpened());
    }

    @Then("I scroll in the calendar")
    public void I_scroll_in_the_calendar() {
        homePage.scrollInCalendar();
    }

    @Then("I select the start date")
    public void I_select_the_start_date() {
        homePage.selectStartDate();
    }

    @Then("The select date button should be disabled")
    public void The_select_date_button_should_be_disabled() {
        Assert.assertEquals(false, homePage.isSelectDateButtonEnabled());
    }

    @Then("I select the end date")
    public void I_select_the_end_date() {
        homePage.selectEndDate();
    }

    @Then("The select date button should be enabled")
    public void The_select_date_button_should_be_enabled() {
        Assert.assertEquals(true, homePage.isSelectDateButtonEnabled());
    }

    @Then("The date range should be correct")
    public void The_date_range_should_be_correct() throws ParseException {
        Assert.assertEquals(true, homePage.isDateRangeCorrect());
    }

    @Then("The number of nights should be correct")
    public void The_number_of_nights_should_be_correct() throws ParseException {
        Assert.assertEquals(true, homePage.isNumberOfNightsCorrect());
    }

    @Then("I click on the select dates button")
    public void I_click_on_the_select_dates_button() {
        homePage.clickOnSelectDatesButton();
    }

    @Then("The destination should be set")
    public void The_destination_should_be_set() {
        Assert.assertEquals(true, homePage.isDestinationSet());
    }

    @Then("The range date should be set")
    public void The_range_date_should_be_set() throws ParseException {
        Assert.assertEquals(true, homePage.isRangeDateSet());
    }

    @Then("I click on the room field")
    public void I_click_on_the_room_field() {
        homePage.clickOnRoomField();
    }

    @Then("The room and guest modal should be opened")
    public void The_room_and_guest_modal_should_be_opened() {
        Assert.assertEquals(true, homePage.isRoomAndGuestModalOpened());
    }

    @Then("I select the children quantity")
    public void I_select_the_children_quantity() {
        homePage.selectChildrenQuantity();
    }

    @Then("The age of child modal should be opened")
    public void The_age_of_child_modal_should_be_opened() {
        Assert.assertEquals(true, homePage.isAgeOfChildModalOpened());
    }

    @Then("I scroll the age")
    public void I_scroll_the_age() {
        homePage.scrollAge();
    }

    @Then("I click on the {int} years old option")
    public void I_click_on_the_years_old_option(int i) {
        homePage.clickOn5yearsOldOption();
    }

    @Then("I click on the OK button")
    public void I_click_on_the_OK_button() {
        homePage.clickOnOKbutton();
    }

    @Then("The childrens age at check out should be displayed")
    public void The_children_s_age_at_check_out_should_be_displayed() {
        Assert.assertEquals(true, homePage.isChildrensAgeAtCheckOutDisplayed());
    }

    @Then("The number of children should be correct")
    public void The_number_of_children_should_be_correct() {
        Assert.assertEquals(true, homePage.isNumberOfChildrenCorrect());
    }

    @Then("The childrens age selected should be correct")
    public void The_childrens_age_selected_should_be_correct() {
        Assert.assertEquals(true, homePage.isChildrensAgeSelectedCorrect());
    }

    @Then("The apply button should be enabled")
    public void The_apply_button_should_be_enabled() {
        Assert.assertEquals(true, homePage.isApplyButtonEnabled());
    }

    @Then("I click on the apply button")
    public void I_click_on_the_apply_button() {
        homePage.clickOnApplyButton();
    }

    @Then("The number of guests should be set")
    public void The_number_of_guests_should_be_set() {
        Assert.assertEquals(true, homePage.isNumberQuestsSet());
    }

    @Then("I click on the search button")
    public void I_click_on_the_search_button() {
        homePage.clickOnSearchButton();
    }

    @Then("The properties list should be displayed")
    public void The_properties_list_should_be_displayed() {
        Assert.assertEquals(true, searchResultsPage.isPropertiesListDisplayed());
    }

    @Then("I scroll until the item number")
    public void I_scroll_until_the_item_number() {
        searchResultsPage.scrollUntilItemNumber();
    }

    @Then("I extract the stay details text")
    public void I_extract_the_stay_details_text() {
        stayDetails = searchResultsPage.extractStayDetailsText();
        System.out.println("Stay details: " + stayDetails);
    }

    @Then("I extract the amount text")
    public void I_extract_the_amount_text() {
        totalAmount = searchResultsPage.extractAmountText();
        System.out.println("Total amount: " + totalAmount);
    }

    @Then("I extract the taxes and charges text")
    public void I_extract_the_taxes_and_charges_text() {
        taxesAmount = searchResultsPage.extractTaxesAndChargesText();
        System.out.println("Taxes amount: " + taxesAmount);
    }

    @Then("I select the second option")
    public void I_select_the_second_option() {
        searchResultsPage.selectSecondOption();
    }

    @Then("The select room modal should be opened")
    public void The_select_room_modal_should_be_opened() {
        Assert.assertEquals(true, propertyDetailsPage.isSelectRoomModalOpened());
    }

    @Then("The total amount in property details should be correct")
    public void The_total_amount_in_property_details_should_be_correct() {
        Assert.assertEquals(true, propertyDetailsPage.isTotalAmountInPropertyDetailsCorrect(totalAmount));
    }

    @Then("The taxes amount in property details should be correct")
    public void The_taxes_amount_in_property_details_should_be_correct() {
        Assert.assertEquals(true, propertyDetailsPage.isTaxesAmountInPropertyDetailsCorrect(taxesAmount));
    }

    @Then("I select the room")
    public void I_select_the_room() {
        propertyDetailsPage.selectRoom();
    }

    @Then("The total amount in room details should be correct")
    public void The_total_amount_in_room_details_should_be_correct() {
        Assert.assertEquals(true, roomSelectionPage.isTotalAmountInRoomDetailsCorrect(totalAmount));
    }

    @Then("The taxes amount in room details should be correct")
    public void The_taxes_amount_in_room_details_should_be_correct() {
        Assert.assertEquals(true, roomSelectionPage.isTaxesAmountInRoomDetailsCorrect(taxesAmount));
    }

    @When("I click on the reserve button")
    public void I_click_on_the_reserve_button() {
        roomSelectionPage.clickOnReserveButton();
    }

    @Then("The add missing details button should be displayed")
    public void The_add_missing_details_button_should_be_displayed() {
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());
    }

    @When("I enter the first name")
    public void I_enter_the_first_name() {
        registrationPage.enterFirstName();
    }

    @When("I enter the last name")
    public void I_enter_the_last_name() {
        registrationPage.enterLastName();
    }

    @When("I enter the email")
    public void I_enter_the_email() {
        registrationPage.enterEmail();
    }

    @When("I enter the address")
    public void I_enter_the_address() {
        registrationPage.enterAddress();
    }

    @When("I enter the zip code")
    public void I_enter_the_zip_code() {
        registrationPage.enterZipCode();
    }

    @When("I enter the city")
    public void I_enter_the_city() {
        registrationPage.enterCity();
    }

    @When("I scroll the form")
    public void I_scroll_the_form() {
        registrationPage.scrollForm();
    }

    @When("I select the country")
    public void I_select_the_country() {
        registrationPage.selectCountry();
    }

    @When("I enter the phone number")
    public void I_enter_the_phone_number() {
        registrationPage.enterPhoneNumber();
    }

    @Then("The next step button should be displayed")
    public void The_next_step_button_should_be_displayed() {
        Assert.assertEquals(true, registrationPage.isNextStepButtonDisplayed());
    }
    
    @When("I select the purpose option")
    public void I_select_the_purpose_option() {
        registrationPage.selectPurpouseOption();
    }

    @Then("The total amount in fill info details should be correct")
    public void The_total_amount_in_fill_info_details_should_be_correct() {
        Assert.assertEquals(true, registrationPage.isTotalAmountInFillInfoDetailsCorrect(totalAmount));
    }

    @Then("The taxes amount in fill info details should be correct")
    public void The_taxes_amount_in_fill_info_details_should_be_correct() {
        Assert.assertEquals(true, registrationPage.isTaxesAmountInFillInfoDetailsCorrect(taxesAmount));
    }

    @Then("The next step button should be enabled")
    public void The_next_step_button_should_be_enabled() {
        Assert.assertEquals(true, registrationPage.isNextStepButtonEnabled());
    }

    @When("I click on the next step button")
    public void I_click_on_the_next_step_button() {
        registrationPage.clickOnNextStepButton();
    }
    
    @Then("The check in date should be correct")
    public void The_check_in_date_should_be_correct() throws Exception {
        Assert.assertEquals(true, bookingOverviewPage.isCheckInDateCorrect());
    }

    @Then("The check out date should be correct")
    public void The_check_out_date_should_be_correct() throws Exception {
        Assert.assertEquals(true, bookingOverviewPage.isCheckOutDateCorrect());
    }

    @Then("The booking overview total amount should be correct")
    public void The_booking_overview_total_amount_should_be_correct() {
        Assert.assertEquals(true, bookingOverviewPage.isBookingOverviewTotalAmountCorrect(totalAmount));
    }

    @Then("The booking overview taxes amount should be correct")
    public void The_booking_overview_taxes_amount_should_be_correct() {
        Assert.assertEquals(true, bookingOverviewPage.isBookingOverviewTaxesAmountCorrect(taxesAmount));
    }

    @Then("The total amount should be correct")
    public void The_total_amount_should_be_correct() {
        Assert.assertEquals(true, bookingOverviewPage.isTotalAmountCorrect(totalAmount));
    }

    @Then("The taxes amount should be correct")
    public void The_taxes_amount_should_be_correct() {
        Assert.assertEquals(true, bookingOverviewPage.isTaxesAmountCorrect(taxesAmount));
    }

    @When("I click on the final step button")
    public void I_click_on_the_final_step_button() {
        bookingOverviewPage.clickOnFinalStepButton();
    }

    @When("I enter the card number")
    public void I_enter_the_card_number() {
        finishBooking.enterCardNumber();
    }

    @Then("The holder name should be correct")
    public void The_holder_name_should_be_correct() {
        Assert.assertEquals(true, finishBooking.checkHolderNameIsCorrect());
    }

    @When("I enter the expiration date")
    public void I_enter_the_expiration_date() {
        finishBooking.enterExpirationDate();
    }

    @Then("The total amount footer should be correct")
    public void The_total_amount_footer_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isTotalAmountFooterCorrect(totalAmount));
    }

    @Then("The taxes amount footer should be correct")
    public void The_taxes_amount_footer_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isTaxesAmountFooterCorrect(taxesAmount));
    }

    @When("I scroll to dates")
    public void I_scroll_to_dates() {
        finishBooking.scrollToDates();
    }

    @Then("The check in date 2 should be correct")
    public void The_check_in_date_2_should_be_correct() throws Exception {
        Assert.assertEquals(true, finishBooking.isCheckInDate2Correct());
    }

    @Then("The check out date 2 should be correct")
    public void The_check_out_date_2_should_be_correct() throws Exception {
        Assert.assertEquals(true, finishBooking.isCheckOutDate2Correct());
    }

    @When("I scroll the finish booking details")
    public void I_scroll_the_finish_booking_details() {
        finishBooking.scrollFinishBookingDetails();
    }

    @Then("The name should be correct")
    public void The_name_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isNameCorrect());
    }

    @Then("The email should be correct")
    public void The_email_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isEmailCorrect());
    }

    @Then("The address should be correct")
    public void The_address_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isAddressCorrect());
    }

    @Then("The city and country should be correct")
    public void The_city_and_country_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isCityCountryCorrect());
    }

    @Then("The phone number should be correct")
    public void The_phone_number_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isPhoneNumberCorrect());
    }

    @Then("The booking overview total 2 amount should be correct")
    public void The_booking_overview_total_2_amount_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isBookingOverviewTotal2AmountCorrect(totalAmount));
    }

    @Then("The booking overview taxes 2 amount should be correct")
    public void The_booking_overview_taxes_2_amount_should_be_correct() {
        Assert.assertEquals(true, finishBooking.isBookingOverviewTaxes2AmountCorrect(taxesAmount));
    }

    @Then("The book now button should be enabled")
    public void The_book_now_button_should_be_enabled() {
        Assert.assertEquals(true, finishBooking.isBookNowButtonEnabled());
    }

    @When("I click on the book now button")
    public void I_click_on_the_book_now_button() {
        finishBooking.clickOnBookNowButton();
    }

    @Then("The error message should be {string}")
    public void The_error_message_should_be(String errorMessage) {
        String message = finishBooking.checkErrorMessage();
        Assert.assertEquals(errorMessage, message);
    }

    


    /* 

    
        Assert.assertEquals(true, roomSelectionPage.isTotalAmountInRoomDetailsCorrect(totalAmount));
        Assert.assertEquals(true, roomSelectionPage.isTaxesAmountInRoomDetailsCorrect(taxesAmount));

        roomSelectionPage.clickOnReserveButton();

        registrationPage.enterFirstName();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());

        registrationPage.enterLastName();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());

        registrationPage.enterEmail();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());

        registrationPage.enterAddress();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());

        registrationPage.enterZipCode();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());

        registrationPage.enterCity();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());
        
        registrationPage.scrollForm();

        registrationPage.selectCountry();
        Assert.assertEquals(true, registrationPage.isAddMissingDetailsButtonDisplayed());

        registrationPage.enterPhoneNumber();
        Assert.assertEquals(true, registrationPage.isNextStepButtonDisplayed());
        
        registrationPage.selectPurpouseOption();

        Assert.assertEquals(true, registrationPage.isTotalAmountInFillInfoDetailsCorrect(totalAmount));
        Assert.assertEquals(true, registrationPage.isTaxesAmountInFillInfoDetailsCorrect(taxesAmount));

        Assert.assertEquals(true, registrationPage.isNextStepButtonEnabled());
    
        registrationPage.clickOnNextStepButton();

        Assert.assertEquals(true, bookingOverviewPage.isCheckInDateCorrect());
        Assert.assertEquals(true, bookingOverviewPage.isCheckOutDateCorrect());

        Assert.assertEquals(true, bookingOverviewPage.isBookingOverviewTotalAmountCorrect(totalAmount));
        Assert.assertEquals(true, bookingOverviewPage.isBookingOverviewTaxesAmountCorrect(taxesAmount));

        Assert.assertEquals(true, bookingOverviewPage.isTotalAmountCorrect(totalAmount));
        Assert.assertEquals(true, bookingOverviewPage.isTaxesAmountCorrect(taxesAmount));

        bookingOverviewPage.clickOnFinalStepButton();
        
        finishBooking.enterCardNumber();
        Assert.assertEquals(true, finishBooking.checkHolderNameIsCorrect());
        finishBooking.enterExpirationDate();

        Assert.assertEquals(true, finishBooking.isTotalAmountFooterCorrect(totalAmount));
        Assert.assertEquals(true, finishBooking.isTaxesAmountFooterCorrect(taxesAmount));

        finishBooking.scrollToDates();

        Assert.assertEquals(true, finishBooking.isCheckInDate2Correct());
        Assert.assertEquals(true, finishBooking.isCheckOutDate2Correct());

        finishBooking.scrollFinishBookingDetails();

        Assert.assertEquals(true, finishBooking.isNameCorrect());
        Assert.assertEquals(true, finishBooking.isEmailCorrect());
        Assert.assertEquals(true, finishBooking.isAddressCorrect());
        Assert.assertEquals(true, finishBooking.isCityCountryCorrect());
        Assert.assertEquals(true, finishBooking.isPhoneNumberCorrect());

        Assert.assertEquals(true, finishBooking.isBookingOverviewTotal2AmountCorrect(totalAmount));
        Assert.assertEquals(true, finishBooking.isBookingOverviewTaxes2AmountCorrect(taxesAmount));

        Assert.assertEquals(true, finishBooking.isBookNowButtonEnabled());

        finishBooking.clickOnBookNowButton();

        String message = finishBooking.checkErrorMessage();
        Assert.assertEquals("Card number is invalid", message);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    */
}