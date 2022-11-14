package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import io.qameta.allure.Step;
import pages.components.TaskEditor;
import pages.components.TaskItem;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodayPage {
    private SelenideElement title = $(".view_header__content .simple_content");
    private SelenideElement loader = $("#loading");
    private Button addTaskButton = new Button("addTask", $(".plus_add_button"));
    private TaskEditor taskEditor = new TaskEditor("taskEditor", $(".task_editor"));
    private TaskItem taskItem = new TaskItem("taskItem", $(".task_list_item__content"));

    @Step("Проверяем, что заголовок страницы - это '{value}'")
    public TodayPage checkTitle(String value) {
        checkLoaderIsNotVisible();
        title.isDisplayed();
        title.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Проверяем, что лоадер закрыт")
    public TodayPage checkLoaderIsNotVisible() {
        loader.shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Создаем новую задачу на сегодня через редактор")
    public TodayPage createNewTaskForToday(String titleValue, String descriptionValue) {
        addTaskButton.click();
        taskEditor.setTitle(" " + titleValue).setDescription(descriptionValue).submitTask();
        return this;
    }

    @Step("Создаем следующую новую задачу на сегодня через редактор")
    public TodayPage createNextNewTaskForToday(String titleValue, String descriptionValue) {
        taskEditor.setTitle(" " + titleValue).setDescription(descriptionValue).submitTask();
        return this;
    }

    @Step("Проверяем контент задачи")
    public TodayPage checkTaskItemContent(String titleValue, String descriptionValue) {
        taskItem
                .checkTitle(titleValue)
                .checkDescription(descriptionValue);
        return this;
    }

    @Step("Удаляем задачу")
    public void removeAllTasks(){
        while (taskItem.checkTaskItemIsPresent()) {
            taskItem.hover();
            taskItem.removeTaskItem();
        }
    }

}
