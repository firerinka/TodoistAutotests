package tests.webTests;

import api.steps.UserSteps;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import config.Browser;
import config.ProjectConfiguration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.TestBase;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class UITestBase extends TestBase {
    @BeforeAll
    static void setTimezone() {
        if (config.isRemote()) {
            UserSteps.setTimezone("UTC");
        } else {
            UserSteps.setTimezone("Europe/Moscow");
        }
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
