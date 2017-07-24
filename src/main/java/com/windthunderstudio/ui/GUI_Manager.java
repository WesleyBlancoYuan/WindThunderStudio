package com.windthunderstudio.ui;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.ConfigReader;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.ui.elements.menu.PopupMenuForAlarm;
import com.windthunderstudio.ui.elements.menuitem.PlainMenuItem;

public class GUI_Manager {
    
    private static final Logger log = LogManager.getLogger(GUI_Manager.class);
    private ImageIcon icon;
    private TrayIcon trayIcon;
    private Properties localeProp = I18N_Manager.loadLocale();
    private static SystemTray tray;
    private PopupMenuForAlarm pop;
    public Font WQ_PLAIN_14;
    public Font WQ_BOLD_14;
    
    public static SystemTray getSystemTray() {
        return tray;
    }
    
    
    public GUI_Manager() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            log.error("Nimbus Look&Feel not found: ", e);
        }
        createGUI();
    }
    
    private void setFontByLocale() {
        if (!I18N_Manager.isChinese) {
            UIManager.put("OptionPane.messageFont", CTS.ARIAL_PLAIN_12);
            UIManager.put("OptionPane.buttonFont", CTS.ARIAL_PLAIN_12);
            UIManager.put("OptionPane.font", CTS.ARIAL_PLAIN_12);
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
        pop = new PopupMenuForAlarm();
        pop.setLabel("My pop");
        
        PlainMenuItem exitMenuItem = new PlainMenuItem();
        exitMenuItem.setLabel(localeProp.getProperty(CTS.MENUITEM_LBL_TEXT_EXIT));
        exitMenuItem.setFont(CTS.ARIAL_PLAIN_12);
        exitMenuItem.setShortcut(new MenuShortcut(KeyEvent.VK_X));
        exitMenuItem.addActionListener(new ActionListener() {
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
                        options, options[0]);
                if (status == JOptionPane.OK_OPTION) {
                    tray.remove(trayIcon);
                    log.info("Exiting...");
                    System.exit(0);
                }
            }
        });
        List<MenuItem> items = new ArrayList<MenuItem>();
        if (I18N_Manager.isChinese) {
            configMenuitemsFont(items);
        }
        items.add(exitMenuItem);
        pop.fillMenu(items);
        return pop;
    }
    private void createGUI() {
        if (SystemTray.isSupported()) {
            log.info("System Tray is supported.");
            tray = SystemTray.getSystemTray();
            // construct popmenu
            pop = createPopupMenu();
            // create tray trayIcon
            File iconFile = new File(CTS.ICON_ALARM_PATH);
            if (!iconFile.exists()) {
                log.error("System tray trayIcon not found in the path: " + CTS.ICON_ALARM_PATH);
                return;
            } else {
                icon = new ImageIcon(CTS.ICON_ALARM_PATH);
                trayIcon = new TrayIcon(icon.getImage());
                trayIcon.setImageAutoSize(true);
                trayIcon.setPopupMenu((PopupMenu)pop);
                try {
                    tray.add(trayIcon);
                } catch (AWTException e) {
                    log.error("Cannot add trayIcon to system tray. ", e);
                }
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
    
    private void configMenuitemsFont(List<MenuItem> items) {
        for (MenuItem i: items) {
            i.setFont(WQ_PLAIN_14);
        }
    }
}
