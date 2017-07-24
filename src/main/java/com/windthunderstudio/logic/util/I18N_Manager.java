package com.windthunderstudio.logic.util;

import java.util.Properties;

public class I18N_Manager {
    private static Properties localeProp = loadLocaleProp();
    public static boolean isChinese;
    /**
     * Reload the locale settings, changing <code>I18N_Manager.localeProp</code>
     * according to the config file.
     * <br /><br/ >
     * To update locale settings in the config file, please call 
     * <code>ConfigReader.reloadConfig()</code> before calling this.
     */
    public static void reloadLocale() {
        localeProp = loadLocaleProp();
    }
    
    public static Properties loadLocale() {
        return localeProp;
    }
    
    private static Properties loadLocaleProp() {
        String localeString = ConfigReader.loadConfig().getProperty(CTS.CONFIG_KEY_LOCALE);
        String localePath = "";
        try {
            switch (localeString) {
            case CTS.LOCALE_STR_ES:
                localePath = CTS.LOCALE_PATH_PREFIX + CTS.LOCALE_STR_ES + "." + CTS.LOCALE_PATH_SUFFIX;
                break;
                
            case CTS.LOCALE_STR_EN:
                localePath = CTS.LOCALE_PATH_PREFIX + CTS.LOCALE_STR_EN + "." + CTS.LOCALE_PATH_SUFFIX;
                break;
                
            case CTS.LOCALE_STR_ZH_HANS:
                isChinese = true;
                localePath = CTS.LOCALE_PATH_PREFIX + CTS.LOCALE_STR_ZH_HANS + "." + CTS.LOCALE_PATH_SUFFIX;
                break;
                
            default:
                localePath = CTS.LOCALE_PATH_PREFIX + CTS.LOCALE_STR_ES + "." + CTS.LOCALE_PATH_SUFFIX;
                break;
            }
            return PropReader.readProp(localePath);
        } catch (Exception e) {
            System.err.println("Error loading locale settings: " + e);
        }
        return null;
    }
}
