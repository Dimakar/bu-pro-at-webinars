package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ProductTest {
    private WebDriver webDriver;

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @BeforeEach
    void setUp() {
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Huawei P10", "Samsung Galaxy S8", "Apple iPhone 8 Plus"})
    void buyProductTest(String productName) {
        webDriver.get("http://192.168.235.13:3000/");

        new ProductListPage(webDriver)
                .getHeaderElement()
                .login("admin", "admin")
                .selectProduct(productName)
                .clickAddToCart()
                .checkProductName(productName)
                .checkProductWasAdded()
                .getHeaderElement()
                .goToCart()
                .checkout()
                .confirm()
                .checkOrderHasBeenReceived()
                .clickOk();
    }
}
