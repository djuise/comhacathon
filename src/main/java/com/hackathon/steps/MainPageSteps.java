package com.hackathon.steps;

import com.hackathon.helpers.TestConfig;
import com.hackathon.runner.annotations.Action;
import com.hackathon.screens.MainPage;

public class MainPageSteps extends TestConfig {

    @Action("set to search {string}")
    public void test(String a) {
        MainPage mainPage = new MainPage(driver.get());
        mainPage.searchField.sendKeys(a);
        System.out.println();
    }
}
