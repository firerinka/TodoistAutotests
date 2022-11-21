package tests.apiTests;

import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ProjectConfiguration;
import config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class ApiTestBase {
    protected static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ProjectConfiguration.apiConfig();
    }

}
