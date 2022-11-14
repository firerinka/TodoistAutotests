package config;

import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    Instance;

    private static final TestConfig TEST_CONFIG =
            ConfigFactory.create(
                    TestConfig.class,
                    System.getProperties()
            );

    public TestConfig read() {
        return TEST_CONFIG;
    }
}
