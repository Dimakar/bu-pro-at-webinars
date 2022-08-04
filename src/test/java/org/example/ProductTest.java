package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductTest {
    private WebDriver webDriver;

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @BeforeEach
    void setUp() {
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    void buyProductTest() throws InterruptedException {
        String productName = "Huawei P10";
        webDriver.get("http://192.168.235.13:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']"))
                .click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();

        List<WebElement> products = webDriver.findElements(By.className("product"));

        products.stream()
                .filter(product -> product.getText().contains(productName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//*[text()='See more']"))
                .click();

        webDriver.findElement(By.xpath("//button[.='Add to cart']"))
                .click();

        new WebDriverWait(webDriver, 2).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[contains(text(),'Item added to your cart.')]")));
        webDriver.findElement(By.xpath("//a[.='CART']")).click();
        webDriver.findElement(By.xpath("//button[.='Checkout']")).click();
        webDriver.findElement(By.xpath("//button[.='Confirm']")).click();

        new WebDriverWait(webDriver, 2).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[contains(text(),\"Your order has been received. The items you've ordered will be sent to your address.\")]")));
        webDriver.findElement(By.xpath("//button[.='OK']")).click();
    }
}
