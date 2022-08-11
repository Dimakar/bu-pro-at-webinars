package org.example.pages.elements;

import io.qameta.allure.Step;
import org.example.pages.AccountPage;
import org.example.pages.BaseView;
import org.example.pages.CartPage;
import org.example.pages.ProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElement extends BaseView {
    @FindBy(xpath = "//input[contains(@id, 'Username')]")
    private WebElement userNameInput;
    @FindBy(xpath = "//input[contains(@id, 'Password')]")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[.='Submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//button[.='LOGIN']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[.='ACCOUNT']")
    private WebElement accountButton;
    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Авторизоваться юзером с {login} {password}")
    public ProductListPage login(String login, String password) {
        clickLoginButton();
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new ProductListPage(webDriver);
    }

    @Step("Нажать кнопку Login")
    public HeaderElement clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Нажать кнопку Account")
    public AccountPage clickAccountButton() {
        accountButton.click();
        return new AccountPage(webDriver);
    }

    @Step("Нажать кнопку Cart")
    public CartPage goToCart() {
        webDriver.findElement(By.xpath("//a[.='CART']")).click();
        return new CartPage(webDriver);
    }

    @Step("Нажать ссылку 'Register here'")
    public RegisterNewUserModalWindow clickRegisterHere() {
        webDriver.findElement(By.xpath("//a[text()='Register here']")).click();
        return new RegisterNewUserModalWindow(webDriver);
    }
}
