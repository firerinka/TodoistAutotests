package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import io.qameta.allure.Step;
import pages.components.TaskEditor;

import static com.codeborne.selenide.Selenide.$;

public class TodayPage {
    private SelenideElement title = $(".view_header__content .simple_content");
    private SelenideElement loader = $("#loading");
    private Button addTaskButton = new Button("addTask", $(".plus_add_button"));
    private TaskEditor taskEditor = new TaskEditor("taskEditor", $(".task_editor"));

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

    @Step("Нажимаем на кнопку добавления задачи")
    public TodayPage addTaskButtonClick() {
        addTaskButton.click();
        return this;
    }

    @Step("Заполняем данные задачи")
    public TodayPage fillTaskData(String titleValue) {
        taskEditor.setTitle(" " + titleValue);
        return this;
    }

}
