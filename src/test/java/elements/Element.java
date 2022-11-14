package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class Element {
    protected final String name;
    protected final SelenideElement selector;

    public Element(String name, SelenideElement selector) {
        this.name = name;
        this.selector = selector;
    }

    @Step("Кликаем на элемент {this.name}")
    public void click() {
        System.out.println("Кликаем на элемент " + this.name);
        selector.click();
    }
}
