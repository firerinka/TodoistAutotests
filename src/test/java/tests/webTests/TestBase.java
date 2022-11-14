package tests.webTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import config.Browser;
import config.ProjectConfiguration;
import config.TestConfig;
import helpers.AttachOld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class TestBase {
    private static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ProjectConfiguration.webConfig();
        ProjectConfiguration.apiConfig();
    }

    @AfterEach
    void addAttachments() {
        AttachOld.screenshotAs("Last screenshot");
        AttachOld.pageSource();
        if (Configuration.browser.equals(Browser.CHROME.name())) {
            AttachOld.browserConsoleLogs();
        }
        if (config.isRemote()) {
            AttachOld.addVideo(ProjectConfiguration.getVideoStorageUrl());
        }
    }

}
