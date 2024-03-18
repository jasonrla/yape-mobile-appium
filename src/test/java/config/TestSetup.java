package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yape_mobile.model.Reservation;
import com.yape_mobile.model.User;
import com.yape_mobile.pages.LoginPage;
import com.yape_mobile.pages.PropertyDetailsPage;
import com.yape_mobile.pages.HomePage;
import com.yape_mobile.pages.SearchResultsPage;
import com.yape_mobile.pages.RoomSelectionPage;
import com.yape_mobile.pages.RegistrationPage;
import com.yape_mobile.pages.BookingOverviewPage;
import com.yape_mobile.pages.FinishBooking;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestSetup {
    private AndroidDriver<MobileElement> driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchResultsPage searchResultsPage;
    private PropertyDetailsPage propertyDetailsPage;
    private RoomSelectionPage roomSelectionPage;
    private RegistrationPage registrationPage;
    private BookingOverviewPage bookingOverviewPage;
    private FinishBooking finishBooking;

    public TestSetup() throws MalformedURLException, URISyntaxException, FileNotFoundException {
        setupDriver();
        JsonObject jsonObject = readJsonFile("src/test/java/resources/data.json");
        User user = createUser(jsonObject);
        Reservation reservation = createReservation(jsonObject);
        setupPages(user, reservation);
    }

    private void setupDriver() throws MalformedURLException, URISyntaxException {
        AppiumConfiguration config = new AppiumConfiguration();
        DesiredCapabilities capabilities = config.getCapabilities();
        String serverUrl = config.getServerUrl();
        driver = new AndroidDriver<>(new URI(serverUrl).toURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private JsonObject readJsonFile(String filePath) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(filePath), JsonObject.class);
    }

    private User createUser(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.get("user"), User.class);
    }

    private Reservation createReservation(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.get("reservation"), Reservation.class);
    }

    private void setupPages(User user, Reservation reservation) {
        homePage = new HomePage(driver, user, reservation);
        loginPage = new LoginPage(driver);
        searchResultsPage = new SearchResultsPage(driver, user, reservation);
        propertyDetailsPage = new PropertyDetailsPage(driver, user, reservation);
        roomSelectionPage = new RoomSelectionPage(driver, user, reservation);
        registrationPage = new RegistrationPage(driver, user, reservation);
        bookingOverviewPage = new BookingOverviewPage(driver, user, reservation);
        finishBooking = new FinishBooking(driver, user, reservation);
    }

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public SearchResultsPage getSearchResultsPage() {
        return searchResultsPage;
    }

    public PropertyDetailsPage getPropertyDetailsPage() {
        return propertyDetailsPage;
    }

    public RoomSelectionPage getRoomSelectionPage() {
        return roomSelectionPage;
    }

    public RegistrationPage getRegistrationPage() {
        return registrationPage;
    }

    public BookingOverviewPage getBookingOverviewPage() {
        return bookingOverviewPage;
    }

    public FinishBooking getFinishBooking() {
        return finishBooking;
    }
}
