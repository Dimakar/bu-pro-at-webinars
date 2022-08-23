package org.example.test.ui;

import org.example.extensions.UITestExtension;
import org.example.pages.ProductListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Покупка продуктов")
@ExtendWith(UITestExtension.class)
public class ProductTest {

    @DisplayName("Добавление в корзину товара и его покупка")
    @ParameterizedTest
    @ValueSource(strings = {"Huawei P10", "Samsung Galaxy S8", "Apple iPhone 8 Plus"})
    void buyProductTest(String productName) {
        open("");

        new ProductListPage()
                .getHeaderElement()
                .login("admin", "admin")
                .selectProduct(productName)
                .clickAddToCart()
                .checkProductName(productName)
                .checkProductWasAdded()
                .getHeaderElement()
                .goToCart()
                .checkCart(productName)
                .checkout()
                .confirm()
                .checkOrderHasBeenReceived()
                .clickOk();
    }
}
