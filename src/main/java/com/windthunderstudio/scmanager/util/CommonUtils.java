package com.windthunderstudio.scmanager.util;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonUtils {
    private static final Logger log = LogManager.getLogger(CommonUtils.class);

    /**
     * Change the first letter of a String to capitalized.
     * @param input
     * @return
     */
    public static String capitalizeString(String input) {
        char[] old = input.toCharArray();
        Character.toUpperCase(old[0]);
        return new String(old);
    }
    
    public static boolean isBlankString(String input) {
        if (input == null) {
            return true;
        } else if (input.isEmpty()) {
            return true;
        } else {
            if (input.trim().equals("")) {
                return true;
            } else {
                return false;
            }
        } 
    }
    
    public static List<File> recursiveSearchFile(Path root) {
        List<File> pref = new ArrayList<File>();
        try {
            DirectoryStream<Path> all = Files.newDirectoryStream(root, CTS.PREF_GLOB_FILTER);
            for (Path path: all) {
                if (path.toFile().isDirectory()) {
                    recursiveSearchFile(path);
                } else {
                    pref.add(path.toFile());
                }
            }
            return pref;
        } catch (Exception e) {
            log.error("Error when searching pref files under " + root.toString() + ". ", e);
            return pref;
        }
    }
}
