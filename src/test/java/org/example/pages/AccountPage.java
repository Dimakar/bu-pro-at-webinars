package org.example.pages;

import io.qameta.allure.Step;
import org.example.dto.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

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
        assertThat(webDriver.findElement(By.xpath("//p[./b[contains(text(), '" + fieldName + "')]]")).getText().trim())
                .as("Field with name = '" + fieldName + "' should be equal to value '" + fieldValue + "'")
                .isEqualTo((fieldName + ": " + fieldValue).trim());
        return this;
    }

}
