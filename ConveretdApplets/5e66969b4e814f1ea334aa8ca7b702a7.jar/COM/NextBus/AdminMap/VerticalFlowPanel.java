// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.Applets.Spacer;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;

public class VerticalFlowPanel extends Panel
{
    private static final long serialVersionUID = 8861476826915475414L;
    private GridBagConstraints constraints;
    private GridBagLayout gridBag;
    private int vgap;
    
    public VerticalFlowPanel() {
        this(0);
    }
    
    public VerticalFlowPanel(final int vgap) {
        this.vgap = 0;
        this.vgap = vgap;
        this.setLayout(this.gridBag = new GridBagLayout());
        this.constraints = new GridBagConstraints();
    }
    
    public final void a(final Component component) {
        if (this.vgap > 0) {
            this.b(new Spacer(new Dimension(this.vgap, this.vgap)));
        }
        this.b(component);
    }
    
    private void b(final Component component) {
        this.constraints.fill = 2;
        this.constraints.gridwidth = 0;
        this.constraints.weightx = 1.0;
        this.constraints.anchor = 17;
        this.gridBag.setConstraints(component, this.constraints);
        this.add(component);
    }
}
