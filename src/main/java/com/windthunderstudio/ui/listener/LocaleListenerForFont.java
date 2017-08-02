package com.windthunderstudio.ui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.ui.controller.FontManager;

public class LocaleListenerForFont implements PropertyChangeListener{
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equalsIgnoreCase((String)CTS.PROPERTY_NAME_TEXT)) {
            JComponent c = (JComponent) (e.getSource());
            FontManager.changeFontWithStyle(c);
        }
    }
}
