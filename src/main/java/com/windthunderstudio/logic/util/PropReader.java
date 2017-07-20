package com.windthunderstudio.logic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropReader {
    
    public static Properties readProp(String path) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            File f = new File(path);
            if (f.exists()) {
                input = new FileInputStream(f);
                prop.load(input);
                
            } else {
                System.out.println("No properties file is found. Double check the path: " + path);
                return null;
            }
            if (input != null) input.close();
            return prop;
        } catch (IOException e) {
            System.err.println("Input/output problem when loading properties: " + e);
        }
        return null;
    }
}
