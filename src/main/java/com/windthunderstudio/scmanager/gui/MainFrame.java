package com.windthunderstudio.scmanager.gui;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.gui.elements.LayerPanelWithSection;
import com.windthunderstudio.scmanager.gui.elements.PlainButton;
import com.windthunderstudio.scmanager.gui.elements.PlainMenu;
import com.windthunderstudio.scmanager.gui.elements.PlainMenuItem;
import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.ConfigReader;
import com.windthunderstudio.scmanager.util.I18N_Manager;
import com.windthunderstudio.scmanager.util.ReflectionUIHandler;
import com.windthunderstudio.scmanager.gui.FontManager;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
    private static final Logger log = LogManager.getLogger(MainFrame.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    //pricipal UI elements
    private JMenuBar menuBar;
    private JPanel content;
    private JPanel bot;
    
    /* menu */
    //menu lang
    private PlainMenu lang;
    private PlainMenuItem langES;
    private PlainMenuItem langEN;
    private PlainMenuItem langCN;
    
    //menuitem about
    private PlainMenuItem about;
    
    /* buttons */
    private PlainButton back;
    private PlainButton next;
    private PlainButton process;
    private PlainButton cancel;
    
    /* For reflection */ 
    private List<JComponent> allComponents;
    
    public MainFrame() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            FontManager fontManager = new FontManager();
            fontManager.registerFont();
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Nimbus Look&Feel not supported: ", e);
        }
        createGUI();
    }
    
    private void createGUI() {
        setLayout(new MigLayout("insets 10, fill, debug", "", "[]5[]5[]"));
        menuBar = new JMenuBar();
//        add(menuBar, "cell 0 0, grow");
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
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
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
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
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
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, I18N_Manager.loadLocale(), "TextKey", "Text");
                        // after changing text, the font will be changed by the propertyChangeListener.
                    }
                }
            }
        });
        lang.add(langCN);
        
        menuBar.add(lang);
        
        about = new PlainMenuItem();
        about.setText(localeProp.getProperty(CTS.TEXT_MENUITEM_ABOUT));
        about.setTextKey(CTS.TEXT_MENUITEM_ABOUT);
        about.setMnemonic(KeyEvent.VK_A);
        menuBar.add(about);
        
        
        /* Content panel */
        content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new CardLayout());
        
        LayerPanelWithSection wsLayer = new LayerPanelWithSection(localeProp.getProperty(CTS.TEXT_LAYER_DESC_HTML_WS));
        
        /* Bot panel for back, next, process and cancel button. */
        bot = new JPanel();
        bot.setOpaque(false);
        bot.setLayout(new MigLayout("insets 5, fill, debug", "push[]5[]5[]5[]", ""));
        
        back = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_BACK));
        back.setTextKey(CTS.TEXT_BTN_BACK);
        back.setMnemonic(KeyEvent.VK_B);
        bot.add(back, "cell 0 0, w 100!");
        
        next = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_NEXT));
        next.setMnemonic(KeyEvent.VK_N);
        bot.add(next, "cell 1 0, w 100!");
        
        process = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_PROCESS));
        process.setMnemonic(KeyEvent.VK_P);
        bot.add(process, "cell 2 0, w 100!");
        
        cancel = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_CANCEL));
        cancel.setMnemonic(KeyEvent.VK_C);
        bot.add(cancel, "cell 3 0, w 100!");
        
        
        
    }

    private JPanel getContent() {
        return content;
    }

    private PlainMenu getLang() {
        return lang;
    }

    private PlainMenuItem getLangES() {
        return langES;
    }

    private PlainMenuItem getLangEN() {
        return langEN;
    }

    private PlainMenuItem getLangCN() {
        return langCN;
    }

    private PlainMenuItem getAbout() {
        return about;
    }

    private PlainButton getBack() {
        return back;
    }

    private PlainButton getNext() {
        return next;
    }

    private PlainButton getProcess() {
        return process;
    }

    private PlainButton getCancel() {
        return cancel;
    }
    
    
}
