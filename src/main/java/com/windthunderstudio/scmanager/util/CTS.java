package com.windthunderstudio.scmanager.util;

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
    public static final String PREF_METADATA_ROOT = ".metadata";
    public static final String PREF_RUNTIME_ROOT = ".metadata" + fsp + ".plugins" + fsp + "org.eclipse.core.runtime";
    public static final String PREF_GLOB_FILTER = "*.pref"; 
    /* *************************** UI resources *************************************/
    public static final String FONT_FAMILY_NAME_WENQUAN = "WenQuanYi Micro Hei Mono";
    public static final String FONT_PATH_WENQUAN = "src/main/resources/fonts/wenq.micro.hei.mono.ttf";
    public static final String ICON_ALARM_PATH = "src/main/resources/icons/alarm.gif";
    
    /* *************************** Config value *************************************/
    public static final String CONFIG_PROP_PATH = "src/main/resources/settings.properties"; //relative
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
    
    /* *************************** UI elements text ***************************/
    public static final String TEXT_MENU_LANG = "text.menu.lang";
    public static final String TEXT_BTN_BACK = "text.btn.back";
    public static final String TEXT_BTN_NEXT = "text.btn.next";
    public static final String TEXT_BTN_PROCESS = "text.btn.process";
    public static final String TEXT_BTN_CANCEL = "text.btn.cancel";
    public static final String TEXT_MENUITEM_ABOUT = "text.menuitem.about";
    public static final String TEXT_LBL_OLD_WS_HTML = "text.lbl.old.ws.html";
    public static final String TEXT_LBL_NEW_WS_HTML = "text.lbl.new.ws.html";
    public static final String TEXT_BTN_CHOOSE = "text.btn.choose";
    public static final String TEXT_LBL_OLD_TREE = "text.lbl.old.tree";
    public static final String TEXT_LBL_NEW_TREE = "text.lbl.new.tree";
    
    /* *************************** Layer **************************************/
    public static final String TEXT_LAYER_DESC_WS_HTML = "text.layer.desc.ws.html";
    public static final String TEXT_LAYER_DESC_FILETREE_HTML = "text.layer.desc.filetree.html";
    
    public static final String LAYER_NAME_WS = "layer.ws";
    public static final String LAYER_NAME_FT = "layer.ft";
    
    
    /* *************************** Tree ***************************************/
    public static final String TEXT_TREE_NODE_OLD_ROOT = "text.tree.node.old.root";
    
    /* *************************** Dialog message and titles ************************/
    public static final String DIALOG_OPTION_YES =      "dialog.option.yes";
    public static final String DIALOG_OPTION_NO =       "dialog.option.no";
    public static final String DIALOG_OPTION_CONFIRM =  "dialog.option.confirm";
    public static final String DIALOG_OPTION_CANCEL =   "dialog.option.cancel";
    public static final String DIALOG_OPTION_ACCEPT =   "dialog.option.accept";
    
    public static final String DIALOG_TEXT_CONFIRM_EXIT = "dialog.text.confirm.exit";
    public static final String DIALOG_TITLE_CONFIRM     = "dialog.title.confirm";
    public static final String DIALOG_TEXT_NOT_SUPPORTED_EXIT = "dialog.text.not.supported.exit";
    public static final String DIALOG_TITLE_CHOOSE_WS = "dialog.title.choose.ws";
    // a classloader path: differ according to class package
    
    
}
