package org.example.pages;

import lombok.Getter;
import org.example.pages.elements.HeaderElement;

public abstract class BasePage {
    @Getter
    private HeaderElement headerElement = new HeaderElement();
}
