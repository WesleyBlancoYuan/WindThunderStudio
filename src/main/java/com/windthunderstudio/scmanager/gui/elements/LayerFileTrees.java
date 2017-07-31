package com.windthunderstudio.scmanager.gui.elements;

import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTree;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.I18N_Manager;

import net.miginfocom.swing.MigLayout;

public class LayerFileTrees extends LayerPanelWithSection {
    private static final Logger log = LogManager.getLogger(LayerFileTrees.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    private PlainLabel oldLabel;
    private PlainLabel newLabel;
    
    /* for reflection use: get the getter */
    public TitleLabel getTopTitle() {
        return super.topTitle;
    }
    
    public LayerFileTrees(String topDesc) {
        super(topDesc);
        log.debug("Entering creating LayerFileTrees...");
        createLayerFileTrees();
        log.debug("Exiting creating LayerFileTrees...");
    }
    
    private void createLayerFileTrees() {
        JPanel treeCenter = new JPanel();
        treeCenter.setOpaque(false);
        treeCenter.setLayout(new MigLayout("insets 5, fill", "[]5[]", "[]5[]"));
        
        oldLabel = new PlainLabel();
        oldLabel.setText(localeProp.getProperty(CTS.TEXT_LBL_OLD_TREE));
        oldLabel.setTextKey(CTS.TEXT_LBL_OLD_TREE);
        treeCenter.add(oldLabel, "cell 0 0, grow");
        
        newLabel = new PlainLabel();
        newLabel.setText(localeProp.getProperty(CTS.TEXT_LBL_NEW_TREE));
        newLabel.setTextKey(CTS.TEXT_LBL_NEW_TREE);
        treeCenter.add(newLabel, "cell 1 0, grow");
        
        JTree oldTree = new JTree();
        
        
        
        
        super.setCenterSection(treeCenter); 
        super.createSections(); //add all elements to layer.
    }
}
