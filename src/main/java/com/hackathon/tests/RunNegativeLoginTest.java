package com.hackathon.tests;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.Test;
import com.hackathon.steps.MainPageSteps;
import com.hackathon.steps.SignInPageSteps;

import java.util.Arrays;

@Test
public class RunNegativeLoginTest extends BaseTest {

    public RunNegativeLoginTest() {
        super("NegativeLoginTest", Arrays.asList(MainPageSteps.class, SignInPageSteps.class));
    }
}
