package com.windthunderstudio.scmanager.gui.controller;

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
        FontManager.changeFontWithStyle(c);
    }
}
