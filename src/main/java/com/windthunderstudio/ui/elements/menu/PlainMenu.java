package com.windthunderstudio.ui.elements.menu;

import javax.swing.JMenu;

import com.windthunderstudio.logic.util.CTS;


public class PlainMenu extends JMenu {
    private String textKey;

    public String getTextKey() {
        return textKey;
    }
    
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }


    public PlainMenu() {
        super();
        this.setFont(CTS.ARIAL_BOLD_14);
    }
    
    public PlainMenu(String text) {
        this();
        this.setText(text);
    }
}
