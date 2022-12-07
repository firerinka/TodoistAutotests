package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.elements.Button;
import pages.elements.Element;

public class TopBar extends Element {
    private final Button userMenuButton = new Button("userMenu",
            selector.$(".settings_btn"));

    public TopBar(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("Открываем меню пользователя")
    public TopBar openUserMenu() {
        userMenuButton.click();
        return this;
    }
}
