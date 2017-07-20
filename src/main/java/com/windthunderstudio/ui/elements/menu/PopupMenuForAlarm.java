package com.windthunderstudio.ui.elements.menu;

import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.ui.elements.menuitem.PlainMenuItem;

public class PopupMenuForAlarm extends PopupMenu {
    
    private Properties localeProp = I18N_Manager.loadLocale();
    
    public PopupMenuForAlarm() {
        super();
        this.setFont(CTS.ARIAL_PLAIN_12);
    }
    
    public void fillMenu(ArrayList<MenuItem> items) {
        if (items != null) {
            for (MenuItem item: items) {
                if (item != null) {
                    this.add(item);
                }
            }
            
        }
    }
    
    public void constructBody() {
        PlainMenuItem exitMenuItem = new PlainMenuItem(localeProp.getProperty(CTS.MENUITEM_LBL_TEXT_EXIT));
        exitMenuItem.setShortcut(new MenuShortcut(KeyEvent.VK_X));
        exitMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(null, 
                        localeProp.getProperty(CTS.DIALOG_TEXT_CONFIRM_EXIT), 
                        localeProp.getProperty(CTS.DIALOG_TITLE_CONFIRM), 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (status == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        add(exitMenuItem);
        
    }
}
