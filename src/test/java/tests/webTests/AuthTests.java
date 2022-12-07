package tests.webTests;

import config.ProjectConfiguration;
import helpers.allureAnnotations.Layer;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.pages.AuthPage;
import pages.pages.TodayPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("m.remneva")
@Tag("web")
@Layer("web")
@Feature("Авторизация")
public class AuthTests extends UITestBase {

    private final String URL_PART = "auth/login/";
    private AuthPage authPage = new AuthPage();
    private TodayPage todayPage = new TodayPage();

    private final String USER_EMAIL = ProjectConfiguration.TEST_CONFIG.userEmail();
    private final String USER_PASSWORD = ProjectConfiguration.TEST_CONFIG.userPassword();

    @Test
    @DisplayName("Авторизация с верными данными")
    public void successfulAuthTest() {
        step("Открываем страницу авторизации", () ->
            open(URL_PART)
        );

        step("Пытаемся авторизоваться", () -> {
            authPage.enterEmail(USER_EMAIL)
                    .enterPassword(USER_PASSWORD)
                    .submit();
        });

        step("Проверяем успешность авторизации", () -> {
            todayPage
                    .checkTitle("Сегодня")
                    .checkCurrentUser(USER_EMAIL);
        });
    }

    @Test
    @DisplayName("Ошибка авторизации с неверным паролем")
    public void wrongPasswordAuthTest() {
        String invalidPassword = "1234567890";

        step("Открываем страницу авторизации", () ->
                open(URL_PART)
        );

        step("Пытаемся авторизоваться", () -> {
            authPage.enterEmail(USER_EMAIL)
                    .enterPassword(invalidPassword)
                    .submit();
        });

        step("Проверяем, что появилось сообщение об ошибке", () -> {
            authPage.checkAuthErrorMessage();
        });
    }

    @Test
    @DisplayName("Ошибки авторизации для несуществующего пользователя")
    public void wrongEmailAuthTest() {
        String invalidEmail = "test@test.com";
        String invalidPassword = "1234567890";

        step("Открываем страницу авторизации", () ->
                open(URL_PART)
        );

        step("Пытаемся авторизоваться", () -> {
            authPage.enterEmail(invalidEmail)
                    .enterPassword(invalidPassword)
                    .submit();
        });

        step("Проверяем, что появилось сообщение об ошибке", () -> {
            authPage.checkAuthErrorMessage();
        });
    }

}
