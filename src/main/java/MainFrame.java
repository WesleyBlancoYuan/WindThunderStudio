import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
    //main elements
    private JMenuBar menuBar;
    private JPanel content;
    private JPanel bot;
    
    //buttons
    private JButton back;
    private JButton next;
    private JButton ok;
    private JButton cancel;
    
    
    public MainFrame() {
        createGUI();
    }
    
    private void createGUI() {
        setLayout(new MigLayout("insets 10, fill, debug", "", "[]5[]5[]"));
//        menuBar = new JMenuBar();
//        add(menuBar, "cell 0 0, grow");
        content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new CardLayout());
        
        bot = new JPanel();
        bot.setOpaque(false);
        bot.setLayout(new MigLayout("insets 5, fill, debug", "[]5[]5[]5[]", ""));
        
        
    }
}
