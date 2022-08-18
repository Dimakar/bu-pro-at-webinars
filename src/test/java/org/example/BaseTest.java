package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.allure.AllureListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected EventFiringWebDriver webDriver;

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @BeforeEach
    void setUp() {
        webDriver = new EventFiringWebDriver(WebDriverManager.chromedriver().create());
        webDriver.register(new AllureListener());
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
