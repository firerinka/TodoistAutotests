package pageObjects.components;

import com.codeborne.selenide.SelenideElement;
import pageObjects.elements.Button;
import pageObjects.elements.Element;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class TaskItem extends Element {

    private final Element title = new Element("taskTitle",
            selector.$(".task_list_item__content .task_content"));
    private final Element description = new Element("taskDescription",
            selector.$(".task_list_item__content .task_description"));
    private final Button moreButton = new Button("moreMenu",
            selector.$(byAttribute("data-testid", "more_menu")));
    private final Button editButton = new Button("editButton",
            selector.$(byAttribute("data-action-hint", "task-edit")));
    private final Button completionButton = new Button("completionButton",
            selector.$(byAttribute("data-action-hint", "task-complete")));

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

    @Step("Открываем редактор задачи")
    public TaskItem openTaskEditor() {
        this.hover();
        editButton.click();
        return this;
    }

    @Step("Комплитим задачу")
    public TaskItem completeTask() {
        completionButton.click();
        return this;
    }

    @Step("Удаляем задачу")
    public void removeTaskItem() {
        this.hover();
        moreButton.click();
        $(".menu_list")
                .$(byAttribute("data-action-hint", "task-overflow-menu-delete"))
                .click();

        $(byAttribute("role", "dialog"))
                .$(byAttribute("type", "submit"))
                .click();
    }
}
