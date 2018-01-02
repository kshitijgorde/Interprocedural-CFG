// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class oversigtskortView extends JPanel
{
    static final long serialVersionUID = 0L;
    private JLabel oversigtskortLabel;
    CResourceManager myResource;
    
    public oversigtskortView() {
        this.oversigtskortLabel = new JLabel();
        this.myResource = CResourceManager.instance();
        if (Constant.debugMode) {
            System.out.println("I oversigtskortView");
        }
        this.setLayout(new BoxLayout(this, 3));
        this.oversigtskortLabel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("findsted.navigationskort")));
        this.add(this.oversigtskortLabel);
    }
}
