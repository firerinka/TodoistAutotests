package pageObjects.pages;

import com.codeborne.selenide.Condition;
import pageObjects.elements.Button;
import pageObjects.elements.Input;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {
    private Input email = new Input("emailInput", $(byAttribute("type", "email")));
    private Input password = new Input("passwordInput", $(byAttribute("type", "password")));
    private Button submit = new Button("submitButton", $(byAttribute("type", "submit")));

    @Step("Вводим email")
    public AuthPage enterEmail(String value) {
        email.setInputValue(value);
        return this;
    }

    @Step("Вводим пароль")
    public AuthPage enterPassword(String value) {
        password.setInputValue(value);
        return this;
    }

    @Step("Авторизуемся")
    public AuthPage submit() {
        submit.click();
        return this;
    }

    @Step("Проверяем ошибку авторизации")
    public AuthPage checkAuthErrorMessage() {
        $(byText("Wrong email or password.")).should(Condition.appear);
        return this;
    }
}
