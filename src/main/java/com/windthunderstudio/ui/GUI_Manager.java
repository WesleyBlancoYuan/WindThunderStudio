package com.windthunderstudio.ui;

import java.awt.SystemTray;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;

public class GUI_Manager {
    
    private Properties localeProp = I18N_Manager.loadLocale();
    
    public GUI_Manager() {
        createGUI();
    }
    
    
    private void createGUI() {
        try {
            if (SystemTray.isSupported()) {
                System.out.println("System tray is supported.");
                SystemTray tray = SystemTray.getSystemTray();
            } else {
                System.out.println("System tray is not supported. ");
                int status = JOptionPane.showConfirmDialog(null, 
                        localeProp.getProperty(CTS.DIALOG_TEXT_NOT_SUPPORTED_EXIT), 
                        localeProp.getProperty(CTS.DIALOG_TITLE_CONFIRM), 
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (status == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        } catch (Exception e) {

        }
    }
}
