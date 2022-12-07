package pages.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.*;
import pages.elements.Button;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodayPage {
    private final SelenideElement title = $(".view_header__content .simple_content");
    private final SelenideElement loader = $("#loading");
    private final Button addTaskButton = new Button("addTask", $(".plus_add_button"));
    private final TaskEditor taskEditor = new TaskEditor("taskEditor", $(".task_editor"));
    private final TaskItem taskItem = new TaskItem("taskItem", $(".task_list_item__content"));
    private final TaskItems taskItems = new TaskItems("taskItems", $$(".task_list_item"));
    private final TopBar topBar = new TopBar("topBar", $("#top_bar"));
    private final UserMenu userMenu = new UserMenu("userMenu", $(".user_menu"));

    @Step("Проверяем, что заголовок страницы - это '{value}'")
    public TodayPage checkTitle(String value) {
        checkLoaderIsNotVisible();
        title.shouldBe(Condition.visible);
        title.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Проверяем, что лоадер закрыт")
    public TodayPage checkLoaderIsNotVisible() {
        loader.should(disappear);
        return this;
    }

    @Step("Создаем новую задачу на сегодня через редактор")
    public TodayPage createNewTaskForToday(String titleValue, String descriptionValue) {
        addTaskButton.click();
        taskEditor.setTitle(" " + titleValue).setDescription(descriptionValue).submitTaskByButton();
        return this;
    }

    @Step("Создаем следующую новую задачу на сегодня через редактор")
    public TodayPage createNextNewTaskForToday(String titleValue, String descriptionValue) {
        taskEditor.setTitle(" " + titleValue).setDescription(descriptionValue).submitTaskByButton();
        return this;
    }

    @Step("Редактируем существующую задачу c номером '{index}' через редактор")
    public TodayPage editTaskByIndex(String titleValue, String descriptionValue, int index) {
        TaskItem item = taskItems.getTaskItemByIndex(index);
        item.openTaskEditor();
        taskEditor.setTitle(titleValue);
        taskEditor.setDescription(descriptionValue);
        taskEditor.submitTaskByButton();
        return this;
    }

    @Step("Завершаем задачу c номером '{index}'")
    public TodayPage completeTaskByIndex(int index) {
        TaskItem item = taskItems.getTaskItemByIndex(index);
        item.completeTask();
        return this;
    }

    @Step("Удаляем задачу c номером '{index}'")
    public TodayPage deleteTaskByIndex(int index) {
        TaskItem item = taskItems.getTaskItemByIndex(index);
        item.removeTaskItem();
        return this;
    }

    @Step("Проверяем, что нет активных задач")
    public void checkNoTasksToday() {
        taskItems.checkNoTasksPresent();
    }

    @Step("Проверяем контент задачи")
    public TodayPage checkTaskItemContent(String titleValue, String descriptionValue) {
        taskItem
                .checkTitle(titleValue)
                .checkDescription(descriptionValue);
        return this;
    }

    @Step("Проверяем контент задачи с номером '{index}'")
    public TodayPage checkTaskItemContent(String titleValue, String descriptionValue, int index) {
        TaskItem item = taskItems.getTaskItemByIndex(index);
        item
                .checkTitle(titleValue)
                .checkDescription(descriptionValue);
        return this;
    }

    @Step("Удаляем все задачи со страницы 'Сегодня'")
    public void removeAllTasks(){
        taskItems.removeAllTasks();
    }

    @Step("Проверяем, что залогинены под верным пользователем")
    public void checkCurrentUser(String value) {
        topBar.openUserMenu();
        userMenu.checkUserEmail(value);
    }

}
