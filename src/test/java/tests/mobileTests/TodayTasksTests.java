package tests.mobileTests;

import allure.Layer;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.steps.TasCreationApiSteps.newTaskCreation;
import static api.steps.TaskRemovalApiSteps.cleanupAllTasks;
import static api.steps.UserSteps.setTimezone;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Owner("m.remneva")
@Tag("mobile")
@Layer("mobile")
@Epic("Задачи")
@Feature("Задачи 'Сегодня'")
public class TodayTasksTests extends MobileTestBase {
    @Test
    @DisplayName("Создание новой задачи")
    public void taskCreationTest() {
        login();
        String taskTitle = "title";
        String taskDescription = "description";

        createNewTaskStep(taskTitle, taskDescription);

        step("Проверяем, что задача создана", () -> {
            $(AppiumBy.id("com.todoist:id/text")).shouldHave(Condition.text(taskTitle));
            $(AppiumBy.id("com.todoist:id/description")).shouldHave(Condition.text(taskDescription));
        });
    }

    @Test
    @DisplayName("Завершение существующей задачи")
    public void taskCompletionTest() {
        String taskTitle = "title";
        String taskDescription = "description";
        String taskDue = "Сегодня";

        newTaskCreation(taskTitle, taskDescription, taskDue);

        login();
        step("Завершаем задачу", () -> {
            $(AppiumBy.id("com.todoist:id/checkmark")).click();
        });

        step("Проверяем, что задача исчезла из списка задач на Сегодня", () -> {
            $(AppiumBy.id("com.todoist:id/item")).shouldNot(Condition.exist);
        });
    }

    @Test
    @DisplayName("Удаление существующей задачи")
    public void taskDeletionTest() {
        String taskTitle = "title";
        String taskDescription = "description";
        String taskDue = "Сегодня";

        newTaskCreation(taskTitle, taskDescription, taskDue);

        login();

        step("Удаляем задачу", () -> {
            $(AppiumBy.id("com.todoist:id/text")).click();
            $(AppiumBy.id("com.todoist:id/item_overflow")).click();
            $(AppiumBy.id("com.todoist:id/delete")).click();
            $(AppiumBy.id("android:id/button1")).click();
        });

        step("Проверяем, что задача исчезла из списка задач на Сегодня", () -> {
            $(AppiumBy.id("com.todoist:id/item")).shouldNot(Condition.exist);
        });
    }

    @Test
    @DisplayName("Редактирование существующей задачи")
    public void taskEditionTest() {
        String taskTitle = "title";
        String taskDescription = "description";
        String taskDue = "Сегодня";

        newTaskCreation(taskTitle, taskDescription, taskDue);

        login();
        String title2 = "title2";
        String description2 = "description2";

        step("Редактируем задачу", () -> {
            $(AppiumBy.id("com.todoist:id/text")).click();
            $(AppiumBy.id("com.todoist:id/item_content")).click();
            $(AppiumBy.id("com.todoist:id/item_content")).sendKeys(title2);
            $(AppiumBy.id("com.todoist:id/item_description")).sendKeys(description2);
            $(AppiumBy.id("com.todoist:id/item_submit")).click();
        });

        step("Проверяем, что задача отредактирована", () -> {
            $(AppiumBy.id("com.todoist:id/item_content")).shouldHave(Condition.text(title2));
            $(AppiumBy.id("com.todoist:id/item_description")).shouldHave(Condition.text(description2));
            Selenide.back();
            $(AppiumBy.id("com.todoist:id/text")).shouldHave(Condition.text(title2));
            $(AppiumBy.id("com.todoist:id/description")).shouldHave(Condition.text(description2));
        });
    }

    @Step("Авторизуемся в приложении и закрываем баннер про таймзону")
    public void login() {
        //m.remneva.test@gmail.com
        String pwd = "5YHEiSRquW2u";
        $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click();
        $(AppiumBy.id("com.google.android.gms:id/credential_avatar")).click();
        $(AppiumBy.id("com.todoist:id/log_in_password")).sendKeys(pwd);
        $(AppiumBy.id("com.todoist:id/btn_log_in")).click();
        $(AppiumBy.id("com.todoist:id/toolbar")).should(Condition.exist);
    }

    @AfterEach
    void cleanup() {
        cleanupAllTasks();
    }

    @Step("Создаем новую задачу с title='{taskTitle}', description='{taskDescription}'")
    private void createNewTaskStep(String taskTitle, String taskDescription) {
        $(AppiumBy.id("com.todoist:id/fab")).click();
        $(AppiumBy.id("android:id/message")).sendKeys(taskTitle);
        $(AppiumBy.id("com.todoist:id/description")).sendKeys(taskDescription);
        $(AppiumBy.id("android:id/button1")).click();
        $(AppiumBy.id("com.todoist:id/item")).click();
    }
}
