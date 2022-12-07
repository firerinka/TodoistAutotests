package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.elements.Element;

public class UserMenu extends Element {
    private final Element userEmail = new Element("userEmail",
            selector.$(".user_menu .user_menu_email"));

    public UserMenu(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("Проверяем, что email пользователя имеет значение '{value}'")
    public UserMenu checkUserEmail(String value) {
        userEmail.checkText(value);
        return this;
    }
}
