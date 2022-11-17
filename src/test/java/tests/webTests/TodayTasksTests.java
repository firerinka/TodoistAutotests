package tests.webTests;

import allure.Layer;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import pageObjects.pages.TodayPage;

import static api.steps.TaskAPISteps.cleanUpAllTasks;
import static api.steps.TaskAPISteps.newTaskCreation;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

@Owner("m.remneva")
@Tag("web")
@Layer("web")
@Epic("Задачи")
@Feature("Задачи 'Сегодня'")
public class TodayTasksTests extends UITestBase {

    private static TodayPage todayPage = new TodayPage();
    private static final String URL_PART = "app/today/";

    @BeforeAll
    static void solveTimezoneIssue() {
        setCookieStep();
        open(URL_PART);
        /* Комментарий для проверяющих:
         Да, это костыль :)
         не придумала ничего лучше, чтобы решить проблему с попапом,
         который выскакивает при несовпадении таймзоны аккаунта с таймзоной окружения
         без этого невозможно гонять тесты одновременно и локально, и на селенойде :( */
        todayPage.checkLoaderIsNotVisible();
        sleep(5000);
        executeJavaScript("document.querySelector('.GB_iframe_html .timezone_button')?.click();");
    }

    @BeforeEach
    void preparation() {
        cleanUpAllTasks();
        setCookieStep();
    }

    @Test
    @DisplayName("Проверка создания задачи")
    public void taskCreationTest() {
        open(URL_PART);

        String taskTitle = "title";
        String taskDescription = "description";
        todayPage
                .createNewTaskForToday(taskTitle, taskDescription)
                .checkTaskItemContent(taskTitle, taskDescription);
    }

    @Test
    @DisplayName("Проверка создания нескольких задач")
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
    @DisplayName("Проверка редактирования существующей задачи")
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
    @DisplayName("Проверка завершения задачи")
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

    private static void setCookieStep() {
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
        cleanUpAllTasks();
    }
}
