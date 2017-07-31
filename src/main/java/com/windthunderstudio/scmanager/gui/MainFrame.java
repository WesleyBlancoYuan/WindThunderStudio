package com.windthunderstudio.scmanager.gui;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.gui.elements.JFileChooserSystemLAF;
import com.windthunderstudio.scmanager.gui.elements.LayerPanelWithSection;
import com.windthunderstudio.scmanager.gui.elements.LayerWorkspace;
import com.windthunderstudio.scmanager.gui.elements.PlainButton;
import com.windthunderstudio.scmanager.gui.elements.PlainLabel;
import com.windthunderstudio.scmanager.gui.elements.PlainMenu;
import com.windthunderstudio.scmanager.gui.elements.PlainMenuBar;
import com.windthunderstudio.scmanager.gui.elements.PlainMenuItem;
import com.windthunderstudio.scmanager.gui.elements.TitleLabel;
import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.ConfigReader;
import com.windthunderstudio.scmanager.util.I18N_Manager;
import com.windthunderstudio.scmanager.util.ReflectionUIHandler;
import com.windthunderstudio.scmanager.gui.FontManager;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
    private static final Logger log = LogManager.getLogger(MainFrame.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    //pricipal UI elements
    private PlainMenuBar menuBar;
    private JPanel content;
    private JPanel bot;
    
    /* buttons */
    private PlainButton back;
    private PlainButton next;
    private PlainButton process;
    private PlainButton cancel;
    
    /* Layers */
    private LayerWorkspace wsLayer;
    
    /* For reflection */ 
    public static List<JComponent> allComponents;
    
    public MainFrame() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            FontManager fontManager = new FontManager();
            fontManager.registerFont();
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Nimbus Look&Feel not supported: ", e);
        }
        createGUI();
    }
    
    private void createGUI() {
        setLayout(new MigLayout("insets 10, fill, debug", "", "[]5[]5[]"));
        
        /* Menu Bar */
        menuBar = new PlainMenuBar();
        setJMenuBar(menuBar);
        
        /* Content panel */
        content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new CardLayout());
        
        wsLayer = new LayerWorkspace(localeProp.getProperty(CTS.TEXT_LAYER_DESC_WS_HTML));
        
        
        
        
        /* Bot panel for back, next, process and cancel button. */
        bot = new JPanel();
        bot.setOpaque(false);
        bot.setLayout(new MigLayout("insets 5, fill, debug", "push[]5[]5[]5[]", ""));
        
        back = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_BACK));
        back.setTextKey(CTS.TEXT_BTN_BACK);
        back.setMnemonic(KeyEvent.VK_B);
        bot.add(back, "cell 0 0, w 100!");
        
        next = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_NEXT));
        next.setMnemonic(KeyEvent.VK_N);
        bot.add(next, "cell 1 0, w 100!");
        
        process = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_PROCESS));
        process.setMnemonic(KeyEvent.VK_P);
        bot.add(process, "cell 2 0, w 100!");
        
        cancel = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_CANCEL));
        cancel.setMnemonic(KeyEvent.VK_C);
        bot.add(cancel, "cell 3 0, w 100!");
        
        loadComponentsIntoList();
    }

    private void loadComponentsIntoList() {
        allComponents = new ArrayList<JComponent>();
        /* Container itself */
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainButton.class.getName(), this));
        
        /* MenuBar */
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenuItem.class.getName(), menuBar));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenu.class.getName(), menuBar));
        /* first layer */
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainLabel.class.getName(), wsLayer));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(JFileChooserSystemLAF.class.getName(), wsLayer));
        allComponents.addAll(ReflectionUIHandler.loadComponentsByClass(TitleLabel.class.getName(), wsLayer));
        
        
    }

    public PlainButton getBack() {
        return back;
    }

    public PlainButton getNext() {
        return next;
    }

    public PlainButton getProcess() {
        return process;
    }

    public PlainButton getCancel() {
        return cancel;
    }
}
