package hackaton.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract public class BaseScreen {
    public BaseScreen(WebDriver driver) {
            PageFactory.initElements(driver, this);
    }
}
