package org.example.pages.elements;

import io.qameta.allure.Step;
import org.example.pages.BaseView;
import org.example.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterNewUserModalWindow extends BaseView {
    public RegisterNewUserModalWindow(WebDriver webDriver) {
        super(webDriver);
    }


    @Step("Заполнить поля нового юзера")
    public RegisterNewUserModalWindow inputNewUserData(String userName, String password, String email, String address, String phoneNumber) {
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterUsername')]")).sendKeys(userName);
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterPassword')]")).sendKeys(password);
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterE-mail')]")).sendKeys(email);
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterAddress')]")).sendKeys(address);
        webDriver.findElement(By.xpath("//input[contains(@id, 'EnterTelephoneNumber')]")).sendKeys(phoneNumber);
        return this;
    }

    @Step("Нажать кнопку Submit")
    public ProductPage submit() {
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        return new ProductPage(webDriver);
    }
}
