package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductListPage extends BasePage {


    public ProductListPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Step("Выбрать продукт {productName}")
    public ProductPage selectProduct(String productName) {
        List<WebElement> products = webDriver.findElements(By.className("product"));

        products.stream()
                .filter(product -> product.getText().contains(productName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//*[text()='See more']"))
                .click();
        return new ProductPage(webDriver);
    }
}
