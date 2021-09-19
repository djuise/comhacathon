package hackaton.screens;

import hackaton.helpers.BaseScreen;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BaseScreen {
    @Getter
    @FindBy(id = "js-site-search-input")
    private WebElement searchField;
    @Getter
    @FindBy(xpath = "//a[@href='/ucstorefront/en/login']")
    private WebElement signInBtn;
    @Getter
    @FindBy(css = "[class='myAccountLinksHeader js-myAccount-toggle']")
    private WebElement myAccountBtn;
    @Getter
    @FindBy(css = "[class='js-responsive-image']")
    private List<WebElement> banners;
}
