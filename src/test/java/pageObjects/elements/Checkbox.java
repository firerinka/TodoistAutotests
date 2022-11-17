package pageObjects.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;

public class Checkbox extends Element {
    public Checkbox(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("В '{this.name}' выбрать checkbox  с названием '{title}'")
    public void selectBoxByTitle(String title) {
        selector.$(byText(title)).click();
        System.out.println("В "+ this.name + " выбрать checkbox  с названием " + title);
    }
}
