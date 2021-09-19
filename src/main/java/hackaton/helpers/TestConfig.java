package hackaton.helpers;

import hackaton.runner.SeleniumConfiguration;
import hackaton.runner.annotations.AfterTest;
import hackaton.runner.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class TestConfig extends SeleniumConfiguration {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeTest
    public void setUp() {
        if (System.getProperty("os.name").contains("nux")) {
            StringJoiner pathToChromeDriver = new StringJoiner(File.separator);
            String path = pathToChromeDriver.add("src").add("main").add("resources").add("webdriwers").add("chromedriver").toString();
            System.setProperty("webdriver.chrome.driver", path);
        }
        else
            WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors", "--disable-dev-shm-usage", "--no-sandbox");
        driver.set(new ChromeDriver(options));
        driver.get().get("https://apparel-uk.local:9002/ucstorefront/en/");
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
