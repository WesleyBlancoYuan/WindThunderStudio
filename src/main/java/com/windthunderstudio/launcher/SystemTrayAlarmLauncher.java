package com.windthunderstudio.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.ui.GUI_Manager;

public class SystemTrayAlarmLauncher {
    private static Logger log = LogManager.getLogger(SystemTrayAlarmLauncher.class);
    public static void main(String[] args) {
        logSystemInfo();
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    GUI_Manager manager = new GUI_Manager();
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            log.fatal("Not able to launch application: ", e);
        }
    }
    
    private static void logSystemInfo() {
        log.info(System.getProperty("os.name"));
        log.info(System.getProperty("os.arch"));
        log.info(System.getProperty("os.version"));
        
        log.info(new Date());
        log.info(System.lineSeparator());
    }

}
