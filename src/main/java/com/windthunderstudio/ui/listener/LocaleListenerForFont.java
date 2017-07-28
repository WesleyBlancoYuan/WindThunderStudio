package com.windthunderstudio.ui.listener;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.ui.controller.FontManager;

public class LocaleListenerForFont implements PropertyChangeListener{
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equalsIgnoreCase((String)CTS.PROPERTY_NAME_TEXT)) {
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
}
