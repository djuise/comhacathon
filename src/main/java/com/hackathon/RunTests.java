package com.hackathon;

import com.hackathon.runner.Runner;
import com.hackathon.steps.MainPageSteps;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class RunTests
{
    public static void main( String[] args ) {
        new Runner().run(1, Arrays.asList("test"), Arrays.asList(MainPageSteps.class));
    }
}
