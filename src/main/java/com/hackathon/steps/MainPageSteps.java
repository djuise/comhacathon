package com.hackathon.steps;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.Action;
import com.hackathon.screens.MainPage;
import com.hackathon.screens.SignInPage;

public class MainPageSteps extends BaseTest {

    @Action("set to search {string}")
    public void test(String a) {
        MainPage mainPage = new MainPage(driver);
        mainPage.searchField.sendKeys(a);
        System.out.println();
    }

    @Action("click on Sign in button")
    public void navigateToSignInPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.SignInBtn.click();
    }
}
