package com.windthunderstudio.scmanager.gui.elements;

import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import com.windthunderstudio.scmanager.gui.FontManager;
import com.windthunderstudio.scmanager.gui.LocaleChangeController;
import com.windthunderstudio.scmanager.gui.LocaleListenerForFont;
import com.windthunderstudio.scmanager.util.CTS;

public class PlainMenuItem extends JMenuItem implements LocaleChangeController {
    private String textKey;
    
    public void setTextKey(String key) {
        this.textKey = key;
    }
    
    @Override
    public String getTextKey() {
        return textKey;
    }
    
    
    
    public PlainMenuItem() {
        super();
        this.setFont(FontManager.ARIAL_PLAIN);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.addPropertyChangeListener(CTS.PROPERTY_NAME_TEXT, new LocaleListenerForFont());
    }
    
    public PlainMenuItem(String text) {
        this();
        this.setText(text);
        
    }
    
}
