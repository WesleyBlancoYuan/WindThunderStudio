package com.windthunderstudio.ui.elements;

import javax.swing.JMenu;

import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.controller.LocaleChangeController;
import com.windthunderstudio.ui.listener.LocaleListenerForFont;


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
        this.addPropertyChangeListener(new LocaleListenerForFont());
    }
    
    public PlainMenu(String text) {
        this();
        this.setText(text);
    }
}
