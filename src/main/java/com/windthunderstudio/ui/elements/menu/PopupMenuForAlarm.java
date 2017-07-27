package com.windthunderstudio.ui.elements.menu;

import java.util.List;
import java.util.Properties;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;

public class PopupMenuForAlarm extends JPopupMenu {
    
//    private Properties localeProp = I18N_Manager.loadLocale();
    
    public PopupMenuForAlarm() {
        super();
        this.setFont(CTS.ARIAL_PLAIN_14);
    }
    
    public void fillMenu(List<JMenuItem> items) {
        if (items != null) {
            for (JMenuItem item: items) {
                if (item != null) {
                    this.add(item);
                }
            }
        }
    }
}
