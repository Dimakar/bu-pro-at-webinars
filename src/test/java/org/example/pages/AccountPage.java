package org.example.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.example.dto.UserData;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class AccountPage extends BasePage {

    @Step("Проверить поля юзера")
    public AccountPage checkUserData(UserData userData) {
        checkUserField("Username", userData.getUserName() == null ? "" : userData.getUserName())
                .checkUserField("E-mail", userData.getEmail() == null ? "" : userData.getEmail())
                .checkUserField("Billing Address", userData.getAddress() == null ? "" : userData.getAddress())
                .checkUserField("Phone", userData.getPhoneNumber() == null ? "" : userData.getPhoneNumber());
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
