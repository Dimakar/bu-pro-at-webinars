package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CartPage checkout() {
        webDriver.findElement(By.xpath("//button[.='Checkout']")).click();
        return this;
    }

    public CartPage confirm() {
        webDriver.findElement(By.xpath("//button[.='Confirm']")).click();
        return this;
    }

    public CartPage checkOrderHasBeenReceived() {
        new WebDriverWait(webDriver, 2).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[contains(text(),\"Your order has been received. The items you've ordered will be sent to your address.\")]")));
        return this;
    }

    public CartPage clickOk() {
        webDriver.findElement(By.xpath("//button[.='OK']")).click();
        return this;
    }
}
