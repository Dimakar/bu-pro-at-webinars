package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class ProductListPage extends BasePage {

    @Step("Выбрать продукт {productName}")
    public ProductPage selectProduct(String productName) {
        $$(By.className("product"))
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .filter(product -> product.getText().contains(productName))
                .findFirst()
                .orElseThrow()
                .$x(".//*[text()='See more']")
                .click();
        return new ProductPage();
    }
}
