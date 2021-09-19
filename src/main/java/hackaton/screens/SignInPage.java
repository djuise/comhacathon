package hackaton.screens;

import hackaton.helpers.BaseScreen;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BaseScreen {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "j_username")
    @Getter
    private WebElement signInEmailField;

    @FindBy(id = "j_password")
    @Getter
    private WebElement signInPasswordField;

    @FindBy(css = "[id='loginForm'] button[type='submit']")
    @Getter
    private WebElement signInBtn;

    @FindBy(css = "[class='alert alert-danger alert-dismissable getAccAlert']")
    @Getter
    private WebElement signInError;
}
