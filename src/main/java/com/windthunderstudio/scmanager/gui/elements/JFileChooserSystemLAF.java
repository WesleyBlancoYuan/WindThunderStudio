package com.windthunderstudio.scmanager.gui.elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.windthunderstudio.scmanager.gui.FontManager;
import com.windthunderstudio.scmanager.gui.LocaleChangeController;
import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.I18N_Manager;

import sun.swing.FilePane;

public class JFileChooserSystemLAF extends JFileChooser implements LocaleChangeController {
    private String textKey;
    
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }
    
    @Override
    public String getTextKey() {
        return textKey;
    }
    
    public JFileChooserSystemLAF() {
        super();
        setFont(FontManager.ARIAL_PLAIN);
        switch (I18N_Manager.localeString) {
        case CTS.LOCALE_STR_EN:
            setLocale(Locale.ENGLISH);
            break;
        case CTS.LOCALE_STR_ES:
            setLocale(new Locale("ES"));
            break;
        case CTS.LOCALE_STR_ZH_HANS:
            setLocale(Locale.SIMPLIFIED_CHINESE);
        }
    }
    
    public void updateUI(){
        LookAndFeel old = UIManager.getLookAndFeel();
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Throwable ex) {
           old = null;
        } 

        super.updateUI();

        if(old != null){
           FilePane filePane = findFilePane(this);
           filePane.setViewType(FilePane.VIEWTYPE_DETAILS);
           filePane.setViewType(FilePane.VIEWTYPE_LIST);

           Color background = UIManager.getColor("Label.background");
           setBackground(background);
           setOpaque(true);

           try {
              UIManager.setLookAndFeel(old);
           } 
           catch (UnsupportedLookAndFeelException ignored) {} // shouldn't get here
        }
     }



     private static FilePane findFilePane(Container parent){
        for(Component comp: parent.getComponents()){
           if(FilePane.class.isInstance(comp)){
              return (FilePane)comp;
           }
           if(comp instanceof Container){
              Container cont = (Container)comp;
              if(cont.getComponentCount() > 0){
                 FilePane found = findFilePane(cont);
                 if (found != null) {
                    return found;
                 }
              }
           }
        }

        return null;
     }




}
