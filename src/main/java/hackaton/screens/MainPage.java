package hackaton.screens;

import hackaton.helpers.BaseScreen;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BaseScreen {

    public MainPage(WebDriver driver) {
            super(driver);
    }

    @FindBy(id = "js-site-search-input") @Getter
    private WebElement searchField;
    @FindBy(xpath = "//a[@href='/ucstorefront/en/login']") @Getter
    private WebElement signInBtn;
    @FindBy(css = "[class='myAccountLinksHeader js-myAccount-toggle']") @Getter
    private WebElement myAccountBtn;
    @FindBy(css = "[class='js-responsive-image']") @Getter
    private List<WebElement> banners;
}
