package org.example.config;


import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;

public class Config {
    static {
        if (System.getProperty("stand") == null) {
            ConfigFactory.setProperty("stand", "demo");
        }
    }

    public static TestConfig getConfig() {
        return ConfigCache.getOrCreate("main", TestConfig.class);
    }
}
