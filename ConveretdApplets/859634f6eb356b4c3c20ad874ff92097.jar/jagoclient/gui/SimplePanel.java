// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Panel;

public class SimplePanel extends Panel
{
    GridBagLayout gridbag;
    
    public SimplePanel(final Component component, final double n, final Component component2, final double n2) {
        this.setLayout(this.gridbag = new GridBagLayout());
        this.add(component);
        this.constrain(component, 0, 0, 1, 1, 1, 10, n, 100.0);
        this.add(component2);
        this.constrain(component2, 1, 0, 1, 1, 1, 10, n2, 100.0);
    }
    
    void constrain(final Component component, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int fill, final int anchor, final double weightx, final double weighty) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;
        this.gridbag.setConstraints(component, gridBagConstraints);
    }
}
