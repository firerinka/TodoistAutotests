package tests.mobileTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.ProjectConfiguration;
import config.TestConfig;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.AttachToRemove;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.MobileEnvironment.BROWSERSTACK;
import static config.MobileEnvironment.EMULATION;

public class TestBase {
    private static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    @BeforeAll
    public static void setup() throws Exception {
        if (config.mobileEnv().equals(BROWSERSTACK)) {
            Configuration.browser = BrowserstackMobileDriver.class.getName();
        } else if(config.mobileEnv().equals(EMULATION)) {
            Configuration.browser = LocalMobileDriver.class.getName();
        } else {
            throw new Exception("Unrecognised mobileEnvironment");
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public void afterEach() {
        AttachToRemove.screenshotAs("Last screenshot");
        AttachToRemove.pageSource();

        if (config.mobileEnv().equals(BROWSERSTACK)) {
            String sessionId = sessionId().toString();
            closeWebDriver();
            AttachToRemove.video(sessionId);
        } else {
            Selenide.closeWebDriver();
        }
    }
}
