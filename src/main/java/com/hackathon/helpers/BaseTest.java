package com.hackathon.helpers;

import com.hackathon.runner.annotations.AfterTest;
import com.hackathon.runner.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    private static BaseTest baseTest = null;

    public BaseTest() {
        if (baseTest == null) {
            setUp();
            baseTest = this;
        }
    }

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        driver.get("https://apparel-uk.local:9002/ucstorefront/en/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    @Override
    protected void finalize() throws Throwable {
        driver.close();
        super.finalize();
    }
}
