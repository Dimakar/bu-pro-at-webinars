package org.example.test.ui;

import org.example.extensions.UiTest;
import org.example.pages.ProductListPage;
import org.example.testdata.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.steps.UiSteps.openPage;
import static org.example.testdata.TestDataManager.createNewUser;
import static org.example.testdata.TestDataManager.deleteUser;

@UiTest
public class AuthTest {
    User user;

    @BeforeEach
    void setUp() {
        user = createNewUser();
    }

    @Test
    void successfulAuthTest() {
        openPage(ProductListPage.class)
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
