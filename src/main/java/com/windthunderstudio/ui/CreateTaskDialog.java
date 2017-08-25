package com.windthunderstudio.ui;

import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;

import net.miginfocom.swing.MigLayout;

public class CreateTaskDialog extends JDialog {
    private static final Logger log = LogManager.getLogger(CreateTaskDialog.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    private JTabbedPane alarmType;
    
    
    public CreateTaskDialog() {
        createGUI();
    }
    
    private void createGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(localeProp.getProperty(CTS.DIALOG_TITLE_CREATE_ALARM));
        setLayout(new MigLayout("insets 10, fill", "[]5[]", "[]5[]5[]5[]5[]5[]"));
        setBounds(0, 0, 600, 500);
        
        
        
        pack();
        setBounds(0, 0, 600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
