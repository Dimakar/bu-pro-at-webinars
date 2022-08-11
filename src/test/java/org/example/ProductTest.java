package org.example;

import org.example.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductTest extends BaseTest {

    @DisplayName("Добавление в корзину товара и его покупка")
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
