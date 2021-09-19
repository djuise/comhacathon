package hackaton.steps;

import hackaton.Logger.Logger;
import hackaton.helpers.TestConfig;
import hackaton.runner.annotations.Action;
import hackaton.runner.annotations.Check;
import hackaton.screens.MainPage;
import org.openqa.selenium.WebElement;

public class MainPageSteps extends TestConfig {

    @SuppressWarnings({"unused", "using for steps"})
    @Action("click on Sign in button")
    public void navigateToSignInPage() {
        Logger log = new Logger();
        MainPage mainPage = new MainPage(driver.get());
        mainPage.getSignInBtn().click();
        log.info("Login page is opened");
    }

    @SuppressWarnings({"unused", "using for steps"})
    @Check("account's header is displayed")
    public void accountsHeaderIsDisplayed() {
        MainPage mainPage = new MainPage(driver.get());
        Logger log = new Logger();
        if(mainPage.getMyAccountBtn().isDisplayed()){
            log.info("User is logged in successfully");
        } else {
            log.error("User failed to log in");
        }
    }

    @SuppressWarnings({"unused", "using for steps"})
    @Action("Get all banners on Main page")
    public void getAllBanners() {
        MainPage mainPage = new MainPage(driver.get());
        Logger log = new Logger();
        int bannersAmount = mainPage.getBanners().size();
        log.info("There are " + bannersAmount + " banners on the Main page");
    }

    @SuppressWarnings({"unused", "using for steps"})
    @Check("All banners are displayed and clickable")
    public void bannersAreDisplayedAndClickable() {
        MainPage mainPage = new MainPage(driver.get());
        Logger log = new Logger();
        int count = 1;
        for(WebElement e: mainPage.getBanners()){
            if (e.isDisplayed()&& !e.getAttribute("src").isEmpty()) {
                log.info("Banner #" + count + " is displayed and clickable");
                count++;
            } else {
                log.error("Banner #" + count + " is not displayed or not clickable");
                count++;
            }
        }
    }

}
