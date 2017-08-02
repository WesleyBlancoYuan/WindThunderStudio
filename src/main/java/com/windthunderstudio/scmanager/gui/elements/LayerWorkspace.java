package com.windthunderstudio.scmanager.gui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.gui.MainFrame;
import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.I18N_Manager;

import net.miginfocom.swing.MigLayout;

public class LayerWorkspace extends LayerPanelWithSection {
    
    private static final Logger log = LogManager.getLogger(LayerWorkspace.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    private File lastLocationOld;
    private File lastLocationNew;
    private PlainLabel oldWS;
    private PlainLabel newWS;
    private JTextField oldLocation;
    private JTextField newLocation;
    private PlainButton chooseOld;
    private PlainButton chooseNew;
    private JFileChooserSystemLAF oldChooser;
    private JFileChooserSystemLAF newChooser;
    
    /* for reflection use: get the getter */
    public TitleLabel getTopTitle() {
        return topTitle;
    }
    
    public LayerWorkspace(String topDesc, String textKey) {
        super(topDesc, textKey);
        log.debug("Entering creating LayerWorkspace...");
        createLayerWS();
        log.debug("Exiting creating LayerWorkspace...");
    }
    
    private void createLayerWS() {
        JPanel wsCenter = new JPanel();
        wsCenter.setOpaque(false);
        wsCenter.setLayout(new MigLayout("insets 5, fill", "[]5[fill, grow]5[]", "[]5[]"));
        
        oldWS = new PlainLabel(localeProp.getProperty(CTS.TEXT_LBL_OLD_WS_HTML));
        oldWS.setTextKey(CTS.TEXT_LBL_OLD_WS_HTML);
        wsCenter.add(oldWS, "cell 0 0, w 200!, h 50!, sg lblws");
        
        newWS = new PlainLabel(localeProp.getProperty(CTS.TEXT_LBL_NEW_WS_HTML));
        newWS.setTextKey(CTS.TEXT_LBL_NEW_WS_HTML);
        wsCenter.add(newWS, "cell 0 1, w 200!, h 50!, sg lblws");
        
        oldLocation = new JTextField();
        oldLocation.setEnabled(false);
        wsCenter.add(oldLocation, "cell 1 0, growx");
        
        newLocation = new JTextField();
        newLocation.setEnabled(false);
        wsCenter.add(newLocation, "cell 1 1, growx");
        
        /* Define the JFileChooser before the buttons' actions. */
        oldChooser = new JFileChooserSystemLAF();
        oldChooser.setMultiSelectionEnabled(false); // disable multiple selection
        oldChooser.setCurrentDirectory((lastLocationOld != null && lastLocationOld.exists()) ? 
                lastLocationOld : new File(System.getProperty("user.home")));
        oldChooser.setAcceptAllFileFilterUsed(false); //don't show "Accept all" in file filter
        oldChooser.setApproveButtonMnemonic(KeyEvent.VK_A);
        oldChooser.setDialogType(JFileChooser.OPEN_DIALOG); //this is a "open file" dialog; default
        oldChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        newChooser = new JFileChooserSystemLAF();
        newChooser.setMultiSelectionEnabled(false); // disable multiple selection
        newChooser.setCurrentDirectory((lastLocationNew != null && lastLocationNew.exists()) ? 
                lastLocationNew : new File(System.getProperty("user.home")));
        newChooser.setAcceptAllFileFilterUsed(false); //don't show "Accept all" in file filter
        newChooser.setApproveButtonMnemonic(KeyEvent.VK_A);
        newChooser.setDialogType(JFileChooser.OPEN_DIALOG); //this is a "open file" dialog; default
        newChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        /* buttons to open JFileChoosers */
        chooseOld = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_CHOOSE));
        chooseOld.setTextKey(CTS.TEXT_BTN_CHOOSE);
        chooseOld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldStatus = oldChooser.showOpenDialog(LayerWorkspace.this);
                if (oldStatus == JFileChooser.APPROVE_OPTION) {
                    lastLocationOld = MainFrame.oldWSPath = oldChooser.getSelectedFile();
                    oldLocation.setText(lastLocationOld.getAbsolutePath());
                    log.debug("Last time chosen dir for old workspace: " + lastLocationOld.getPath());
                }
            }
        });
        wsCenter.add(chooseOld, "cell 2 0, w 180!");
        
        chooseNew = new PlainButton(localeProp.getProperty(CTS.TEXT_BTN_CHOOSE));
        chooseNew.setTextKey(CTS.TEXT_BTN_CHOOSE);
        chooseNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newStatus = newChooser.showOpenDialog(LayerWorkspace.this);
                if (newStatus == JFileChooser.APPROVE_OPTION) {
                    lastLocationNew = MainFrame.newWSPath = newChooser.getSelectedFile();
                    newLocation.setText(lastLocationNew.getAbsolutePath());
                    log.debug("Last time chosen dir for new workspace: " + lastLocationNew.getPath());
                }
            }
        });
        wsCenter.add(chooseNew, "cell 2 1, w 180!");

        super.setCenterSection(wsCenter); 
        
        super.createSections(); //add all elements to layer.
    }

    public PlainLabel getOldWS() {
        return oldWS;
    }

    public PlainLabel getNewWS() {
        return newWS;
    }

    public JFileChooserSystemLAF getOldChooser() {
        return oldChooser;
    }

    public JFileChooserSystemLAF getNewChooser() {
        return newChooser;
    }

    public PlainButton getChooseOld() {
        return chooseOld;
    }

    public PlainButton getChooseNew() {
        return chooseNew;
    }

    public static Logger getLog() {
        return log;
    }

    public JTextField getOldLocation() {
        return oldLocation;
    }

    public JTextField getNewLocation() {
        return newLocation;
    }
    
    
}

