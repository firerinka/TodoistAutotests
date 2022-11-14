package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.Element;
import elements.Elements;

public class TaskItems extends Elements {

    public TaskItems(String name, ElementsCollection selector) {
        super(name, selector);
    }
}
