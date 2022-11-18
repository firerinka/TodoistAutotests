package helpers;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class Cookie {
    public static void setCookieStep() {
        String pictureUrl = "/_next/static/images/logo_calendar_1e7f4d38ee669ac13f651a09552deaf6.webp";
        String cookieName = "todoistd";
        String cookieValue = "fulc19zW+hHS0qpGH593BuGxjqI=?pCHK=gASVJAAAAAAAAACMIGNlMTU2ODViMTBhMTZjNjAyNGYxZDMxZDA4OGY4NjczlC4=&user_id=gASVBgAAAAAAAABKnSF/Ai4=";
        step("Set cookie to to browser", () -> {
            open(pictureUrl);
            getWebDriver().manage().addCookie(
                    new org.openqa.selenium.Cookie(cookieName, cookieValue));
        });
    }
}
