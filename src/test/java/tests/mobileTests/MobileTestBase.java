package tests.mobileTests;

import api.steps.UserSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ProjectConfiguration;
import config.TestConfig;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.MobileEnvironment.BROWSERSTACK;
import static config.MobileEnvironment.EMULATION;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class MobileTestBase {
    private static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    @BeforeAll
    public static void setup() throws Exception {
        UserSteps.setTimezone(config.timezone());
        SelenideLogger.addListener("helpers/allureAnnotations", new AllureSelenide());
        ProjectConfiguration.apiConfig();

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
    public void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        if (config.mobileEnv().equals(BROWSERSTACK)) {
            String sessionId = sessionId().toString();
            closeWebDriver();
            Attach.addMobileVideo(sessionId);
        } else {
            Selenide.closeWebDriver();
        }
    }

}
