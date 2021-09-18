package com.hackathon.screens;

import com.hackathon.helpers.BaseScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseScreen {

    public MainPage(WebDriver driver) {
            super(driver);
    }

    @FindBy(id = "js-site-search-input")
    public WebElement searchField;
    @FindBy (xpath = "//a[@href='/ucstorefront/en/login']")
    public WebElement SignInBtn;
}
