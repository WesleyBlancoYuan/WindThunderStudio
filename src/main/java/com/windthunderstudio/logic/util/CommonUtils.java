package com.windthunderstudio.logic.util;

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
    
}
