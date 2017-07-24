package com.windthunderstudio.logic.util;

import java.awt.Font;
import java.io.File;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Map;

public class CTS {
    /* *************************** System values ************************************/
    public static final String ls = System.lineSeparator();
    public static final String fsp = File.separator;
    
    /* *************************** Pan-app values ***********************************/
    public static final String APP_NAME = "app.name";
    
    /* *************************** UI resources *************************************/
    public static final Font ARIAL_PLAIN_12 = new Font("Arial", Font.PLAIN, 12);
    public static final Font ARIAL_BOLD_12 = new Font("Arial", Font.BOLD, 12);
    public static final String FONT_FAMILY_NAME_WENQUAN = "WenQuanYi Micro Hei Mono";
    
    
    /* *************************** Config value *************************************/
    public static final String CONFIG_PROP_PATH = "src/main/resources/settings.properties"; //relative
    public static final String CONFIG_KEY_LOCALE = "locale";
    
    /* *************************** Locale values ************************************/
    public static final String LOCALE_PATH_PREFIX = "src/main/resources/_locales/";
    public static final String LOCALE_PATH_SUFFIX = "locale";
    public static final String LOCALE_STR_ES = "es";
    public static final String LOCALE_STR_EN = "en";
    public static final String LOCALE_STR_ZH_HANS = "zh_hans";
    
    /* *************************** Menu label text***********************************/
    public static final String MENUITEM_LBL_TEXT_EXIT = "menuitem.lbl.text.exit";
    
    public static final String FONT_PATH_WENQUAN = "src/main/resources/fonts/wenq.micro.hei.mono.ttf";
    /* *************************** Dialog message and titles ************************/
    public static final String DIALOG_OPTION_YES =      "dialog.option.yes";
    public static final String DIALOG_OPTION_NO =       "dialog.option.no";
    public static final String DIALOG_OPTION_CONFIRM =  "dialog.option.confirm";
    public static final String DIALOG_OPTION_CANCEL =   "dialog.option.cancel";
    
    public static final String DIALOG_TEXT_CONFIRM_EXIT = "dialog.text.confirm.exit";
    public static final String DIALOG_TITLE_CONFIRM     = "dialog.title.confirm";
    public static final String DIALOG_TEXT_NOT_SUPPORTED_EXIT = "dialog.text.not.supported.exit";
    // a classloader path: differ according to class package
    public static final String ICON_ALARM_PATH = "src/main/resources/icons/alarm.png";
    
    
}
