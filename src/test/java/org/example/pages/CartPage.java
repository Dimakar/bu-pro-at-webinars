package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Page(url = "/cart")
public class CartPage extends BasePage {

    @Step("Нажать кнопку Checkout")
    public CartPage checkout() {
        $x("//button[.='Checkout']").click();
        return this;
    }

    @Step("Нажать кнопку Confirm")
    public CartPage confirm() {
        $x("//button[.='Confirm']").click();
        return this;
    }

    @Step("Проверить, что заказ успешно оформлен")
    public CartPage checkOrderHasBeenReceived() {
        $x("//div[@class='success']/p")
                .shouldHave(Condition.text("Your order has been received. The items you've ordered will be sent to your address."),
                        Duration.ofSeconds(2));
        return this;
    }

    @Step("Нажать кнопку OK")
    public CartPage clickOk() {
        $x("//button[.='OK']").click();
        return this;
    }

    @Step("Проверить содержимое корзины")
    public CartPage checkCart(String... productNames) {
        $$x("//div[@class='cart-items']//tbody/tr//a")
                .shouldHave(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(productNames));
        return this;
    }
}
