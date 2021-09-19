package hackaton.helpers;

import hackaton.runner.SeleniumConfiguration;
import hackaton.runner.annotations.AfterTest;
import hackaton.runner.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestConfig extends SeleniumConfiguration {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors", "--disable-dev-shm-usage");
        driver.set(new ChromeDriver(options));
        driver.get().get("https://apparel-uk.local:9002/ucstorefront/en/");
        driver.get().manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.get().quit();
    }

    @Override
    protected void finalize() throws Throwable {
        driver.get().close();
        super.finalize();
    }
}
