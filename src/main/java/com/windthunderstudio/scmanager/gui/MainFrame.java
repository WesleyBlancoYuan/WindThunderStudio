package com.windthunderstudio.scmanager.gui;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.gui.controller.FontManager;
import com.windthunderstudio.scmanager.gui.elements.JFileChooserSystemLAF;
import com.windthunderstudio.scmanager.gui.elements.LayerFileTrees;
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

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
    private static final Logger log = LogManager.getLogger(MainFrame.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    /* pricipal UI elements */
    private PlainMenuBar menuBar;
    private JPanel content;
    private JPanel bot;
    
    /* buttons */
    private PlainButton back;
    private PlainButton next;
    private PlainButton process;
    private PlainButton cancel;
    
    /* Layers */
    private LayerWorkspace layerWS;
    private LayerFileTrees layerFileTree;
    /* for data */
    public static File oldWSPath;
    public static File newWSPath;
    
    private String[] cardNames;
    private int index;
    
    /**
     * All components to update text when locale changes.
     */
    /* For reflection */
    public static List<JComponent> textComponents;
    
    /**
     * All components without textKey to change text when locale changes. 
     * Only change font.
     */
    public static List<JComponent> fontComponents;
    
    public MainFrame() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            FontManager.registerFont();
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Nimbus Look&Feel not supported: ", e);
        }
        createGUI();
    }
    
    private void createGUI() {
        setLayout(new MigLayout("insets 10, fill", "[]", "[grow, fill]5[]"));
        setBounds(0, 0, 1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Menu Bar */
        menuBar = new PlainMenuBar();
        setJMenuBar(menuBar);
        
        /* Content panel */
        content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new CardLayout());
        
        layerWS = new LayerWorkspace(localeProp.getProperty(CTS.TEXT_LAYER_DESC_WS_HTML),
                CTS.TEXT_LAYER_DESC_WS_HTML);
        
        layerFileTree = new LayerFileTrees(localeProp.getProperty(CTS.TEXT_LAYER_DESC_FILETREE_HTML),
                CTS.TEXT_LAYER_DESC_FILETREE_HTML);
        
        content.add(layerWS, CTS.LAYER_NAME_WS);
        content.add(layerFileTree, CTS.LAYER_NAME_FT);
        
        cardNames = new String[2];
        cardNames[0] = CTS.LAYER_NAME_WS;
        cardNames[1] = CTS.LAYER_NAME_FT;
        
        
        /* Bot panel for back, next, process and cancel button. */
        bot = new JPanel();
        bot.setOpaque(false);
        bot.setLayout(new MigLayout("insets 5, fill", "push[]5[]5[]5[]", ""));
        
        back = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_BACK));
        back.setTextKey(CTS.TEXT_BTN_BACK);
        back.setMnemonic(KeyEvent.VK_B);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index > 0) index--;
                showCard(cardNames[index]);
            }
        });
        bot.add(back, "cell 0 0, w 100!");
        
        next = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_NEXT));
        next.setTextKey(CTS.TEXT_BTN_NEXT);
        next.setMnemonic(KeyEvent.VK_N);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < cardNames.length-1) index++;
                showCard(cardNames[index]);
            }
        });
        bot.add(next, "cell 1 0, w 100!");
        
        process = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_PROCESS));
        process.setTextKey(CTS.TEXT_BTN_PROCESS);
        process.setMnemonic(KeyEvent.VK_P);
        bot.add(process, "cell 2 0, w 100!");
        
        cancel = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_CANCEL));
        cancel.setTextKey(CTS.TEXT_BTN_CANCEL);
        cancel.setMnemonic(KeyEvent.VK_C);
        bot.add(cancel, "cell 3 0, w 100!");
        
        
        loadComponentsIntoList();
        addPropertyChangeListener("index", new PropertyChangeListener() {
            
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int newIndex = ((Integer)(evt.getNewValue())).intValue();
                if (newIndex == cardNames.length - 1) {
                    next.setEnabled(false);
                } else {
                    next.setEnabled(true);
                }
                if (newIndex == 0) {
                    back.setEnabled(false);
                } else {
                    back.setEnabled(true);
                }
            }
        });
        
        add(content, "cell 0 0, grow");
        showCard(cardNames[0]);
        add(bot, "cell 0 1, grow");
        
        pack();
        setResizable(false);
        setBounds(0, 0, 1000, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadComponentsIntoList() {
        textComponents = new ArrayList<JComponent>();
        fontComponents = new ArrayList<JComponent>();
        /* Container itself */
        textComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainButton.class.getName(), this, false));
        
        /* MenuBar */
        textComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenuItem.class.getName(), menuBar, false));
        textComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainMenu.class.getName(), menuBar, false));
        
        /* first layer */
        textComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainLabel.class.getName(), layerWS, false));
        textComponents.addAll(ReflectionUIHandler.loadComponentsByClass(PlainButton.class.getName(), layerWS, false));
        textComponents.addAll(ReflectionUIHandler.loadComponentsByClass(TitleLabel.class.getName(), layerWS, true));
        fontComponents.addAll(ReflectionUIHandler.loadComponentsByClass(JTextField.class.getName(), layerWS, false));
        
        
    }

    private void showCard(String card) {
        CardLayout cl = (CardLayout)content.getLayout();
        cl.show(content, card);
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
