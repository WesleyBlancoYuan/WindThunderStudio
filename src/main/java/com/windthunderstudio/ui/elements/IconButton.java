package com.windthunderstudio.ui.elements;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.ui.controller.FontManager;
import com.windthunderstudio.ui.controller.LocaleChangeController;
import com.windthunderstudio.ui.listener.LocaleListenerForFont;

public class IconButton extends JButton{

    public IconButton() {
        super();
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setText(null);
    }
    public IconButton(Icon icon) {
        this();
        setIcon(icon);
    }
}
