package tests.webTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.TodayPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class TodayTasksTests extends TestBase {

    private TodayPage todayPage = new TodayPage();

    @BeforeEach
    void preparation() {
        setCookieStep();
        open("");
        todayPage.removeAllTasks();
    }

    @Test
    @DisplayName("Проверка создания задачи")
    public void taskCreationTest() {
        String taskTitle = "title";
        String taskDescription = "description";
        todayPage
                .createNewTaskForToday(taskTitle, taskDescription)
                .checkTaskItemContent(taskTitle, taskDescription);
    }

    @Test
    @DisplayName("Проверка создания нескольких задач")
    public void tasksCreationTest() {
        String taskTitle1 = "title1";
        String taskDescription1 = "description1";
        String taskTitle2 = "title2";
        String taskDescription2 = "description2";
        todayPage
                .createNewTaskForToday(taskTitle1, taskDescription1)
                .createNextNewTaskForToday(taskTitle2, taskDescription2)
                .checkTaskItemContent(taskTitle1, taskDescription1, 0)
                .checkTaskItemContent(taskTitle2, taskDescription2, 1);
    }

    @Test
    @DisplayName("Проверка редактирования существующей задачи")
    public void taskEditingTest() {
        //TODO потом сделать создание задачи через API
        String taskTitle1 = "title1";
        String taskDescription1 = "description1";
        String taskTitle2 = "title2";
        String taskDescription2 = "description2";
        todayPage
                .createNewTaskForToday(taskTitle1, taskDescription1)
                .editTaskByIndex(taskTitle2, taskDescription2, 0)
                .checkTaskItemContent(taskTitle2, taskDescription2, 0);
    }

    @Test
    @DisplayName("Проверка закомпличивания задачи")
    public void taskCompletionTest() {
        //TODO потом сделать создание задачи через API
        String taskTitle = "title";
        String taskDescription = "description";
        todayPage
                .createNewTaskForToday(taskTitle, taskDescription)
                .completeTaskByIndex(0)
                .checkNoTasksToday();
    }

    private void setCookieStep() {
        //TODO подумать, как получать куки запросом
        step("Set cookie to to browser", () -> {
            open("/static/home/features/get-more-done-1008.webp");
            getWebDriver().manage().addCookie(
                    new Cookie("todoistd",
                            "fulc19zW+hHS0qpGH593BuGxjqI=?pCHK=gASVJAAAAAAAAACMIGNlMTU2ODViMTBhMTZjNjAyNGYxZDMxZDA4OGY4NjczlC4=&user_id=gASVBgAAAAAAAABKnSF/Ai4="));
        });
    }

    @AfterEach
    void cleanup() {
        //TODO заменить на очистку через API
        todayPage.removeAllTasks();
    }
}
