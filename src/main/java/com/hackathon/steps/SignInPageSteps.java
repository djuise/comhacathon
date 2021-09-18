package com.hackathon.steps;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.Action;
import com.hackathon.screens.SignInPage;

public class SignInPageSteps extends BaseTest {
    @Action("Input email {string} and password {string}")
    public void inputValidCreds(String a, String b){
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInEmailField.sendKeys(a);
        signInPage.signInPasswordField.sendKeys(b);
    }
    @Action("click on Log In button")
    public void logInBtnClick() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInBtn.click();

    }
}
