package tests.webTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import config.Browser;
import config.ProjectConfiguration;
import helpers.AttachOld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.TestBase;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class UITestBase extends TestBase {

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
