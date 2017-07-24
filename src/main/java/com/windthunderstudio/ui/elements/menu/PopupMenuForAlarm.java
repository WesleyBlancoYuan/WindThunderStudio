package com.windthunderstudio.ui.elements.menu;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.util.List;
import java.util.Properties;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;

public class PopupMenuForAlarm extends PopupMenu {
    
    private Properties localeProp = I18N_Manager.loadLocale();
    
    public PopupMenuForAlarm() {
        super();
        this.setFont(CTS.ARIAL_PLAIN_12);
    }
    
    public void fillMenu(List<MenuItem> items) {
        if (items != null) {
            for (MenuItem item: items) {
                if (item != null) {
                    this.add(item);
                }
            }
        }
    }
    
    public void constructBody() {
    }
}
