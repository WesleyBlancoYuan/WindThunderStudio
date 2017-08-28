package com.windthunderstudio.ui.elements;

import javax.swing.JLayeredPane;

import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.controller.LocaleChangeController;

public class SimpleLayer extends JLayeredPane implements LocaleChangeController {

    private String textKey;
    
    @Override
    public String getTextKey() {
        return textKey;
    }
    
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }
    
    public SimpleLayer() {
        super();
        this.setFont(I18N_Manager.isChinese ? FontManager.WQ_PLAIN : FontManager.ARIAL_PLAIN);
    }
    
}
