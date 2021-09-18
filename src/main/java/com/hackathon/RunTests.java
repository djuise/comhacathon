package com.hackathon;

import com.hackathon.runner.Runner;
import com.hackathon.tests.RunBannerTest;
import com.hackathon.tests.RunNegativeLoginTest;

/**
 * Hello world!
 *
 */
public class RunTests
{
    public static void main( String[] args ) {
       // new Runner().run(new RunNegativeLoginTest());
        new Runner().run(new RunBannerTest());
    }
}
