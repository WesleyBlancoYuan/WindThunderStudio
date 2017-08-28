package com.windthunderstudio.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.ui.elements.PlainButton;

import net.miginfocom.swing.MigLayout;

public class CreateTask extends JDialog {
    private static final Logger log = LogManager.getLogger(CreateTask.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    private JTabbedPane alarmType;
    private JLayeredPane interval;
    private JLayeredPane timestamp;
    
    private PlainButton ok;
    private PlainButton cancel;
    
    public CreateTask() {
        createGUI();
    }
    
    private void createGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(localeProp.getProperty(CTS.DIALOG_TITLE_CREATE_ALARM));
        setLayout(new MigLayout("insets 10, fill", "[]", "[fill, grow]5[]"));
        setBounds(0, 0, 600, 500);
        
        alarmType = new JTabbedPane(JTabbedPane.TOP);
        
        interval = new JLayeredPane();
        
        interval.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        alarmType.addTab(localeProp.getProperty(CTS.LAYER_TITLE_INTERVAL), null, interval, 
                localeProp.getProperty(CTS.LAYER_TITLE_INTERVAL_TOOLTIP));
        
        timestamp = new JLayeredPane();
        timestamp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        alarmType.addTab(localeProp.getProperty(CTS.LAYER_TITLE_TIMESTAMP), null, timestamp, 
                localeProp.getProperty(CTS.LAYER_TITLE_TIMESTAMP_TOOLTIP));
        
        add(alarmType, "cell 0 0, grow");

        JPanel bot = new JPanel();
        bot.setOpaque(false);
        bot.setLayout(new MigLayout("insets 5, fill", "push[]5[]", "[]"));
        add(bot, "cell 0 1, grow");
        
        
        ok = new PlainButton();
        ok.setText(localeProp.getProperty(CTS.DIALOG_OPTION_CONFIRM));
        ok.setTextKey(CTS.DIALOG_OPTION_CONFIRM);
        ok.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                createAlarm();
            }
        });
        bot.add(ok, "cell 0 0, w 100!");
        
        cancel = new PlainButton();
        cancel.setText(localeProp.getProperty(CTS.DIALOG_OPTION_CANCEL));
        cancel.setTextKey(CTS.DIALOG_OPTION_CANCEL);
        cancel.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        bot.add(cancel, "cell 1 0, w 100!");
        
        
        
        pack();
        setBounds(0, 0, 600, 500);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    private boolean createAlarm() {
        return false;
    }

    public JLayeredPane getInterval() {
        return interval;
    }

    public JLayeredPane getTimestamp() {
        return timestamp;
    }

    public PlainButton getOk() {
        return ok;
    }

    public PlainButton getCancel() {
        return cancel;
    }
    
    
}
