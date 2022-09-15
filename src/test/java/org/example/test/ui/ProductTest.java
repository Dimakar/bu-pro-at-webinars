package org.example.test.ui;

import org.example.extensions.UiTest;
import org.example.pages.ProductListPage;
import org.example.testdata.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.example.steps.UiSteps.openPage;
import static org.example.testdata.TestDataManager.createNewUser;
import static org.example.testdata.TestDataManager.deleteUser;

@DisplayName("Покупка продуктов")
@UiTest
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
        openPage(user, ProductListPage.class)
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
