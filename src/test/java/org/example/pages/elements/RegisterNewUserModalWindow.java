package org.example.pages.elements;

import io.qameta.allure.Step;
import org.example.dto.CreateUserRequestDto;
import org.example.pages.ProductPage;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterNewUserModalWindow {

    @Step("Заполнить поля нового юзера")
    public RegisterNewUserModalWindow inputNewUserData(CreateUserRequestDto createUserRequestDto) {
        $x("//input[contains(@id, 'EnterUsername')]").sendKeys(createUserRequestDto.getUserName() == null ? "" : createUserRequestDto.getUserName());
        $x("//input[contains(@id, 'EnterPassword')]").sendKeys(createUserRequestDto.getPassword() == null ? "" : createUserRequestDto.getPassword());
        $x("//input[contains(@id, 'EnterE-mail')]").sendKeys(createUserRequestDto.getEmail() == null ? "" : createUserRequestDto.getEmail());
        $x("//input[contains(@id, 'EnterAddress')]").sendKeys(createUserRequestDto.getAddress() == null ? "" : createUserRequestDto.getAddress());
        $x("//input[contains(@id, 'EnterTelephoneNumber')]").sendKeys(createUserRequestDto.getPhoneNumber() == null ? "" : createUserRequestDto.getPhoneNumber());
        return this;
    }

    @Step("Нажать кнопку Submit")
    public ProductPage submit() {
        $x("//button[.='Submit']").click();
        return new ProductPage();
    }
}
