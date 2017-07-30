package com.windthunderstudio.scmanager.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.I18N_Manager;

public class FontManager {
    private static final Logger log = LogManager.getLogger(FontManager.class);
    
    public static final Font ARIAL_PLAIN = new Font("Arial", Font.PLAIN, 14);
    public static final Font ARIAL_BOLD = new Font("Arial", Font.BOLD, 14);
    
    public static Font WQ_BOLD;
    public static Font WQ_PLAIN;
    
    public void registerFont() {
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
                WQ_BOLD = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.BOLD, 14);
                WQ_PLAIN = new Font(CTS.FONT_FAMILY_NAME_WENQUAN, Font.PLAIN, 14);
                
                configDialogFont(WQ_PLAIN);
            } catch (IOException e) {
                log.error("Cannot locate Font file: ", e);
            } catch (FontFormatException e1) {
                log.error("Cannot format Font: ", e1);
            }
        }
    }
    
    public void configDialogFont(Font font) {
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.font", font);
    }
}
