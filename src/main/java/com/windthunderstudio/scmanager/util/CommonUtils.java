package com.windthunderstudio.scmanager.util;

import javax.print.DocFlavor.STRING;

public class CommonUtils {
    
    /**
     * 
     * @param input
     * @return
     */
    public static String capitalizeString(String input) {
        char[] old = input.toCharArray();
        Character.toUpperCase(old[0]);
        return new String(old);
    }
}
