package tests.webTests;

import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import config.Browser;
import config.ProjectConfiguration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.TestBase;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class UITestBase extends TestBase {

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
