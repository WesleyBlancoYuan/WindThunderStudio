package com.windthunderstudio.ui.elements.menuitem;

import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import com.windthunderstudio.logic.util.CTS;

public class PlainMenuItem extends JMenuItem{
    public PlainMenuItem() {
        super();
        this.setFont(CTS.ARIAL_PLAIN_14);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }
    
    public PlainMenuItem(String text) {
        this();
        this.setText(text);
        
    }
    
}
