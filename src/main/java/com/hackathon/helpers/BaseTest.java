package com.hackathon.helpers;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class BaseTest extends TestConfig {
    public String scenario;
    public List<Class> classesList;
    public WebDriver driver;

    public BaseTest(String scenario, List<Class> classesList) {
        this.scenario = scenario;
        this.classesList = classesList;
        this.driver = TestConfig.driver.get();
    }

    public BaseTest(String scenario, List<Class> classesList, String folder) {
        this.scenario = folder + scenario;
        this.classesList = classesList;
    }


    public void test() {
        System.out.println("ASD");
    }
}
