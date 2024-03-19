package stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.yape_mobile.pages.BookingOverviewPage;
import com.yape_mobile.pages.FinishBooking;
import com.yape_mobile.pages.HomePage;
import com.yape_mobile.pages.PropertyDetailsPage;
import com.yape_mobile.pages.RegistrationPage;
import com.yape_mobile.pages.RoomSelectionPage;
import com.yape_mobile.pages.SearchResultsPage;
import com.yape_mobile.pages.SignInPage;

import config.TestSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeStepDef {
    private AppiumDriver<MobileElement> driver;
    private SignInPage loginPage;
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    
    @When("deciding not to sign in")
    public void deciding_not_to_sign_in() {
        loginPage.closeLoginPage();
    }

    @Then("the main page should be opened")
    public void the_main_page_should_be_opened() {
        Assert.assertEquals(true, homePage.isMainPageOpened());
        Assert.assertEquals(true, homePage.isSearchButtonEnabled());
    }

    @Then("destination message should be displayed")
    public void destination_message_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("default date range should be set")
    public void default_date_range_should_be_set() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("default guest options should be set")
    public void default_guest_options_should_be_set() {
        // Write code here that turns the phrase above into concrete actions
    }

}
