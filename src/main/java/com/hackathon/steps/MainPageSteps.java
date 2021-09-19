package com.hackathon.steps;

import com.hackathon.Logger.Logger;
import com.hackathon.helpers.TestConfig;
import com.hackathon.runner.annotations.Action;
import com.hackathon.runner.annotations.Check;
import com.hackathon.screens.MainPage;
import org.openqa.selenium.WebElement;

public class MainPageSteps extends TestConfig {

    @Action("set to search {string}")
    public void test(String a) {
        MainPage mainPage = new MainPage(driver.get());
        mainPage.searchField.sendKeys(a);
        System.out.println("Test 1 passed");
    }

    @Action("click on Sign in button")
    public void navigateToSignInPage() {
        Logger log = new Logger();
        MainPage mainPage = new MainPage(driver.get());
        mainPage.SignInBtn.click();
        log.info("Login page is opened");
    }

    @Check("account's header is displayed")
    public void accountsHeaderIsDisplayed() {
        MainPage mainPage = new MainPage(driver.get());
        Logger log = new Logger();
        if(mainPage.myAccountBtn.isDisplayed()){
            log.info("User is logged in successfully");
        } else {
            log.error("User failed to log in");
        }
    }
    @Action("Get all banners on Main page")
    public void getAllBanners() {
        MainPage mainPage = new MainPage(driver.get());
        Logger log = new Logger();
        int bannersAmount = mainPage.banners.size();
        log.info("There are " + bannersAmount + " banners on the Main page");
    }
    @Check("All banners are displayed and clickable")
    public void bannersAreDisplayedAndClickable() {
        MainPage mainPage = new MainPage(driver.get());
        Logger log = new Logger();
        int count = 1;
        for(WebElement e: mainPage.banners){
            if (e.isDisplayed()&& !e.getAttribute("src").isEmpty()) {
                //System.out.println(e.getAttribute("src"));
                log.info("Banner #" + count + " is displayed and clickable");
                count++;
            } else {
                log.error("Banner #" + count + " is not displayed or not clickable");
                count++;
            }
        }
    }
}
