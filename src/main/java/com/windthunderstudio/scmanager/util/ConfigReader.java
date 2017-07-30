package com.windthunderstudio.scmanager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static Properties config = PropReader.readProp(CTS.CONFIG_PROP_PATH);
    private static final Logger log = LogManager.getLogger(ConfigReader.class);
    public static void reloadConfig() {
        config = PropReader.readProp(CTS.CONFIG_PROP_PATH);
    }
    
    public static Properties loadConfig() {
        return config;
    }

    public static void config(String key, String value) {
        config.setProperty(key, value);
        OutputStream out;
        try {
            out = new FileOutputStream(new File(CTS.CONFIG_PROP_PATH));
            config.store(out, "");
            out.close();
        } catch (IOException e) {
            log.error("Cannot write to config file: ", e);
        }
        
    }
}
