package pageObjects.elements;

import com.codeborne.selenide.ElementsCollection;

public class Elements {
    protected final String name;
    protected final ElementsCollection selector;

    public Elements(String name, ElementsCollection selector) {
        this.name = name;
        this.selector = selector;
    }
}
