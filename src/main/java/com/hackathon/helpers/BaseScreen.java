package com.hackathon.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract public class BaseScreen {

//    private static BaseScreen baseScreen = null;

    public BaseScreen(WebDriver driver) {
//        if (baseScreen == null)
            PageFactory.initElements(driver, this);

//        baseScreen = this;
    }
}
