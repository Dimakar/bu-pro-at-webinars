package org.example.pages;

import org.example.ProductTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductPage clickAddToCart() {
        webDriver.findElement(By.xpath("//button[.='Add to cart']"))
                .click();
        return this;
    }


    public ProductPage checkProductWasAdded() {
        new WebDriverWait(webDriver, 2).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[contains(text(),'Item added to your cart.')]")));
        return this;
    }


    public ProductPage checkProductName(String productName) {
        assertThat(webDriver.findElement(By.xpath("//*[@class='product-details-container']/h1")).getText())
                .isEqualTo(productName);
        return this;
    }
}
