package com.windthunderstudio.scmanager.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.scmanager.gui.elements.LayerWorkspace;
import com.windthunderstudio.scmanager.util.CTS;
import com.windthunderstudio.scmanager.util.CommonUtils;

public class PrefFilesFinder {
    private static final Logger log = LogManager.getLogger(PrefFilesFinder.class);
    
    private File workspace;
    public PrefFilesFinder() {
        
    }
    
    public PrefFilesFinder(File wsroot) {
        this();
        this.workspace = wsroot;
        
    }

    public List<File>[] findAllPrefFiles() {
        File metadata = searchPrefFilesRoot();
        List<File> runtimePrefs = new ArrayList<File>();
        List<File> others = new ArrayList<File>();
        List<File>[] all = new List[2];
        if (metadata != null && metadata.exists()) {
            if (metadata.isDirectory()) {
                List<File> allPrefs = CommonUtils.recursiveSearchFile(metadata.toPath());
                
                File runtime = new File(CTS.PREF_RUNTIME_ROOT);
                for (File f: allPrefs) {
                    if (f.getParentFile().equals(runtime)) {
                        runtimePrefs.add(f);
                    } else {
                        others.add(f);
                    }
                }
                all[0] = runtimePrefs;
                all[1] = others;
                return all;
            }
        }
        return all;
    }
    private File searchPrefFilesRoot() {
        File root = null;
        if (workspace != null && workspace.exists()) {
            String wsStr = workspace.toString();
            if (!wsStr.endsWith(CTS.fsp)) wsStr = wsStr + CTS.fsp;
            root = new File(wsStr.concat(CTS.PREF_METADATA_ROOT));
            return root;
        } else {
            log.error("The path to the .metadata directory is not found. ");
            return null;
        }
    }
    
    
}
