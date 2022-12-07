package pages.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class Button extends Element {
    public Button(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("Нажимаем кнопку '{this.name}'")
    @Override
    public void click() {
        System.out.println("Нажимаем кнопку " + name);
        selector.click();
    }
}
