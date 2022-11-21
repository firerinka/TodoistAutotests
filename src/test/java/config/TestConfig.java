package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
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

    @Key("browserstack.userName")
    String browserstackUserName();

    @Key("browserstack.password")
    String browserstackPassword();

    @Key("app.appUrl")
    String appUrl();

    @Key("app.package")
    @DefaultValue("com.todoist")
    String appPackage();

    @Key("app.activity")
    @DefaultValue("com.todoist.alias.HomeActivityDefault")
    String appActivity();

    @Key("app.name")
    @DefaultValue("todoist.apk")
    String appName();

    @Key("device.deviceName")
    @DefaultValue("Pixel_4_API_30")
    String mobileDeviceName();

    @Key("device.os_version")
    @DefaultValue("11.0")
    String mobileDeviceOsVersion();

    @Key("device.platformName")
    @DefaultValue("Android")
    String mobilePlatformName();

    @Key("browserstack.project")
    String browserstackProject();

    @Key("browserstack.build")
    String browserstackBuild();

    @Key("browserstack.testsName")
    String browserstackTestsName();

    @Key("server.remoteUrl")
    @DefaultValue("http://localhost:4723/wd/hub")
    String mobileServerRemoteUrl();

    @Key("mobileEnvironment")
    @DefaultValue("EMULATION")
    MobileEnvironment mobileEnv();

    @Key("browserstack.sessionsUrl")
    String browserstackSessionsUrl();
}
