package com.windthunderstudio.ui.elements;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.controller.LocaleChangeController;
import com.windthunderstudio.ui.listener.LocaleListenerForFont;

public class PlainRadio extends JRadioButton implements LocaleChangeController{
    private String textKey;
    
    @Override
    public String getTextKey() {
        // TODO Auto-generated method stub
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }
    public PlainRadio() {
        super();
        setFont(FontManager.ARIAL_PLAIN);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.addPropertyChangeListener(CTS.PROPERTY_NAME_TEXT, new LocaleListenerForFont());
    }
    public PlainRadio(String text) {
        this();
        setText(text);
    }
}
