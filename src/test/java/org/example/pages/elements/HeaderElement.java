package org.example.pages.elements;

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
    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductListPage login(String login, String password) {
        loginButton.click();
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new ProductListPage(webDriver);
    }

    public CartPage goToCart() {
        webDriver.findElement(By.xpath("//a[.='CART']")).click();
        return new CartPage(webDriver);
    }
}
