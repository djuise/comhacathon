package com.hackathon.steps;

import com.hackathon.Logger.Logger;
import com.hackathon.helpers.BaseTest;
import com.hackathon.helpers.TestConfig;
import com.hackathon.runner.annotations.Action;
import com.hackathon.runner.annotations.Check;
import com.hackathon.screens.SignInPage;

public class SignInPageSteps extends TestConfig {
    @Action("Input email {string} and password {string}")
    public void inputValidCreds(String a, String b){
        SignInPage signInPage = new SignInPage(driver.get());
        signInPage.signInEmailField.sendKeys(a);
        signInPage.signInPasswordField.sendKeys(b);
    }
    @Action("click on Log In button")
    public void logInBtnClick() {
        SignInPage signInPage = new SignInPage(driver.get());
        signInPage.signInBtn.click();

    }
    @Check("sign in error is displayed")
    public void validationErrorIsDisplayed() {
        Logger log = new Logger();
        SignInPage signInPage = new SignInPage(driver.get());
        if (signInPage.signInError.isDisplayed()) {
            log.info("Validation error is displayed");
        } else {
            log.error("Validation error is failed to display");}

    }

}
