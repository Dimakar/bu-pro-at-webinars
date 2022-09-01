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
        clickLoginButton();
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new ProductListPage();
    }

    public ProductListPage login(User user) {
        return login(user.getUsername(), user.getPassword());
    }

    @Step("Нажать кнопку Login")
    public HeaderElement clickLoginButton() {
        loginButton.click();
        return this;
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

    @Step("Нажать ссылку 'Register here'")
    public RegisterNewUserModalWindow clickRegisterHere() {
        $x("//a[text()='Register here']").click();
        return new RegisterNewUserModalWindow();
    }
}
