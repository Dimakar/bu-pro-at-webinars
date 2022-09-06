package org.example.pages.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.pages.AccountPage;
import org.example.pages.CartPage;
import org.example.pages.ProductListPage;
import org.example.testdata.User;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderElement {
    private SelenideElement userNameInput = $x("//input[contains(@id, 'Username')]");
    private SelenideElement passwordInput = $x("//input[contains(@id, 'Password')]");
    private SelenideElement submitButton = $x("//button[.='Submit']");
    private SelenideElement loginButton = $x("//button[.='LOGIN']");
    private SelenideElement accountButton = $x("//span[.='ACCOUNT']");

    @Step("Авторизоваться юзером с {login} {password}")
    public ProductListPage login(String login, String password) {
        return clickLoginButton()
                .inputUsername(login)
                .inputPassword(password)
                .clickSubmitButton();
    }

    public HeaderElement inputPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public HeaderElement inputUsername(String login) {
        userNameInput.sendKeys(login);
        return this;
    }

    public ProductListPage login(User user) {
        return login(user.getUsername(), user.getPassword());
    }

    @Step("Нажать кнопку Login")
    public HeaderElement clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Нажать кнопку Submit")
    public ProductListPage clickSubmitButton() {
        submitButton.click();
        return new ProductListPage();
    }

    @Step("Нажать кнопку Account")
    public AccountPage clickAccountButton() {
        accountButton.click();
        return new AccountPage();
    }

    @Step("Нажать кнопку Cart")
    public CartPage goToCart() {
        $x("//a[.='CART']").click();
        return new CartPage();
    }

    @Step("Проверить, что отображается кнопка Logout")
    public HeaderElement checkLogoutIsPresent() {
        $x("//div[.='LOGOUT']").click();
        return this;
    }

    @Step("Нажать ссылку 'Register here'")
    public RegisterNewUserModalWindow clickRegisterHere() {
        $x("//a[text()='Register here']").click();
        return new RegisterNewUserModalWindow();
    }
}
