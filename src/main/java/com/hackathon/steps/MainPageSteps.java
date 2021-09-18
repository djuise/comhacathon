package com.hackathon.steps;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.Action;
import com.hackathon.screens.MainPage;

public class MainPageSteps extends BaseTest {

    @Action("set to search {string}")
    public void test(String a) {
        MainPage mainPage = new MainPage(driver);
        mainPage.searchField.sendKeys(a);
        System.out.println();
    }
}
