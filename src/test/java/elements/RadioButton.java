package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;

public class RadioButton extends Element {
    public RadioButton(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("В radioButton {this.name} выбираем значение {labelName}")
    public void setByLabelName(String labelName) {
        System.out.println("В radioButton " + this.name + "выбираем значение " + labelName);
        selector.$(byText(labelName)).click();
    }
}
