package org.example.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.example.dto.CreateUserRequestDto;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class AccountPage extends BasePage {

    @Step("Проверить поля юзера")
    public AccountPage checkUserData(CreateUserRequestDto createUserRequestDto) {
        checkUserField("Username", createUserRequestDto.getUserName() == null ? "" : createUserRequestDto.getUserName())
                .checkUserField("E-mail", createUserRequestDto.getEmail() == null ? "" : createUserRequestDto.getEmail())
                .checkUserField("Billing Address", createUserRequestDto.getAddress() == null ? "" : createUserRequestDto.getAddress())
                .checkUserField("Phone", createUserRequestDto.getPhoneNumber() == null ? "" : createUserRequestDto.getPhoneNumber());
        return this;
    }
    @Step("Проверить поля юзера")
    public AccountPage checkUserData(String userName, String email, String address, String phoneNumber) {
        checkUserField("Username", userName)
                .checkUserField("E-mail", email)
                .checkUserField("Billing Address", address)
                .checkUserField("Phone", phoneNumber);
        return this;
    }

    @Step("Проверить, что поле {fieldName} = {fieldValue}")
    private AccountPage checkUserField(String fieldName, String fieldValue) {
        $x("//p[./b[contains(text(), '" + fieldName + "')]]")
                .shouldBe(Condition.text((fieldName + ": " + fieldValue))
                                .because("Field with name = '" + fieldName + "' should be equal to value '" + fieldValue + "'"),
                        Duration.ofSeconds(2));
        return this;
    }

}
