package com.windthunderstudio.scmanager.gui.elements;

import javax.swing.JButton;

import com.windthunderstudio.scmanager.gui.FontManager;
import com.windthunderstudio.scmanager.gui.LocaleChangeController;

public class PlainButton extends JButton implements LocaleChangeController{
    private String textKey;
    
    @Override
    public String getTextKey() {
        // TODO Auto-generated method stub
        return textKey;
    }

    public PlainButton() {
        super();
        setFont(FontManager.ARIAL_PLAIN);
    }
}
