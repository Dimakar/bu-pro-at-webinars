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
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.logging.Level;

import static org.example.config.Config.getConfig;

public class UITestExtension implements AfterEachCallback, BeforeAllCallback {


    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.clearBrowserCache();
        Selenide.refresh();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (getConfig().selenoidUrl() != null) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            capabilities.setCapability("sessionTimeout", "20m");
            Configuration.remote = getConfig().selenoidUrl();
        }
        capabilities.setCapability("env", List.of(
                "LANG=ru_RU.UTF-8",
                "LANGUAGE=ru:RU",
                "LC_ALL=ru_RU.UTF-8"));
        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = getConfig().browser();
        Configuration.browserVersion = getConfig().browserVersion();
        Configuration.baseUrl = getConfig().baseUrl();
        Configuration.timeout = getConfig().timeout();
        SelenideLogger.addListener("Custom", new AllureSelenide().screenshots(true)
                .enableLogs(LogType.BROWSER, Level.WARNING).savePageSource(true));
    }
}
