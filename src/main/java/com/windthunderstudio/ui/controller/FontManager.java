package com.windthunderstudio.ui.controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alee.laf.WebLookAndFeel;
import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;

public class FontManager {
    private static final Logger log = LogManager.getLogger(FontManager.class);
    
    public static final Font ARIAL_PLAIN = new Font("Arial", Font.PLAIN, 14);
    public static final Font ARIAL_BOLD = new Font("Arial", Font.BOLD, 14);
    
    public static Font WQ_BOLD;
    public static Font WQ_PLAIN;
    
    public static Font current = ARIAL_PLAIN;
    public static void registerFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font wenquanPlain = Font.createFont(Font.TRUETYPE_FONT, new File(CTS.FONT_PATH_WENQUAN));
            //only after registering the font family name will be available.
            ge.registerFont(wenquanPlain);
            //must assign font with new, if is JOptionPane. If is JLabel, can just use 
            //with deriveFont()
            WQ_BOLD = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.BOLD, 15);
            WQ_PLAIN = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.PLAIN, 15);
            
        } catch (IOException e) {
            log.error("Cannot locate Font file: ", e);
        } catch (FontFormatException e1) {
            log.error("Cannot format Font: ", e1);
        }
        
        if (!I18N_Manager.isChinese) {
            current = ARIAL_PLAIN;
        } else {
            current = WQ_PLAIN;
        }
        configDialogFont();
    }
    
    public static void configDialogFont() {
        UIManager.put("OptionPane.messageFont", current);
        UIManager.put("OptionPane.buttonFont", current);
        UIManager.put("OptionPane.font", current);
//        WebLookAndFeel.optionPaneFont = current;
    }
    
    public static void changeFontWithStyle(JComponent c) {
        try {
            if (I18N_Manager.isChinese) {
                if (c.getFont().getStyle() == Font.BOLD) {
                    c.setFont(WQ_BOLD);
                } else {
                    c.setFont(WQ_PLAIN);
                }
            } else {
                if (c.getFont().getStyle() == Font.BOLD) {
                    c.setFont(ARIAL_BOLD);
                } else {
                    c.setFont(ARIAL_PLAIN);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error when setting the font of a component of class " + 
                    c.getClass().getName() + ": ", e);
        }
        
    }
    
    public static void changeFontWithLang() {
        if (I18N_Manager.isChinese) {
            //only change for the first time
            if (!current.equals(WQ_PLAIN)) current = WQ_PLAIN;
        } else {
            //only change for the first time
            if (!current.equals(ARIAL_PLAIN)) current = ARIAL_PLAIN;
        }
    }
}
