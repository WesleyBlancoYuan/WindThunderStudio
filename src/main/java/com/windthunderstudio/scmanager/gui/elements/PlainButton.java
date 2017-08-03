package com.windthunderstudio.scmanager.gui.elements;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.windthunderstudio.scmanager.gui.controller.FontManager;
import com.windthunderstudio.scmanager.gui.controller.LocaleChangeController;
import com.windthunderstudio.scmanager.gui.controller.LocaleListenerForFont;
import com.windthunderstudio.scmanager.util.CTS;

public class PlainButton extends JButton implements LocaleChangeController{
    private String textKey;
    
    @Override
    public String getTextKey() {
        // TODO Auto-generated method stub
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }
    public PlainButton() {
        super();
        setFont(FontManager.ARIAL_PLAIN);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.addPropertyChangeListener(CTS.PROPERTY_NAME_TEXT, new LocaleListenerForFont());
    }
    public PlainButton(String text) {
        this();
        setText(text);
    }
}
