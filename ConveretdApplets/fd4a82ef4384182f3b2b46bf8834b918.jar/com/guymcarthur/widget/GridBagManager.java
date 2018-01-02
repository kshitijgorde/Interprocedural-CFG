// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.widget;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Container;

public class GridBagManager
{
    public static void add(final Container container, final Component component, final int gridy, final int gridx, final int gridheight, final int gridwidth, final int fill, final int anchor, final double weightx, final double weighty, final int n, final int n2, final int n3, final int n4) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;
        if (n + n3 + n2 + n4 > 0) {
            gridBagConstraints.insets = new Insets(n, n2, n3, n4);
        }
        ((GridBagLayout)container.getLayout()).setConstraints(component, gridBagConstraints);
        container.add(component);
    }
    
    public static void add(final Container container, final Component component, final int n, final int n2) {
        add(container, component, n, n2, 1, 1, 0, 18, 0.0, 0.0, 0, 0, 0, 0);
    }
    
    public static void add(final Container container, final Component component, final int n, final int n2, final int n3, final int n4) {
        add(container, component, n, n2, n3, n4, 0, 18, 0.0, 0.0, 0, 0, 0, 0);
    }
    
    public static void add(final Container container, final Component component, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        add(container, component, n, n2, n3, n4, 0, 18, 0.0, 0.0, n5, n6, n7, n8);
    }
    
    public static void add(final Container container, final Component component, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        add(container, component, n, n2, n3, n4, n5, n6, 0.0, 0.0, 0, 0, 0, 0);
    }
}
