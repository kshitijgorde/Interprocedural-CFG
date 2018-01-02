// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import java.awt.Insets;
import java.awt.GridBagConstraints;

class MyGridBagConstraints extends GridBagConstraints
{
    public MyGridBagConstraints(final int gridx, final int gridy, final int gridwidth, final int gridheight, final double weightx, final double weighty, final int anchor, final int fill, final Insets insets, final int ipadx, final int ipady) {
        super.gridx = gridx;
        super.gridy = gridy;
        super.gridwidth = gridwidth;
        super.gridheight = gridheight;
        super.weightx = weightx;
        super.weighty = weighty;
        super.anchor = anchor;
        super.fill = fill;
        super.insets = insets;
        super.ipadx = ipadx;
        super.ipady = ipady;
    }
}
