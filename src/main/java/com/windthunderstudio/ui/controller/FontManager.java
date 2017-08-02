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

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;

public class FontManager {
    private static final Logger log = LogManager.getLogger(FontManager.class);
    
    public static final Font ARIAL_PLAIN = new Font("Arial", Font.PLAIN, 14);
    public static final Font ARIAL_BOLD = new Font("Arial", Font.BOLD, 14);
    
    public static Font WQ_BOLD;
    public static Font WQ_PLAIN;
    
    public static void registerFont() {
        if (!I18N_Manager.isChinese) {
            configDialogFont(ARIAL_PLAIN);
        } else {
            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Font wenquanPlain = Font.createFont(Font.TRUETYPE_FONT, new File(CTS.FONT_PATH_WENQUAN));
                //only after registering the font family name will be available.
                ge.registerFont(wenquanPlain);
                //must assign font with new, if is JOptionPane. If is JLabel, can just use 
                //with deriveFont()
                WQ_BOLD = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.BOLD, 15);
                WQ_PLAIN = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.PLAIN, 15);
                
                configDialogFont(WQ_PLAIN);
            } catch (IOException e) {
                log.error("Cannot locate Font file: ", e);
            } catch (FontFormatException e1) {
                log.error("Cannot format Font: ", e1);
            }
        }
    }
    
    private static void configDialogFont(Font font) {
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.font", font);
    }
    
    public static void changeFontWithStyle(JComponent c) {
        try {
            if (I18N_Manager.isChinese) {
                if (c.getFont().getStyle() == Font.BOLD) {
                    c.setFont(FontManager.WQ_BOLD);
                } else {
                    c.setFont(FontManager.WQ_PLAIN);
                }
            } else {
                if (c.getFont().getStyle() == Font.BOLD) {
                    c.setFont(FontManager.ARIAL_BOLD);
                } else {
                    c.setFont(FontManager.ARIAL_PLAIN);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error on the component of class " + 
                    c.getClass().getName() + ": ", e);
        }
        
    }
}
