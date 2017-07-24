package com.windthunderstudio.logic.util;

import java.util.Properties;

public class ConfigReader {
    private static Properties config = PropReader.readProp(CTS.CONFIG_PROP_PATH);
    
    public static void reloadConfig() {
        config = PropReader.readProp(CTS.CONFIG_PROP_PATH);
    }
    
    public static Properties loadConfig() {
        return config;
    }
}
