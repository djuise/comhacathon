package hackaton.steps;

import hackaton.Logger.Logger;
import hackaton.helpers.TestConfig;
import hackaton.runner.annotations.Action;
import hackaton.runner.annotations.Check;
import hackaton.screens.SignInPage;

public class SignInPageSteps extends TestConfig {

    private SignInPage signInPage = new SignInPage();

    @SuppressWarnings({"unused", "using for steps"})
    @Action("Input email {string} and password {string}")
    public void inputValidCreds(String a, String b){
        SignInPage signInPage = new SignInPage();
        signInPage.getSignInEmailField().sendKeys(a);
        signInPage.getSignInPasswordField().sendKeys(b);
    }

    @SuppressWarnings({"unused", "using for steps"})
    @Action("click on Log In button")
    public void logInBtnClick() {
        SignInPage signInPage = new SignInPage();
        signInPage.getSignInBtn().click();

    }

    @SuppressWarnings({"unused", "using for steps"})
    @Check("sign in error is displayed")
    public void validationErrorIsDisplayed() {
        Logger log = new Logger();
        SignInPage signInPage = new SignInPage();
        if (signInPage.getSignInError().isDisplayed()) {
            log.info("Validation error is displayed");
        } else {
            log.error("Validation error is failed to display");}

    }

}
