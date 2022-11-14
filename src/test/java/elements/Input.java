package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class Input extends Element {
    public Input(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("В поле {this.name} вводим значение {value}")
    public void setInputValue(String value) {
        System.out.println("В поле " + this.name + " вводим значение " + value);
        selector.setValue(value);
    }

    @Step("В поле {this.name} выбираем значение {value} из autocomplete")
    public void selectFromAutocompleteByFullValue(String value) {
        System.out.println("В поле " + this.name + " выбираем значение " + value + "из autocomplete");
        selector.setValue(value).pressEnter();
    }
}
