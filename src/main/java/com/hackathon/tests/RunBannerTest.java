package com.hackathon.tests;

import com.hackathon.helpers.BaseTest;
import com.hackathon.steps.MainPageSteps;
import com.hackathon.steps.SignInPageSteps;

import java.util.Arrays;

public class RunBannerTest extends BaseTest {
    public RunBannerTest() {
        super("BannersAreDisplayedAndClickable", Arrays.asList(MainPageSteps.class, SignInPageSteps.class));
    }
}
