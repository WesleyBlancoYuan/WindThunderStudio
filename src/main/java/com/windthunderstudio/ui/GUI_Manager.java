package com.windthunderstudio.ui;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.ConfigReader;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.logic.util.ReflectionUIHandler;
import com.windthunderstudio.ui.elements.menu.PlainMenu;
import com.windthunderstudio.ui.elements.menu.PopupMenuForAlarm;
import com.windthunderstudio.ui.elements.menuitem.BoldLabel;
import com.windthunderstudio.ui.elements.menuitem.PlainMenuItem;

public class GUI_Manager {
    
    private static final Logger log = LogManager.getLogger(GUI_Manager.class);
    private ImageIcon icon;
    private TrayIcon trayIcon;
    private Properties localeProp = I18N_Manager.loadLocale();
    private static SystemTray tray;
    private PopupMenuForAlarm jpop;
    public Font WQ_PLAIN_14;
    public Font WQ_BOLD_14;
    
    /* UI elements */
    private BoldLabel actions;
    private BoldLabel config;
    private PlainMenu lang;
    private PlainMenuItem langES;
    private PlainMenuItem langEN;
    private PlainMenuItem langCN;
    private PlainMenuItem exit;
    
    /* For reflection */ 
    private List<JComponent> allComponents;
    
    public static SystemTray getSystemTray() {
        return tray;
    }
    
    
    public GUI_Manager() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Metal Look&Feel not supported: ", e);
        }
        createGUI();
    }
    
    private void setFontByLocale() {
        if (!I18N_Manager.isChinese) {
            UIManager.put("OptionPane.messageFont", CTS.ARIAL_PLAIN_14);
            UIManager.put("OptionPane.buttonFont", CTS.ARIAL_PLAIN_14);
            UIManager.put("OptionPane.font", CTS.ARIAL_PLAIN_14);
        } else {
            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Font wenquanPlain = Font.createFont(Font.TRUETYPE_FONT, new File(CTS.FONT_PATH_WENQUAN));
                //only after registering the font family name will be available.
                ge.registerFont(wenquanPlain);
                //must assign font with new, if is JOptionPane. If is JLabel, can just use 
                //with deriveFont()
                WQ_BOLD_14 = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.BOLD, 14);
                WQ_PLAIN_14 = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.PLAIN, 14);
                UIManager.put("OptionPane.messageFont", WQ_PLAIN_14);
                UIManager.put("OptionPane.buttonFont", WQ_PLAIN_14);
                UIManager.put("OptionPane.font", WQ_PLAIN_14);
            } catch (IOException e) {
                log.error("Cannot locate Font file: ", e);
            } catch (FontFormatException e1) {
                log.error("Cannot format Font: ", e1);
            }
        }
    }
    private PopupMenuForAlarm createPopupMenu() {
        setFontByLocale();
        jpop = new PopupMenuForAlarm();
        
        /* Action part: common operations*/
        actions = new BoldLabel();
        actions.setText(localeProp.getProperty(CTS.TEXT_LBL_ACTION));
        actions.setTextKey(CTS.TEXT_LBL_ACTION);
        jpop.add(actions);
        
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
        langES.setText(CTS.LOCALE_LANG_ES);
        langES.setMnemonic(KeyEvent.VK_E);
        langES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_ES);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, "Text", "TextKey");
                    }
                }
            }
        });
        lang.add(langES);
        
        /* Config - Lang - EN */
        langEN = new PlainMenuItem();
        langEN.setText(CTS.LOCALE_LANG_EN);
        langEN.setMnemonic(KeyEvent.VK_G);
        langEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_EN);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, "Text", "TextKey");
                    }
                }
            }
        });
        lang.add(langEN);
        
        /* Config - Lang - CN */
        langCN = new PlainMenuItem();
        langCN.setText(CTS.LOCALE_LANG_EN);
        
        langCN.setMnemonic(KeyEvent.VK_G);
        langCN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigReader.config(CTS.CONFIG_KEY_LOCALE, CTS.LOCALE_STR_ZH_HANS);
                ConfigReader.reloadConfig();
                I18N_Manager.reloadLocale();
                if (allComponents != null && !allComponents.isEmpty()) {
                    for (JComponent c: allComponents) {
                        ReflectionUIHandler.getAndSetProperty(c, "Text", "TextKey");
                    }
                }
            }
        });
        lang.add(langCN);
        
        
        jpop.add(lang);
        
        jpop.addSeparator();
        /* Exit */
        exit = new PlainMenuItem();
        exit.setText(localeProp.getProperty(CTS.TEXT_MENUITEM_EXIT));
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
        
        jpop.addSeparator();
        jpop.add(exit);
        
        // add all constructed components for config
        allComponents = new ArrayList<JComponent>();
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(BoldLabel.class.getName(), this));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenuItem.class.getName(), this));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenu.class.getName(), this));
        
        configComponentsFont(allComponents);
        
        
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
    
    private void configComponentsFont(List<JComponent> components) {
        for (JComponent i: components) {
            if (I18N_Manager.isChinese) {
                if (i instanceof JLabel) {
                    i.setFont(WQ_BOLD_14);
                } else {
                    i.setFont(WQ_PLAIN_14);
                }
            }
            // if the locale is not Chinese, font is set in super class.
        }
    }
}
