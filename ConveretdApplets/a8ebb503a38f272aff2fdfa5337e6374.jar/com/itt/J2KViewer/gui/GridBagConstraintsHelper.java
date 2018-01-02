// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.GridBagConstraints;

public class GridBagConstraintsHelper
{
    public static GridBagConstraints buildConstraints(final int gridx, final int gridy, final int fill, final int ipadx, final int ipady, final int anchor) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.fill = fill;
        gridBagConstraints.ipadx = ipadx;
        gridBagConstraints.ipady = ipady;
        gridBagConstraints.anchor = anchor;
        return gridBagConstraints;
    }
    
    public static GridBagConstraints buildConstraints(final int n, final int n2, final int n3) {
        return buildConstraints(n, n2, 0, 0, 0, n3);
    }
    
    public static GridBagConstraints buildConstraints(final int n, final int n2, final int n3, final int n4) {
        return buildConstraints(n, n2, n3, 0, 0, n4);
    }
    
    public static GridBagConstraints buildConstraints(final int n, final int n2, final int n3, final int n4, final int n5) {
        return buildConstraints(n, n2, 0, n3, n4, n5);
    }
    
    public static GridBagConstraints buildConstraintsWithWeight(final int n, final int n2, final int n3, final int n4, final int n5, final double weightx, final double weighty) {
        final GridBagConstraints buildConstraints = buildConstraints(n, n2, n3, n4, n5, 17);
        buildConstraints.weightx = weightx;
        buildConstraints.weighty = weighty;
        return buildConstraints;
    }
}
