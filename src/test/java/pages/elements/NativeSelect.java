package pages.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class NativeSelect extends Element {
    public NativeSelect(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("В selectе '{this.name}' выбираем значение '{value}'")
    public void selectValue(String value) {
        System.out.println("В selectе " + this.name + " выбираем значение " + value);
        selector.selectOption(value);
    }
}
