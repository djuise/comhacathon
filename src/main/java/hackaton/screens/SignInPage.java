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

    @Getter
    @FindBy(id = "j_username")
    private WebElement signInEmailField;
    @Getter
    @FindBy(id = "j_password")
    private WebElement signInPasswordField;
    @Getter
    @FindBy(css = "[id='loginForm'] button[type='submit']")
    private WebElement signInBtn;
    @Getter
    @FindBy(css = "[class='alert alert-danger alert-dismissable getAccAlert']")
    private WebElement signInError;
}
