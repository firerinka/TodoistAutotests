package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;

public class Select extends Element {
    public Select(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("В selectе '{this.name}' выбираем значение '{value}'")
    public void selectValue(String value) {
        System.out.println("В selectе " + this.name + " выбираем значение " + value);
        selector.click();
        selector.$(byText(value)).click();
    }
}
