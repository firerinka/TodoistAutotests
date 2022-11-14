package tests.webTests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.TodayPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class TodayTasksTests extends TestBase {

    private TodayPage todayPage = new TodayPage();

    @BeforeEach
    void authorisation() {
        setCookieStep();
        open("");
    }

    @Test
    @Description("Проверка создания задачи")
    public void taskCreationCheck() {
        todayPage.createNewTaskForToday("title", "description")
                .checkTaskItemContent("title", "description")
                .createNextNewTaskForToday("title2", "description2");
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
