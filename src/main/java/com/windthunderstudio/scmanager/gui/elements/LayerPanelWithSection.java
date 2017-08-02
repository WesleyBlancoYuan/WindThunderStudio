package com.windthunderstudio.scmanager.gui.elements;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.windthunderstudio.scmanager.util.CommonUtils;

import net.miginfocom.swing.MigLayout;

public class LayerPanelWithSection extends JLayeredPane {
    private JPanel topSection;
    private JPanel centerSection;
    /** top panel description */
    private String topDesc;
    protected TitleLabel topTitle;
    
    
    public LayerPanelWithSection(String desc, String textKey) {
        super();
        this.topDesc = desc;
        topTitle = new TitleLabel(desc);
        topTitle.setTextKey(textKey);
    }
    
    public void createSections() {
        setLayout(new MigLayout("insets 5, fill", "[]", "[]0[]0[fill, grow]"));
        
        topSection = new JPanel();
        topSection.setOpaque(false);
        topSection.setLayout(new MigLayout("insets 5, fill", "[]", "[]"));
        if (!CommonUtils.isBlankString(topDesc)) {
            topSection.add(topTitle, "grow");
        }
        this.add(topSection, "cell 0 0, grow");
        
        add(new JSeparator(SwingConstants.HORIZONTAL), "cell 0 1, grow");
        
        if (centerSection != null) {
            this.add(centerSection, "cell 0 2, grow");
        }
        
    }
    
    
    public JPanel getTopSection() {
        return topSection;
    }

    public void setTopSection(JPanel topSection) {
        this.topSection = topSection;
    }

    public JPanel getCenterSection() {
        return centerSection;
    }

    public void setCenterSection(JPanel centerSection) {
        this.centerSection = centerSection;
    }

    public TitleLabel getTopTitle() {
        return topTitle;
    }

    
}
