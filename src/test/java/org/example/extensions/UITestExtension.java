package org.example.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Level;

public class UITestExtension implements AfterEachCallback, BeforeAllCallback {


    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.clearBrowserCache();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://192.168.235.13:3000";
        Configuration.timeout = 2000;
        SelenideLogger.addListener("Custom", new AllureSelenide().screenshots(true)
                .enableLogs(LogType.BROWSER, Level.WARNING).savePageSource(true));
    }
}
