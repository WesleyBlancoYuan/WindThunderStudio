package com.windthunderstudio.ui.elements.menuitem;

import java.awt.MenuItem;

import com.windthunderstudio.logic.util.CTS;

public class PlainMenuItem extends MenuItem{
    public PlainMenuItem() {
        super();
        this.setFont(CTS.ARIAL_PLAIN_12);
    }
    
    public PlainMenuItem(String text) {
        this();
        this.setLabel(text);
        
    }
    
}
