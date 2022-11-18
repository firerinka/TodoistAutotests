package tests.mobileTests;

import allure.Layer;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.steps.TaskRemovalApiSteps.cleanupAllTasks;
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

        step("Создаем новую задачу", () -> {
            $(AppiumBy.id("com.todoist:id/fab")).click();
            $(AppiumBy.id("android:id/message")).sendKeys(taskTitle);
            $(AppiumBy.id("com.todoist:id/description")).sendKeys(taskDescription);
            $(AppiumBy.id("android:id/button1")).click();
            $(AppiumBy.id("com.todoist:id/item")).click();
        });

        step("Проверяем, что задача создана", () -> {
            $(AppiumBy.id("com.todoist:id/text")).shouldHave(Condition.text(taskTitle));
            $(AppiumBy.id("com.todoist:id/description")).shouldHave(Condition.text(taskDescription));
        });
    }

    public void login() {
        //m.remneva.test@gmail.com
        String pwd = "5YHEiSRquW2u";
        $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click();
        $(AppiumBy.id("com.google.android.gms:id/credential_avatar")).click();
        $(AppiumBy.id("com.todoist:id/log_in_password")).sendKeys(pwd);
        $(AppiumBy.id("com.todoist:id/btn_log_in")).click();
        $(AppiumBy.id("android:id/button3")).click();
    }

    @AfterEach
    void cleanup() {
        cleanupAllTasks();
    }
}
