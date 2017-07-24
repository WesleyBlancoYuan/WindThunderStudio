package com.windthunderstudio.ui.elements.menuitem;

import java.awt.MenuItem;

import com.windthunderstudio.logic.util.CTS;

public class BoldMenuItem extends MenuItem{
    public BoldMenuItem() {
        super();
        this.setFont(CTS.ARIAL_BOLD_12);
    }
    
    public BoldMenuItem(String text) {
        this();
        this.setLabel(text);
        
    }
}
