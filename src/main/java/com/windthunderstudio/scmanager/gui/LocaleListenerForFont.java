package com.windthunderstudio.scmanager.gui;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;

import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.I18N_Manager;

public class LocaleListenerForFont implements PropertyChangeListener{
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        JComponent c = (JComponent) (e.getSource());
        if (I18N_Manager.isChinese) {
            if (c.getFont().getStyle() == Font.BOLD) {
                c.setFont(FontManager.WQ_BOLD);
            } else {
                c.setFont(FontManager.WQ_PLAIN);
            }
        } else {
            if (c.getFont().getStyle() == Font.BOLD) {
                c.setFont(FontManager.ARIAL_BOLD);
            } else {
                c.setFont(FontManager.ARIAL_PLAIN);
            }
        }
    }
}
