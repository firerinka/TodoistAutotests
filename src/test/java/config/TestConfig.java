package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/user.properties",
        "classpath:config/${env}.properties",
        "classpath:config/mobile.${deviceHost}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
})
public interface TestConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("browserVersion")
    @DefaultValue("107.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1280x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://todoist.com/")
    String baseUrl();

    @Key("apiUrl")
    @DefaultValue("https://api.todoist.com/rest/v2/")
    String apiUrl();

    @Key("syncApiUrl")
    @DefaultValue("https://api.todoist.com/sync/v9/sync")
    String syncApiUrl();

    @Key("apiToken")
    @DefaultValue("77a110ac1192de22ec0e4a9038d30bfe7fd5ce32")
    String apiToken();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("http://localhost:4444")
    String remoteUrl();

    @Key("videoStorage")
    String videoStorage();

    @Key("timezone")
    @DefaultValue("Europe/Moscow")
    String timezone();

    @Key("browserstackUserName")
    String browserstackUserName();

    @Key("browserstackPassword")
    String browserstackPassword();

    @Key("appUrl")
    String appUrl();

    @Key("appPackage")
    @DefaultValue("com.todoist")
    String appPackage();

    @Key("appActivity")
    @DefaultValue("com.todoist.alias.HomeActivityDefault")
    String appActivity();

    @Key("appName")
    @DefaultValue("todoist.apk")
    String appName();

    @Key("deviceName")
    @DefaultValue("Pixel_4_API_30")
    String mobileDeviceName();

    @Key("osVersion")
    @DefaultValue("11.0")
    String mobileDeviceOsVersion();

    @Key("platformName")
    @DefaultValue("Android")
    String mobilePlatformName();

    @Key("browserstackProject")
    String browserstackProject();

    @Key("browserstackBuild")
    String browserstackBuild();

    @Key("browserstackTestsName")
    String browserstackTestsName();

    @Key("mobileServerRemoteUrl")
    @DefaultValue("http://localhost:4723/wd/hub")
    String mobileServerRemoteUrl();

    @Key("mobileEnvironment")
    @DefaultValue("EMULATION")
    MobileEnvironment mobileEnv();

    @Key("browserstackSessionsUrl")
    String browserstackSessionsUrl();

    @Key("email")
    String userEmail();

    @Key("password")
    String userPassword();
}
