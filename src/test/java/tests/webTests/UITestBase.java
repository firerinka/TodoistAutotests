package tests.webTests;

import api.steps.UserSteps;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Browser;
import config.ProjectConfiguration;
import config.TestConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class UITestBase {
    private static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    @BeforeAll
    public static void setup() {
        UserSteps.setTimezone(config.timezone());
        SelenideLogger.addListener("helpers/allureAnnotations", new AllureSelenide());
        ProjectConfiguration.webConfig();
        ProjectConfiguration.apiConfig();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (config.browser().equals(Browser.CHROME)) {
            Attach.browserConsoleLogs();
        }
        if (config.isRemote()) {
            Attach.addWebVideo(ProjectConfiguration.getVideoStorageUrl());
        }
    }
}
