package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ProjectConfiguration;
import config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class LocalMobileDriver implements WebDriverProvider {
    private static TestConfig config = ProjectConfiguration.TEST_CONFIG;

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.mobileServerRemoteUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(config.mobilePlatformName());
        options.setDeviceName(config.mobileDeviceName());
        options.setPlatformVersion(config.mobileDeviceOsVersion());
        options.setApp(app.getAbsolutePath());
        options.setAppPackage(config.appPackage());
        options.setAppActivity(config.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private File getApp() {
        String appUrl = config.appUrl();
        String appPath = LocalMobileDriver.class.getClassLoader().getResource("apps/" + config.appName()).getPath();

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }

}
