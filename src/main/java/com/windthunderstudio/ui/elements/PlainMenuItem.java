package com.windthunderstudio.ui.elements;

import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.controller.LocaleChangeController;
import com.windthunderstudio.ui.listener.LocaleListenerForFont;

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
        this.addPropertyChangeListener(new LocaleListenerForFont());
    }
    
    public PlainMenuItem(String text) {
        this();
        this.setText(text);
        
    }
    
}
