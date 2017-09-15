package com.windthunderstudio.ui;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jcrontab.Crontab;

import com.alee.laf.WebLookAndFeel;
import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.ConfigReader;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.logic.util.ReflectionUIHandler;
import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.elements.BoldLabel;
import com.windthunderstudio.ui.elements.PlainButton;
import com.windthunderstudio.ui.elements.PlainLabel;
import com.windthunderstudio.ui.elements.PlainMenu;
import com.windthunderstudio.ui.elements.PlainMenuItem;
import com.windthunderstudio.ui.elements.PopupMenuForAlarm;
import com.windthunderstudio.ui.elements.SimpleLayer;


public class GUI_Manager {
    
    private static final Logger log = LogManager.getLogger(GUI_Manager.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    private ImageIcon icon;
    private TrayIcon trayIcon;
    private static SystemTray tray;
    private PopupMenuForAlarm jpop;
    public Font WQ_PLAIN_14;
    public Font WQ_BOLD_14;
    
    /* UI elements */
    private BoldLabel actions;
    private PlainMenuItem task;
    private BoldLabel config;
    private PlainMenu lang;
    private PlainMenuItem langES;
    private PlainMenuItem langEN;
    private PlainMenuItem langCN;
    private PlainMenuItem exit;
    private Crontab ct;
    
    /* tasks management dialog */
    private TasksManagement tasks;
    
    /* For reflection */ 
    private List<JComponent> allComponents;
    
    public static SystemTray getSystemTray() {
        return tray;
    }

    public GUI_Manager() {
        try {
//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            UIManager.setLookAndFeel(new WebLookAndFeel());
            FontManager.registerFont();
            ct = Crontab.getInstance();
            ct.init(ConfigReader.loadConfig());
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Nimbus Look&Feel not supported: ", e);
        } catch (Exception e) {
            log.error("Error initializing task scheduler: ", e);
        }
        createGUI();
    }
    

    private PopupMenuForAlarm createPopupMenu() {
        
        jpop = new PopupMenuForAlarm();
        
        /* Action part: common operations */
        actions = new BoldLabel();
        actions.setText(localeProp.getProperty(CTS.TEXT_LBL_ACTION));
        actions.setTextKey(CTS.TEXT_LBL_ACTION);
        jpop.add(actions);
        
        /* create dialog and hide, for font config. Must not be null when added to allComponents */
        tasks = new TasksManagement();
        
        task = new PlainMenuItem(localeProp.getProperty(CTS.TEXT_MENU_TASK));
        task.setTextKey(CTS.TEXT_MENU_TASK);
        task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasks.setVisible(true);
            }
        });
        
        jpop.add(task);
        
        jpop.addSeparator();
        /* Config part */
        config = new BoldLabel();
        config.setText(localeProp.getProperty(CTS.TEXT_LBL_CONFIG));
        config.setTextKey(CTS.TEXT_LBL_CONFIG);
        jpop.add(config);
        
        /* Config - Lang */
        lang = new PlainMenu(localeProp.getProperty(CTS.TEXT_MENU_LANG));
        lang.setTextKey(CTS.TEXT_MENU_LANG);
        lang.setMnemonic(KeyEvent.VK_L);
        
        /* Config - Lang - ES */
        langES = new PlainMenuItem();
        langES.setText(localeProp.getProperty(CTS.LOCALE_LANG_ES));
        langES.setTextKey(CTS.LOCALE_LANG_ES);
        langES.setMnemonic(KeyEvent.VK_S); //for showing it in English and Spanish menu
        langES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N_Manager.isChinese = false;
                FontManager.changeFontWithLang();
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_ES);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                localeProp = I18N_Manager.loadLocale(); //load prop in this class
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        reconfigComponent(c);
                    }
                    jpop.revalidate();
                }
                FontManager.configDialogFont();
            }
        });
        lang.add(langES);
        
        /* Config - Lang - EN */
        langEN = new PlainMenuItem();
        langEN.setText(localeProp.getProperty(CTS.LOCALE_LANG_EN));
        langEN.setTextKey(CTS.LOCALE_LANG_EN);
        langEN.setMnemonic(KeyEvent.VK_G);
        langEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N_Manager.isChinese = false;
                FontManager.changeFontWithLang();
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_EN);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                localeProp = I18N_Manager.loadLocale(); //load prop in this class
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        reconfigComponent(c);
                    }
                    jpop.revalidate();
                }
                FontManager.configDialogFont();
            }
        });
        lang.add(langEN);
        
        /* Config - Lang - CN */
        langCN = new PlainMenuItem();
        langCN.setText(localeProp.getProperty(CTS.LOCALE_LANG_ZH_HANS));
        langCN.setTextKey(CTS.LOCALE_LANG_ZH_HANS);
        langCN.setMnemonic(KeyEvent.VK_C);
        langCN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N_Manager.isChinese = true;
                FontManager.changeFontWithLang();
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_ZH_HANS);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                localeProp = I18N_Manager.loadLocale(); //load prop in this class
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        reconfigComponent(c);
                    }
                    jpop.revalidate();
                    
                }
                FontManager.configDialogFont();
            }
        });
        lang.add(langCN);
        
        jpop.add(lang);
        jpop.addSeparator();
        
        
        /* Exit */
        exit = new PlainMenuItem();
        exit.setText(localeProp.getProperty(CTS.TEXT_MENUITEM_EXIT));
        exit.setTextKey(CTS.TEXT_MENUITEM_EXIT);
        exit.setMnemonic(KeyEvent.VK_X);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = new String[]{
                        localeProp.getProperty(CTS.DIALOG_OPTION_CONFIRM),
                        localeProp.getProperty(CTS.DIALOG_OPTION_CANCEL)};
                int status = JOptionPane.showOptionDialog(null, 
                        MessageFormat.format(localeProp.getProperty(CTS.DIALOG_TEXT_CONFIRM_EXIT), 
                                localeProp.getProperty(CTS.APP_NAME)), 
                        localeProp.getProperty(CTS.DIALOG_TITLE_CONFIRM), 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, //for default question mark
                        options, options[1]);
                if (status == JOptionPane.OK_OPTION) {
                    tray.remove(trayIcon);
                    log.info("Exiting...");
                    System.exit(0);
                }
            }
        });
        
        jpop.add(exit);
        
        // add all constructed components for config
        allComponents = new ArrayList<JComponent>();
        
        /* main pop menu */
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(BoldLabel.class.getName(), this));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenuItem.class.getName(), this));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenu.class.getName(), this));
        
        /* tasks dialog */
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(BoldLabel.class.getName(), tasks));
        
        /* create task dialog */
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(JTabbedPane.class.getName(), tasks.getCreateTask()));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainButton.class.getName(), tasks.getCreateTask()));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainLabel.class.getName(), tasks.getCreateTask()));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(BoldLabel.class.getName(), tasks.getCreateTask()));
        
        //first time change font with current locale
        for (JComponent c: allComponents) {
            reconfigComponent(c);
        }
        return jpop;
    }
    private void createGUI() {
        if (SystemTray.isSupported()) {
            log.info("System Tray is supported.");
            tray = SystemTray.getSystemTray();
            // construct popmenu
            jpop = createPopupMenu();
            // create tray trayIcon
            File iconFile = new File(CTS.ICON_ALARM_PATH);
            if (!iconFile.exists()) {
                log.error("System tray trayIcon not found in the path: " + CTS.ICON_ALARM_PATH);
                return;
            } else {
                icon = new ImageIcon(CTS.ICON_ALARM_PATH);
                trayIcon = new TrayIcon(icon.getImage());
                trayIcon.setImageAutoSize(true);
                trayIcon.addMouseListener(new MouseListener() {
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        maybeShowPopup(e);
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        maybeShowPopup(e);
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    
                    private void maybeShowPopup(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            jpop.setLocation(e.getX()-jpop.getWidth(), e.getY()-jpop.getHeight());
                            jpop.setInvoker(jpop);
                            jpop.setVisible(true);
                        }
                    }
                });
                try {
                    tray.add(trayIcon);
                } catch (AWTException e) {
                    log.error("Cannot add trayIcon to system tray. ", e);
                }
                log.info("Icon added to system tray. ");
            }
        } else {
            log.error("System Tray is not supported.");
            int status = JOptionPane.showConfirmDialog(null, 
                    localeProp.getProperty(CTS.DIALOG_TEXT_NOT_SUPPORTED_EXIT), 
                    localeProp.getProperty(CTS.DIALOG_TITLE_CONFIRM), 
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (status == JOptionPane.OK_OPTION) {
                log.info("Exiting for not supported...");
                System.exit(0);
            }
        }
    }
    
    private void reconfigComponent(JComponent c) {
        if (c instanceof JTabbedPane) {
            // 1. set tab title
            JTabbedPane tabs = ((JTabbedPane)c);
            tabs.setFont(FontManager.current);
            WebLookAndFeel.toolTipFont = FontManager.current;
            for (int i=0; i<tabs.getTabCount(); i++) {
                SimpleLayer layer = (SimpleLayer)tabs.getComponentAt(i);
                tabs.setTitleAt(i, localeProp.getProperty(layer.getTextKey()));
                tabs.setToolTipTextAt(i, localeProp.getProperty(layer.getTooltipTextKey()));
                layer.revalidate();
            }
            // 2. set tabbedpane font, for tab title
            tabs.setFont(FontManager.current);
            // 3. refresh text and font
            tabs.revalidate();
        } else {
            ReflectionUIHandler.getAndSetProperty(c, I18N_Manager.loadLocale(), "TextKey", "Text");
            // after changing text, the font will be changed by the propertyChangeListener.
        }
    }
    
    public PopupMenuForAlarm getJpop() {
        return jpop;
    }

    public void setJpop(PopupMenuForAlarm jpop) {
        this.jpop = jpop;
    }

    public BoldLabel getActions() {
        return actions;
    }

    public void setActions(BoldLabel actions) {
        this.actions = actions;
    }

    
    public PlainMenuItem getTask() {
        return task;
    }

    public BoldLabel getConfig() {
        return config;
    }

    public void setConfig(BoldLabel config) {
        this.config = config;
    }

    public PlainMenu getLang() {
        return lang;
    }

    public void setLang(PlainMenu lang) {
        this.lang = lang;
    }

    public PlainMenuItem getLangES() {
        return langES;
    }

    public void setLangES(PlainMenuItem langES) {
        this.langES = langES;
    }

    public PlainMenuItem getLangEN() {
        return langEN;
    }

    public void setLangEN(PlainMenuItem langEN) {
        this.langEN = langEN;
    }

    public PlainMenuItem getLangCN() {
        return langCN;
    }

    public void setLangCN(PlainMenuItem langCN) {
        this.langCN = langCN;
    }

    public PlainMenuItem getExit() {
        return exit;
    }

    public void setExit(PlainMenuItem exit) {
        this.exit = exit;
    }

    public TasksManagement getTasks() {
        return tasks;
    }

    
}
