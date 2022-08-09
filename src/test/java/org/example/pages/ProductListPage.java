package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends BasePage {


    public ProductListPage(WebDriver webDriver) {
        super(webDriver);
    }



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
