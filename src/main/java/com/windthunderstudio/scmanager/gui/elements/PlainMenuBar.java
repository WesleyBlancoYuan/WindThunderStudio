package com.windthunderstudio.scmanager.gui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

import com.windthunderstudio.scmanager.gui.MainFrame;
import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.ConfigReader;
import com.windthunderstudio.scmanager.util.I18N_Manager;
import com.windthunderstudio.scmanager.util.ReflectionUIHandler;

public class PlainMenuBar extends JMenuBar {
    private Properties localeProp = I18N_Manager.loadLocale();
    //menu lang
    private PlainMenu lang;
    private PlainMenuItem langES;
    private PlainMenuItem langEN;
    private PlainMenuItem langCN;
    //menuitem about
    private PlainMenuItem about;
    
    public PlainMenuBar() {
        super();
        createMenuBar();
    }
    
    
    private void createMenuBar() {
        lang = new PlainMenu();
        lang.setText(localeProp.getProperty(CTS.TEXT_MENU_LANG));
        lang.setTextKey(CTS.TEXT_MENU_LANG);
        lang.setMnemonic(KeyEvent.VK_L);
        
        /* Lang - ES */
        langES = new PlainMenuItem();
        langES.setText(localeProp.getProperty(CTS.LOCALE_LANG_ES));
        langES.setTextKey(CTS.LOCALE_LANG_ES);
        langES.setMnemonic(KeyEvent.VK_S); //for showing it in English and Spanish menu
        langES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N_Manager.isChinese = false;
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_ES);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                localeProp = I18N_Manager.loadLocale(); //load prop in this class
                if (MainFrame.allComponents != null && !MainFrame.allComponents.isEmpty()) {
                    for (JComponent c: MainFrame.allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, I18N_Manager.loadLocale(), "TextKey", "Text");
                        // after changing text, the font will be changed by the propertyChangeListener.
                    }
                }
            }
        });
        lang.add(langES);
        
        /* Lang - EN */
        langEN = new PlainMenuItem();
        langEN.setText(localeProp.getProperty(CTS.LOCALE_LANG_EN));
        langEN.setTextKey(CTS.LOCALE_LANG_EN);
        langEN.setMnemonic(KeyEvent.VK_G);
        langEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N_Manager.isChinese = false;
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_EN);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                localeProp = I18N_Manager.loadLocale(); //load prop in this class
                if (MainFrame.allComponents != null && !MainFrame.allComponents.isEmpty()) {
                    for (JComponent c: MainFrame.allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, I18N_Manager.loadLocale(), "TextKey", "Text");
                        // after changing text, the font will be changed by the propertyChangeListener.
                    }
                }
            }
        });
        lang.add(langEN);
        
        /* Lang - CN */
        langCN = new PlainMenuItem();
        langCN.setText(localeProp.getProperty(CTS.LOCALE_LANG_ZH_HANS));
        langCN.setTextKey(CTS.LOCALE_LANG_ZH_HANS);
        langCN.setMnemonic(KeyEvent.VK_C);
        langCN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N_Manager.isChinese = true;
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_ZH_HANS);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                localeProp = I18N_Manager.loadLocale(); //load prop in this class
                if (MainFrame.allComponents != null && !MainFrame.allComponents.isEmpty()) {
                    for (JComponent c: MainFrame.allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, I18N_Manager.loadLocale(), "TextKey", "Text");
                        // after changing text, the font will be changed by the propertyChangeListener.
                    }
                }
            }
        });
        lang.add(langCN);
        
        add(lang);
        
        about = new PlainMenuItem();
        about.setText(localeProp.getProperty(CTS.TEXT_MENUITEM_ABOUT));
        about.setTextKey(CTS.TEXT_MENUITEM_ABOUT);
        about.setMnemonic(KeyEvent.VK_A);
        add(about);
        
    }
    
    public PlainMenu getLang() {
        return lang;
    }

    public PlainMenuItem getLangES() {
        return langES;
    }

    public PlainMenuItem getLangEN() {
        return langEN;
    }

    public PlainMenuItem getLangCN() {
        return langCN;
    }

    public PlainMenuItem getAbout() {
        return about;
    }


    
}
