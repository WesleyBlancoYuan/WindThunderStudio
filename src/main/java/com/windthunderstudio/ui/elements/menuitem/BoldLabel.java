package com.windthunderstudio.ui.elements.menuitem;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.windthunderstudio.logic.util.CTS;

public class BoldLabel extends JLabel {
    private String textKey;
    
    public String getTextKey() {
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public BoldLabel() {
        super();
        this.setFont(CTS.ARIAL_BOLD_14);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }
    
    public BoldLabel(String text) {
        this();
        this.setText(text);
    }
}
