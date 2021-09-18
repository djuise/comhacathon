package com.hackathon.steps;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.Action;
import com.hackathon.screens.MainPage;

public class ScTest extends BaseTest {

    @Action("set to search {string}")
    public void test(String a) {
        MainPage mainPage = new MainPage(driver);
        mainPage.searchField.sendKeys("TEST");
        System.out.println();
    }
}
