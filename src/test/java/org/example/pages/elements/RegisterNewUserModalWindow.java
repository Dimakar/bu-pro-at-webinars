package org.example.pages.elements;

import io.qameta.allure.Step;
import org.example.dto.UserData;
import org.example.pages.ProductPage;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterNewUserModalWindow {

    @Step("Заполнить поля нового юзера")
    public RegisterNewUserModalWindow inputNewUserData(UserData userData) {
        $x("//input[contains(@id, 'EnterUsername')]").sendKeys(userData.getUserName() == null ? "" : userData.getUserName());
        $x("//input[contains(@id, 'EnterPassword')]").sendKeys(userData.getPassword() == null ? "" : userData.getPassword());
        $x("//input[contains(@id, 'EnterE-mail')]").sendKeys(userData.getEmail() == null ? "" : userData.getEmail());
        $x("//input[contains(@id, 'EnterAddress')]").sendKeys(userData.getAddress() == null ? "" : userData.getAddress());
        $x("//input[contains(@id, 'EnterTelephoneNumber')]").sendKeys(userData.getPhoneNumber() == null ? "" : userData.getPhoneNumber());
        return this;
    }

    @Step("Нажать кнопку Submit")
    public ProductPage submit() {
        $x("//button[.='Submit']").click();
        return new ProductPage();
    }
}
