package com.windthunderstudio.scmanager.gui.elements;

import javax.swing.JMenu;
import javax.swing.SwingConstants;

import com.windthunderstudio.scmanager.gui.controller.FontManager;
import com.windthunderstudio.scmanager.gui.controller.LocaleChangeController;
import com.windthunderstudio.scmanager.gui.controller.LocaleListenerForFont;
import com.windthunderstudio.scmanager.util.CTS;


public class PlainMenu extends JMenu implements LocaleChangeController {
    private String textKey;
    
    @Override
    public String getTextKey() {
        return textKey;
    }
    
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }


    public PlainMenu() {
        super();
        this.setFont(FontManager.ARIAL_PLAIN);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.addPropertyChangeListener(CTS.PROPERTY_NAME_TEXT, new LocaleListenerForFont());
    }
    
    public PlainMenu(String text) {
        this();
        this.setText(text);
    }
}
