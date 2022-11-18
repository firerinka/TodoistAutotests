package tests.webTests;

import allure.Layer;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageObjects.pages.AuthPage;
import pageObjects.pages.TodayPage;

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

    @Test
    @DisplayName("Авторизации с верными данными")
    public void successfulAuthTest() {
        step("Открываем страницу авторизации", () ->
            open(URL_PART)
        );

        step("Пытаемся авторизоваться", () -> {
            authPage.enterEmail("m.remneva.test@gmail.com")
                    .enterPassword("5YHEiSRquW2u")
                    .submit();
        });

        step("Проверяем успешность авторизации, открылась страница 'Сегодня'", () -> {
            todayPage.checkTitle("Сегодня");
        });
    }

    @Test
    @DisplayName("Ошибка авторизации с неверным паролем")
    public void wrongPasswordAuthTest() {
        step("Открываем страницу авторизации", () ->
                open(URL_PART)
        );

        step("Пытаемся авторизоваться", () -> {
            authPage.enterEmail("m.remneva.test@gmail.com")
                    .enterPassword("1234567890")
                    .submit();
        });

        step("Проверяем, что появилось сообщение об ошибке", () -> {
            authPage.checkAuthErrorMessage();
        });
    }

    @Test
    @DisplayName("Ошибки авторизации для несуществующего пользователя")
    public void wrongEmailAuthTest() {
        step("Открываем страницу авторизации", () ->
                open(URL_PART)
        );

        step("Пытаемся авторизоваться", () -> {
            authPage.enterEmail("test@test.com")
                    .enterPassword("1234567890")
                    .submit();
        });

        step("Проверяем, что появилось сообщение об ошибке", () -> {
            authPage.checkAuthErrorMessage();
        });
    }

}
