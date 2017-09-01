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
import com.windthunderstudio.ui.elements.BoldLabel;
import com.windthunderstudio.ui.elements.PlainButton;
import com.windthunderstudio.ui.elements.PlainCheck;
import com.windthunderstudio.ui.elements.PlainLabel;
import com.windthunderstudio.ui.elements.PlainRadio;
import com.windthunderstudio.ui.elements.PlainTextfield;
import com.windthunderstudio.ui.elements.SimpleLayer;

import net.miginfocom.swing.MigLayout;

public class CreateTask extends JDialog {
    private static final Logger log = LogManager.getLogger(CreateTask.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    private JTabbedPane alarmType;
    private SimpleLayer interval;
    private SimpleLayer timestamp;
    
    private PlainButton ok;
    private PlainButton cancel;
    
    private BoldLabel intervalLBL;
    
    private PlainTextfield day;
    private PlainLabel dayLBL;
    
    private PlainTextfield hour;
    private PlainLabel hourLBL;
    
    private PlainTextfield min;
    private PlainLabel minLBL;
    
    private BoldLabel remindType;
    private PlainRadio withPopup;
    private PlainRadio withDialog;
    private PlainCheck beep;
    
    private BoldLabel desc;
    private PlainTextfield descField;
    
    private PlainCheck startNow;
    
    public CreateTask() {
        createGUI();
    }
    
    private void createGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(localeProp.getProperty(CTS.DIALOG_TITLE_CREATE_ALARM));
        setLayout(new MigLayout("insets 10, fill", "[]", "[fill, grow]5[]"));
        setBounds(0, 0, 600, 500);
        
        alarmType = new JTabbedPane(JTabbedPane.TOP);
        
        interval = new SimpleLayer();
        interval.setOpaque(false);
        interval.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        alarmType.addTab(localeProp.getProperty(CTS.LAYER_TITLE_INTERVAL), null, interval, 
                localeProp.getProperty(CTS.LAYER_TITLE_INTERVAL_TOOLTIP));
        interval.setTextKey(CTS.LAYER_TITLE_INTERVAL);
        interval.setTooltipTextKey(CTS.LAYER_TITLE_INTERVAL_TOOLTIP);
        
        interval.setLayout(new MigLayout("insets 5, fill", "[]5[]", "[::30]5[::30]5[::30]5[::30]"));
        
        /* first line: set time interval: day, hour and minute */
        intervalLBL = new BoldLabel();
        intervalLBL.setText(localeProp.getProperty(CTS.TEXT_LBL_INTERVAL));
        intervalLBL.setTextKey(CTS.TEXT_LBL_INTERVAL);
        interval.add(intervalLBL, "cell 0 0, sg leftlbl");
        
        day = new PlainTextfield();
        interval.add(day, "cell 1 0, split 6, w 50!");
        
        dayLBL = new PlainLabel();
        dayLBL.setText(localeProp.getProperty(CTS.TEXT_LBL_DAYS));
        dayLBL.setTextKey(CTS.TEXT_LBL_DAYS);
        interval.add(dayLBL, "grow, sg lbl1");
        
        hour = new PlainTextfield();
        interval.add(hour, "w 50!");
        
        hourLBL = new PlainLabel();
        hourLBL.setText(localeProp.getProperty(CTS.TEXT_LBL_HOURS));
        hourLBL.setTextKey(CTS.TEXT_LBL_HOURS);
        interval.add(hourLBL, "grow, sg lbl1");
        
        min = new PlainTextfield();
        interval.add(min, "w 50!");
        
        minLBL = new PlainLabel();
        minLBL.setText(localeProp.getProperty(CTS.TEXT_LBL_MINUTES));
        minLBL.setTextKey(CTS.TEXT_LBL_MINUTES);
        interval.add(minLBL, "grow, sg lbl1");
        
        /* second line: how to remind: with right bottom corner popup, or with dialog */
        remindType = new BoldLabel();
        remindType.setText(localeProp.getProperty(CTS.TEXT_LBL_REMINDTYPE));
        remindType.setTextKey(CTS.TEXT_LBL_REMINDTYPE);
        interval.add(remindType, "cell 0 1, grow, sg leftlbl");
        
        withPopup = new PlainRadio();
        withPopup.setText(localeProp.getProperty(CTS.TEXT_RADIO_WITHPOPUP));
        withPopup.setTextKey(CTS.TEXT_RADIO_WITHPOPUP);
        interval.add(withPopup, "cell 1 1, split 2, grow");
        
        withDialog = new PlainRadio();
        withDialog.setText(localeProp.getProperty(CTS.TEXT_RADIO_WITHDIALOG));
        timestamp = new SimpleLayer();
        timestamp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        alarmType.addTab(localeProp.getProperty(CTS.LAYER_TITLE_TIMESTAMP), null, timestamp, 
                localeProp.getProperty(CTS.LAYER_TITLE_TIMESTAMP_TOOLTIP));
        timestamp.setTextKey(CTS.LAYER_TITLE_TIMESTAMP);
        timestamp.setTooltipTextKey(CTS.LAYER_TITLE_TIMESTAMP_TOOLTIP);
        
        
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

    public JTabbedPane getAlarmType() {
        return alarmType;
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
