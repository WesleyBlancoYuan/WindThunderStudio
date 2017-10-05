package com.windthunderstudio.logic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropReader {
    private static final Logger log = LogManager.getLogger(PropReader.class);
    
    public static Properties readProp(String path) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
//            input = new FileInputStream(new File(path));
            input = PropReader.class.getResourceAsStream(path);
            prop.load(input);
            input.close();
            return prop;
        } catch (IOException e) {
            log.error("Cannot read config file: " + e);
        }
        return null;
    }
}
