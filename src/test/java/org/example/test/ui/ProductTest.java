package org.example.test.ui;

import io.qameta.allure.junit5.AllureJunit5;
import org.example.extensions.ApiTestExtension;
import org.example.extensions.UITestExtension;
import org.example.testdata.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.example.pages.BasePage.auth;
import static org.example.testdata.TestDataManager.createNewUser;
import static org.example.testdata.TestDataManager.deleteUser;

@DisplayName("Покупка продуктов")
@ExtendWith({UITestExtension.class, ApiTestExtension.class, AllureJunit5.class})
public class ProductTest {
    User user;

    @BeforeEach
    void setUp() {
        user = createNewUser();
    }

    @DisplayName("Добавление в корзину товара и его покупка")
    @ParameterizedTest
    @ValueSource(strings = {"Huawei P10", "Samsung Galaxy S8", "Apple iPhone 8 Plus"})
    void buyProductTest(String productName) {
        auth(user)
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

    @AfterEach
    void tearDown() {
        deleteUser(user);
    }
}
