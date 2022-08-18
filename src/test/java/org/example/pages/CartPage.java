package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    // TODO: 11.08.2022 везде одинаковые названия шагов 
    @Step("Нажать кнопку Checkout")
    public CartPage checkout() {
        webDriver.findElement(By.xpath("//button[.='Checkout']")).click();
        return this;
    }

    @Step("Нажать кнопку Confirm")
    public CartPage confirm() {
        webDriver.findElement(By.xpath("//button[.='Confirm']")).click();
        return this;
    }

    @Step("Проверить, что заказ успешно оформлен")
    public CartPage checkOrderHasBeenReceived() {
        new WebDriverWait(webDriver, 2).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[contains(text(),\"Your order has been received. The items you've ordered will be sent to your address.\")]")));
        return this;
    }

    @Step("Нажать кнопку OK")
    public CartPage clickOk() {
        webDriver.findElement(By.xpath("//button[.='OK']")).click();
        return this;
    }

    @Step("Проверить содержимое корзины")
    public CartPage checkCart(String... productNames) {
        List<String> products = webDriver.findElements(By.xpath("//div[@class='cart-items']//tbody/tr"))
                .stream()
                .map(el -> el.findElement(By.xpath("//td[./a]")).getText())
                .collect(Collectors.toList());

        assertThat(products).containsExactlyInAnyOrder(productNames);
        return this;
    }
}
