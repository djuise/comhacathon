package com.hackathon.screens;

import com.hackathon.helpers.BaseScreen;
import com.hackathon.runner.annotations.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BaseScreen {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "j_username")
    public WebElement signInEmailField;

    @FindBy(id = "j_password")
    public WebElement signInPasswordField;

    @FindBy(css = "[id='loginForm']_button[type='submit']")
    public WebElement signInBtn;
}
