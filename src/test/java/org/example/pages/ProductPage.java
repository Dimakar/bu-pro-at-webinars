package org.example.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    @Step("Нажать кнопку 'Add to cart'")
    public ProductPage clickAddToCart() {
        $x("//button[.='Add to cart']")
                .click();
        return this;
    }

    @Step("Проверить, что появился элемент с текстом 'Item added to your cart.'")
    public ProductPage checkProductWasAdded() {
        $x("//*[contains(text(),'Item added to your cart.')]").shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что открыта страница продукта {productName}")
    public ProductPage checkProductName(String productName) {
        $x("//*[@class='product-details-container']/h1")
                .shouldHave(Condition.exactText(productName));
        return this;
    }
}
