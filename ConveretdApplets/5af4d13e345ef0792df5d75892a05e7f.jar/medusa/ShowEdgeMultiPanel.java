// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JPanel;

public class ShowEdgeMultiPanel extends JPanel
{
    private MedusaSettings ss;
    private MedusaFrame parent;
    private final int baseSize = 20;
    private ShowEdgePanel sp;
    
    public ShowEdgeMultiPanel() {
        this.sp = new ShowEdgePanel(new Color(244, 20, 120), "My interaction", 20, 20, 1, this);
        this.ss = new MedusaSettings();
        this.initComponents();
        this.initEdgePanels();
    }
    
    public ShowEdgeMultiPanel(final MedusaFrame parent, final MedusaSettings ss) {
        this.sp = new ShowEdgePanel(new Color(244, 20, 120), "My interaction", 20, 20, 1, this);
        this.ss = ss;
        this.parent = parent;
        this.initComponents();
        this.initEdgePanels();
    }
    
    public void updateSettings(final MedusaSettings ss) {
        this.removeAll();
        this.ss = ss;
        this.initEdgePanels();
    }
    
    private void initEdgePanels() {
        for (int i = 1; i <= this.ss.getSize(); ++i) {
            final Integer n = new Integer(i);
            this.add(new ShowEdgePanel(this.ss.getColor(n), this.ss.getName(n, 0), 20, 20, i, this));
        }
    }
    
    private void initComponents() {
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(new Color(238, 238, 238));
    }
    
    protected void handleEdgeEvent(final int n, final boolean b) {
        this.parent.handleEdgeEvent(n, b);
    }
}
