package org.example.test.ui;

import io.qameta.allure.junit5.AllureJunit5;
import org.example.dto.CreateUserRequestDto;
import org.example.endpoints.ApiUserRegisterEndpoint;
import org.example.extensions.ApiTestExtension;
import org.example.extensions.UITestExtension;
import org.example.pages.ProductListPage;
import org.example.testdata.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;
import static org.example.testdata.TestDataGenerator.generateUserDto;

@DisplayName("Покупка продуктов")
@ExtendWith({UITestExtension.class, ApiTestExtension.class, AllureJunit5.class})
public class ProductTest {
    User user;

    @BeforeEach
    void setUp() {
        // TODO: 01.09.2022 упростить подготовку ТД
        CreateUserRequestDto requestDto = generateUserDto();
        user = User.builder()
                .username(requestDto.getUserName())
                .password(requestDto.getPassword())
                .build();
        user.setToken(new ApiUserRegisterEndpoint().registerUser(requestDto).getToken());
    }

    @DisplayName("Добавление в корзину товара и его покупка")
    @ParameterizedTest
    @ValueSource(strings = {"Huawei P10", "Samsung Galaxy S8", "Apple iPhone 8 Plus"})
    void buyProductTest(String productName) {
        open("");

        new ProductListPage()
                .getHeaderElement()
                .login(user)
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
