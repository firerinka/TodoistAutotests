package pages.components;

import com.codeborne.selenide.SelenideElement;
import elements.Element;
import elements.Input;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class TaskEditor extends Element {

    public TaskEditor(String name, SelenideElement selector) {
        super(name, selector);
    }

    Input title = new Input("taskTitle", $(".richtextinput .notranslate"));

    @Step("Вводим название задачи '{value}'")
    public TaskEditor setTitle(String value) {
        title.setInputValue(value);
        return this;
    }
}
