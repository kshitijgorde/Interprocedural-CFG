// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.awt;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;

public abstract class Container extends java.awt.Container
{
    public Container() {
        this.setLayout(new GridBagLayout());
    }
    
    protected abstract Dimension getBestSize();
    
    protected void addComponent(final Component comp, final int x, final int y, final int w, final int h, final int weightx, final int weighty) {
        final GridBagLayout gbl = (GridBagLayout)this.getLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(comp);
        gbl.setConstraints(comp, gbc);
    }
}
