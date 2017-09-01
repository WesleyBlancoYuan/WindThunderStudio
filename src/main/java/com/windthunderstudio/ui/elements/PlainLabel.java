package com.windthunderstudio.ui.elements;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.controller.LocaleChangeController;
import com.windthunderstudio.ui.listener.LocaleListenerForFont;

public class PlainLabel extends JLabel implements LocaleChangeController {
    private String textKey;
    
    @Override
    public String getTextKey() {
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public PlainLabel() {
        super();
        this.setFont(FontManager.ARIAL_PLAIN);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.addPropertyChangeListener(new LocaleListenerForFont());
    }
    
    public PlainLabel(String text) {
        this();
        this.setText(text);
    }
}
