package hackaton.helpers;

import org.openqa.selenium.support.PageFactory;

abstract public class BaseScreen {
    public BaseScreen() {
            PageFactory.initElements(TestConfig.driver.get(), this);
    }
}
