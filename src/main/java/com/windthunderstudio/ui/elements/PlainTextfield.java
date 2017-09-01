package com.windthunderstudio.ui.elements;

import javax.swing.JTextField;

import com.windthunderstudio.ui.controller.FontManager;

public class PlainTextfield extends JTextField {
    
    public PlainTextfield() {
        super();
        this.setFont(FontManager.ARIAL_PLAIN);
    }
    

}
