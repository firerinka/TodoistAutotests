package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Element;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class TaskItem extends Element {

    private Element title = new Element("taskTitle", $(".task_list_item__content .task_content"));
    private Element description = new Element("taskDescription", $(".task_list_item__content .task_description"));
    private Button moreButton = new Button("moreMenu", $(byAttribute("data-testid", "more_menu")));

    public TaskItem(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("Проверяем заголовок задачи")
    public TaskItem checkTitle(String value) {
        title.checkText(value);
        return this;
    }

    @Step("Проверяем описание задачи")
    public TaskItem checkDescription(String value) {
        description.checkText(value);
        return this;
    }

    @Step("Удаляем задачу")
    public void removeTaskItem() {
        moreButton.click();
        $(".menu_list")
                .$(byAttribute("data-action-hint", "task-overflow-menu-delete"))
                .click();

        $(byAttribute("role", "dialog"))
                .$(byAttribute("type", "submit"))
                .click();
    }

    public boolean checkTaskItemIsPresent() {
        return this.selector.is(Condition.visible);
    }
}
