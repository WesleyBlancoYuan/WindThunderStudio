package com.windthunderstudio.launcher;

import javax.swing.SwingUtilities;

import com.windthunderstudio.ui.GUI_Manager;

public class SystemTrayAlarmLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                GUI_Manager manager = new GUI_Manager();
            }
        });
    }

}
