package org.example.test.ui;

import io.qameta.allure.junit5.AllureJunit5;
import org.example.extensions.ApiTestExtension;
import org.example.extensions.UITestExtension;
import org.example.pages.ProductListPage;
import org.example.testdata.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static org.example.testdata.TestDataManager.createNewUser;
import static org.example.testdata.TestDataManager.deleteUser;

@ExtendWith({UITestExtension.class, ApiTestExtension.class, AllureJunit5.class})
public class AuthTest {
    User user;

    @BeforeEach
    void setUp() {
        user = createNewUser();
    }

    @Test
    void successfulAuthTest() {
        open("");
        new ProductListPage()
                .getHeaderElement()
                .clickLoginButton()
                .inputUsername(user.getUsername())
                .inputPassword(user.getPassword())
                .clickSubmitButton()
                .getHeaderElement()
                .checkLogoutIsPresent();
    }

    @AfterEach
    void tearDown() {
        deleteUser(user);
    }
}
