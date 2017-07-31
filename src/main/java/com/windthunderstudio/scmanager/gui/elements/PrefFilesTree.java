package com.windthunderstudio.scmanager.gui.elements;

import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

public class PrefFilesTree extends JTree {
    private DefaultTreeModel model;
    
    public PrefFilesTree() {
        super();
        
    }
    
    public PrefFilesTree(File wsroot) {
        this();
        if (wsroot.exists()) {
            
        }
    }
}
