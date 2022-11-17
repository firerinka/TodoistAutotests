package pageObjects.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;

public class Element {
    protected final String name;
    protected final SelenideElement selector;

    public Element(String name, SelenideElement selector) {
        this.name = name;
        this.selector = selector;
    }

    @Step("Кликаем на элемент '{this.name}'")
    public void click() {
        System.out.println("Кликаем на элемент " + this.name);
        selector.click();
    }

    @Step("Наводим курсор на элемент '{this.name}'")
    public void hover() {
        System.out.println("Наводим курсор на элемент " + this.name);
        selector.hover();
    }

    @Step("Проверяем, что текст элемента '{this.name}' имеет значение '{value}'")
    public void checkText(String value) {
        System.out.println("Проверяем, что текст элемента " + this.name + " имеет значение " + value);
        selector.shouldHave(text(value));
    }
}
