package org.example.pages;

import lombok.Getter;
import lombok.SneakyThrows;
import org.example.pages.elements.HeaderElement;
import org.example.testdata.User;

import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {
    @Getter
    private HeaderElement headerElement = new HeaderElement();


    @SneakyThrows
    public static ProductListPage auth(User user) {
        open("");
        localStorage().setItem("token", user.getToken());
        refresh();
        return new ProductListPage();
    }
}
