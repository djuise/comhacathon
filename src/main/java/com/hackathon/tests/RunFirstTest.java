package com.hackathon.tests;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.Test;
import com.hackathon.steps.MainPageSteps;
import com.hackathon.steps.MainPageSteps2;

import java.util.Arrays;

@Test
public class RunFirstTest extends BaseTest {

    public RunFirstTest() {
        super("test", Arrays.asList(MainPageSteps.class, MainPageSteps2.class));
    }
}
