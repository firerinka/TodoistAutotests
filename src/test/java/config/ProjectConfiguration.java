package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProjectConfiguration {
    public static final TestConfig TEST_CONFIG = ConfigReader.Instance.read();

    public static void apiConfig() {
        RestAssured.baseURI = TEST_CONFIG.baseUrl();
    }

    public static void webConfig() {
        Configuration.baseUrl = TEST_CONFIG.baseUrl();
        Configuration.browser = TEST_CONFIG.browser().toString();
        Configuration.browserVersion = TEST_CONFIG.browserVersion();
        Configuration.browserSize = TEST_CONFIG.browserSize();
        if (TEST_CONFIG.isRemote()) {
            Configuration.remote = TEST_CONFIG.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }

    public static String getVideoStorageUrl() {
        return TEST_CONFIG.videoStorage();
    }

    public static Boolean isRemote() {
        return TEST_CONFIG.isRemote();
    }
}
