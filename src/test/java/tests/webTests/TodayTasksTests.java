package tests.webTests;

import allure.Layer;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import pageObjects.pages.TodayPage;

import static api.steps.TaskCreationApiSteps.newTaskCreation;
import static api.steps.TaskRemovalApiSteps.cleanupAllTasks;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Cookie.setCookieStep;

@Owner("m.remneva")
@Tag("web")
@Layer("web")
@Epic("Задачи")
@Feature("Задачи 'Сегодня'")
public class TodayTasksTests extends UITestBase {

    private static TodayPage todayPage = new TodayPage();
    private static final String URL_PART = "app/today/";

    @BeforeEach
    void preparation() {
        cleanupAllTasks();
        setCookieStep();
    }

    @Test
    @DisplayName("Создание новой задачи")
    public void taskCreationTest() {
        open(URL_PART);

        String taskTitle = "title";
        String taskDescription = "description";
        todayPage
                .createNewTaskForToday(taskTitle, taskDescription)
                .checkTaskItemContent(taskTitle, taskDescription);
    }

    @Test
    @DisplayName("Создание нескольких задач")
    public void tasksCreationTest() {
        open(URL_PART);

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
    @DisplayName("Редактирования существующей задачи")
    public void taskEditingTest() {
        String taskTitle1 = "title1";
        String taskDescription1 = "description1";
        String taskDue = "Сегодня";

        newTaskCreation(taskTitle1, taskDescription1, taskDue);

        open(URL_PART);
        todayPage.checkTaskItemContent(taskTitle1, taskDescription1);

        String taskTitle2 = "title2";
        String taskDescription2 = "description2";
        todayPage
                .editTaskByIndex(taskTitle2, taskDescription2, 0)
                .checkTaskItemContent(taskTitle2, taskDescription2, 0);
    }

    @Test
    @DisplayName("Завершение существующей задачи")
    public void taskCompletionTest() {
        String taskTitle = "title";
        String taskDescription = "description";
        String taskDue = "Сегодня";

        newTaskCreation(taskTitle, taskDescription, taskDue);

        open(URL_PART);

        todayPage
                .completeTaskByIndex(0)
                .checkNoTasksToday();
    }

    @Test
    @DisplayName("Удаление существующей задачи")
    public void taskDeletionTest() {
        String taskTitle = "title";
        String taskDescription = "description";
        String taskDue = "Сегодня";

        newTaskCreation(taskTitle, taskDescription, taskDue);

        open(URL_PART);

        todayPage
                .deleteTaskByIndex(0)
                .checkNoTasksToday();
    }

    @AfterEach
    void cleanup() {
        cleanupAllTasks();
    }
}
