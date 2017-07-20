package com.windthunderstudio.logic.util;

import java.awt.Font;

public class CTS {
    /* *************************** Pan-app values ***********************************/
    public static final Font ARIAL_PLAIN_12 = new Font("Arial", Font.PLAIN, 12);
    public static final Font ARIAL_BOLD_12 = new Font("Arial", Font.BOLD, 12);
    
    /* *************************** Config value *************************************/
    public static final String CONFIG_PROP_PATH = "src/main/resources/settings.properties"; //relative
    public static final String CONFIG_KEY_LOCALE = "locale";
    
    /* *************************** Locale values ************************************/
    public static final String LOCALE_PATH_PREFIX = "src/main/resources/_locales";
    public static final String LOCALE_PATH_SUFFIX = "locale";
    public static final String LOCALE_STR_ES = "es";
    public static final String LOCALE_STR_EN = "en";
    public static final String LOCALE_STR_ZH_HANS = "zh_hans";
    
    /* *************************** Menu label text***********************************/
    public static final String MENUITEM_LBL_TEXT_EXIT = "menuitem.lbl.text.exit";
    
    
    /* *************************** Dialog message and titles ************************/
    public static final String DIALOG_TEXT_CONFIRM_EXIT = "dialog.text.confirm.exit";
    public static final String DIALOG_TITLE_CONFIRM     = "dialog.title.confirm";
    public static final String DIALOG_TEXT_NOT_SUPPORTED_EXIT = "dialog.text.not.supported.exit";
}
