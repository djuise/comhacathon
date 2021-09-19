package com.hackathon;

import com.hackathon.runner.Runner;
import com.hackathon.tests.RunFirstTest;

/**
 * Hello world!
 *
 */
public class RunTests
{
    public static void main( String[] args ) {
        new Runner().run(new RunFirstTest());
    }
}
