package org.example.steps;

import org.example.pages.Page;
import org.example.testdata.User;
import org.slf4j.helpers.MessageFormatter;

import static com.codeborne.selenide.Selenide.*;

public class UiSteps {
    public static <T> T openPage(Class<T> pageClass) {
        return open(pageClass.getAnnotation(Page.class).url(), pageClass);
    }

    public static <T> T openPage(User user, Class<T> pageClass) {
        open("");
        localStorage().setItem("token", user.getToken());
        return openPage(pageClass);
    }

    public static <T> T openPage(Class<T> pageClass, String... parameters) {
        return open(MessageFormatter.format(pageClass.getAnnotation(Page.class).url(), parameters).getMessage(), pageClass);
    }

    public static <T> T openPage(User user, Class<T> pageClass, String... parameters) {
        open("");
        localStorage().setItem("token", user.getToken());
        return openPage(pageClass, parameters);
    }
}
