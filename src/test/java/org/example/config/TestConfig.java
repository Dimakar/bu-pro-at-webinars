package org.example.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "system:env", "file:src/test/resources/${stand}.properties"})
public interface TestConfig extends Config {
    String baseUrl();
    String baseURI();

    @DefaultValue("chrome")
    String browser();
    @DefaultValue("101.0")
    String browserVersion();

    @DefaultValue("2000")
    int timeout();

    @DefaultValue("http://192.168.235.95:4444/wd/hub")
    String selenoidUrl();
}
