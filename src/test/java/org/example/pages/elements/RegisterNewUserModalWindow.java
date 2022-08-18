package org.example.pages.elements;

import io.qameta.allure.Step;
import org.example.dto.UserData;
import org.example.pages.BaseView;
import org.example.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterNewUserModalWindow extends BaseView {
    public RegisterNewUserModalWindow(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Заполнить поля нового юзера")
    public RegisterNewUserModalWindow inputNewUserData(UserData userData) {
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterUsername')]")).sendKeys(userData.getUserName() == null ? "" : userData.getUserName());
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterPassword')]")).sendKeys(userData.getPassword() == null ? "" : userData.getPassword());
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterE-mail')]")).sendKeys(userData.getEmail() == null ? "" : userData.getEmail());
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterAddress')]")).sendKeys(userData.getAddress() == null ? "" : userData.getAddress());
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterTelephoneNumber')]")).sendKeys(userData.getPhoneNumber() == null ? "" : userData.getPhoneNumber());
        return this;
    }
    @Step("Нажать кнопку Submit")
    public ProductPage submit() {
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        return new ProductPage(webDriver);
    }
}
