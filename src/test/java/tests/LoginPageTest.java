/*package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.yape_mobile.pages.LoginPage;
import com.yape_mobile.pages.HomePage;
import com.yape_mobile.pages.SearchResultsPage;
import com.yape_mobile.pages.PropertyDetailsPage;
import com.yape_mobile.pages.RoomSelectionPage;
import com.yape_mobile.pages.RegistrationPage;
import com.yape_mobile.pages.BookingOverviewPage;
import com.yape_mobile.pages.FinishBooking;

import config.TestSetup;

public class LoginPageTest {
    private AppiumDriver<MobileElement> driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private PropertyDetailsPage propertyDetailsPage;
    private RoomSelectionPage roomSelectionPage;
    private RegistrationPage registrationPage;
    private BookingOverviewPage bookingOverviewPage;
    private FinishBooking finishBooking;

    @Before
    public void setUp() throws MalformedURLException, FileNotFoundException, URISyntaxException {
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

    @Test
    public void testLogin() throws Exception {
        
        loginPage.clickOnXButton();

        Assert.assertEquals(true, homePage.isMainPageOpened());

        homePage.enterYourDestination();

        Assert.assertEquals(true, homePage.isDestinationModalOpened());

        homePage.enterCuscoText();

        Assert.assertEquals(true, homePage.allItemsContainSearchWord());

        homePage.selectFirstItem();

        Assert.assertEquals(true, homePage.isCalendarOpened());
        
        homePage.scrollInCalendar();
        homePage.selectStartDate();

        Assert.assertEquals(false, homePage.isSelectDateButtonEnabled());

        homePage.selectEndDate();

        Assert.assertEquals(true, homePage.isSelectDateButtonEnabled());

        Assert.assertEquals(true, homePage.isDateRangeCorrect());
        Assert.assertEquals(true, homePage.isNumberOfNightsCorrect());

        homePage.clickOnSelectDatesButton();

        Assert.assertEquals(true, homePage.isDestinationSet());
        Assert.assertEquals(true, homePage.isRangeDateSet());

        homePage.clickOnRoomField();

        Assert.assertEquals(true, homePage.isRoomAndGuestModalOpened());

        homePage.selectChildrenQuantity();

        Assert.assertEquals(true, homePage.isAgeOfChildModalOpened());

        homePage.scrollAge();
        homePage.clickOn5yearsOldOption();
        homePage.clickOnOKbutton();

        Assert.assertEquals(true, homePage.isChildrensAgeAtCheckOutDisplayed());
        Assert.assertEquals(true, homePage.isNumberOfChildrenCorrect());
        Assert.assertEquals(true, homePage.isChildrensAgeSelectedCorrect());

        Assert.assertEquals(true, homePage.isApplyButtonEnabled());

        homePage.clickOnApplyButton();

        Assert.assertEquals(true, homePage.isNumberQuestsSet());

        homePage.clickOnSearchButton();

        Assert.assertEquals(true, searchResultsPage.isPropertiesListDisplayed());

        searchResultsPage.scrollUntilItemNumber();

        String stayDetails = searchResultsPage.extractStayDetailsText();
        String totalAmount = searchResultsPage.extractAmountText();
        String taxesAmount = searchResultsPage.extractTaxesAndChargesText();

        System.out.println(stayDetails);
        System.out.println(totalAmount);
        System.out.println(taxesAmount);

        searchResultsPage.selectSecondOption();

        Assert.assertEquals(true, propertyDetailsPage.isSelectRoomModalOpened());

        Assert.assertEquals(true, propertyDetailsPage.isTotalAmountInPropertyDetailsCorrect(totalAmount));
        Assert.assertEquals(true, propertyDetailsPage.isTaxesAmountInPropertyDetailsCorrect(taxesAmount));

        propertyDetailsPage.selectRoom();

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
}

*/