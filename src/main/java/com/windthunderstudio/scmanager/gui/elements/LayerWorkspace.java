package com.windthunderstudio.scmanager.gui.elements;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private JFileChooserSystemLAF oldChooser;
    private JFileChooserSystemLAF newChooser;
    
    /* for reflection use: get the getter */
    public TitleLabel getTopTitle() {
        return super.topTitle;
    }
    
    public LayerWorkspace(String topDesc) {
        super(topDesc);
        log.debug("Entering creating LayerWorkspace...");
        createLayerWS();
        log.debug("Exiting creating LayerWorkspace...");
    }
    
    private void createLayerWS() {
        JPanel wsCenter = new JPanel();
        wsCenter.setOpaque(false);
        wsCenter.setLayout(new MigLayout("insets 5, fill", "[]5[]", "[]5[]"));
        
        oldWS = new PlainLabel(localeProp.getProperty(CTS.TEXT_LBL_OLD_WS_HTML));
        oldWS.setTextKey(CTS.TEXT_LBL_OLD_WS_HTML);
        wsCenter.add(oldWS, "cell 0 0, w 150!, sg lblws");
        
        newWS = new PlainLabel(localeProp.getProperty(CTS.TEXT_LBL_NEW_WS_HTML));
        newWS.setTextKey(CTS.TEXT_LBL_NEW_WS_HTML);
        wsCenter.add(newWS, "cell 0 1, w 150!, sg lblws");
        
        oldChooser = new JFileChooserSystemLAF();
        oldChooser.setMultiSelectionEnabled(false); // disable multiple selection
        oldChooser.setCurrentDirectory((lastLocationOld != null && lastLocationOld.exists()) ? 
                lastLocationOld : new File(System.getProperty("usr.home")));
        oldChooser.setAcceptAllFileFilterUsed(false); //don't show "Accept all" in file filter
        oldChooser.setApproveButtonMnemonic(KeyEvent.VK_A);
        oldChooser.setDialogType(JFileChooser.OPEN_DIALOG); //this is a "open file" dialog; default
        oldChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int oldStatus = oldChooser.showOpenDialog(this);
        if (oldStatus == JFileChooser.APPROVE_OPTION) {
            lastLocationOld = oldChooser.getSelectedFile();
            log.debug("Last time chosen dir for old workspace: " + lastLocationNew.getPath());
        }
        
        wsCenter.add(oldChooser, "cell 1 0, grow");
        
        newChooser = new JFileChooserSystemLAF();
        newChooser.setMultiSelectionEnabled(false); // disable multiple selection
        newChooser.setCurrentDirectory((lastLocationNew != null && lastLocationNew.exists()) ? 
                lastLocationNew : new File(System.getProperty("usr.home")));
        newChooser.setAcceptAllFileFilterUsed(false); //don't show "Accept all" in file filter
        newChooser.setApproveButtonMnemonic(KeyEvent.VK_A);
        newChooser.setDialogType(JFileChooser.OPEN_DIALOG); //this is a "open file" dialog; default
        newChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int newStatus = newChooser.showOpenDialog(this);
        if (newStatus == JFileChooser.APPROVE_OPTION) {
            lastLocationNew = newChooser.getSelectedFile();
            log.debug("Last time chosen dir for new workspace: " + lastLocationNew.getPath());
        }
        
        wsCenter.add(newChooser, "cell 1 1, grow");
        
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
    
    
}

