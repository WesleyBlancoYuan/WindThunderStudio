package com.windthunderstudio.scmanager.gui.elements;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.windthunderstudio.scmanager.gui.controller.FontManager;
import com.windthunderstudio.scmanager.gui.controller.LocaleChangeController;
import com.windthunderstudio.scmanager.gui.controller.LocaleListenerForFont;
import com.windthunderstudio.scmanager.util.CTS;

public class TitleLabel extends JLabel implements LocaleChangeController {
    private String textKey;
    
    @Override
    public String getTextKey() {
        return textKey;
    }
    
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }
    
    public TitleLabel() {
        super();
        setFont(FontManager.ARIAL_BOLD_BIG);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setVerticalAlignment(SwingConstants.CENTER);
        addPropertyChangeListener(CTS.PROPERTY_NAME_TEXT, new LocaleListenerForFont());
    }
    
    public TitleLabel(String text) {
        this();
        setText(text);
    }

}
