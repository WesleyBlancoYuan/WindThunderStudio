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
    public static final String PROPERTY_NAME_TEXT = "text"; //for propertyListener
    
    /* *************************** UI resources *************************************/
    public static final String FONT_FAMILY_NAME_WENQUAN = "WenQuanYi Micro Hei Mono";
    public static final String FONT_PATH_WENQUAN = "src/main/resources/fonts/wenq.micro.hei.mono.ttf";
    public static final String ICON_ALARM_PATH = "src/main/resources/icons/alarm.gif";
    public static final String ICON_CREATE_PATH = "src/main/resources/icons/create.png";
    public static final String ICON_EDIT_PATH = "src/main/resources/icons/edit.png";
    public static final String ICON_DELETE_PATH = "src/main/resources/icons/delete.png";
    
    /* *************************** Config value *************************************/
    public static final String CONFIG_PROP_PATH = "src/main/resources/settings.ini"; //relative
    public static final String CONFIG_KEY_LOCALE = "locale";
    
    /* *************************** Locale values ************************************/
    public static final String LOCALE_PATH_PREFIX = "src/main/resources/_locales/";
    public static final String LOCALE_PATH_SUFFIX = "locale";
    public static final String LOCALE_STR_ES = "es";
    public static final String LOCALE_STR_EN = "en";
    public static final String LOCALE_STR_ZH_HANS = "zh_hans";
    public static final String LOCALE_LANG_ES = "locale.lang.es";
    public static final String LOCALE_LANG_EN = "locale.lang.en";
    public static final String LOCALE_LANG_ZH_HANS = "locale.lang.zh_hans";
    
    /* *************************** Dialog message and titles ************************/
    public static final String DIALOG_OPTION_YES =      "dialog.option.yes";
    public static final String DIALOG_OPTION_NO =       "dialog.option.no";
    public static final String DIALOG_OPTION_CONFIRM =  "dialog.option.confirm";
    public static final String DIALOG_OPTION_CANCEL =   "dialog.option.cancel";
    
    public static final String DIALOG_TEXT_CONFIRM_EXIT = "dialog.text.confirm.exit";
    public static final String DIALOG_TITLE_CONFIRM     = "dialog.title.confirm";
    public static final String DIALOG_TEXT_NOT_SUPPORTED_EXIT = "dialog.text.not.supported.exit";
    
    public static final String DIALOG_TEXT_MANAGE_ALARM = "dialog.text.manage.alarm";
    public static final String DIALOG_TITLE_MANAGE_ALARM = "dialog.title.manage.alarm";
    
    public static final String DIALOG_TITLE_CREATE_ALARM = "dialog.title.create.alarm";
    /* *************************** Menu label/button/menu text ***************************/
    public static final String TEXT_MENUITEM_EXIT = "text.menuitem.exit";
    public static final String TEXT_LBL_ACTION = "text.lbl.action";
    public static final String TEXT_LBL_CONFIG = "text.lbl.config";
    public static final String TEXT_MENU_LANG = "text.menu.lang";
    public static final String TEXT_MENU_TASK = "text.menu.task";
    
    /* *************************** Table *************************************************/
    public static final String TABLE_COLNAME_TASK_NAME = "table.colname.task.name";
    public static final String TABLE_COLNAME_TASK_DESC = "table.colname.task.desc";
    
}
