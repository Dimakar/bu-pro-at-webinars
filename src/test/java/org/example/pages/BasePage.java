package org.example.pages;

import org.example.pages.elements.HeaderElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage extends BaseView {

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
