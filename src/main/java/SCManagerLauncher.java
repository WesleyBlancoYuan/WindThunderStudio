import javax.swing.SwingUtilities;

import com.windthunderstudio.scmanager.gui.MainFrame;

public class SCManagerLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf = new MainFrame();
            }
        });
    }
}
