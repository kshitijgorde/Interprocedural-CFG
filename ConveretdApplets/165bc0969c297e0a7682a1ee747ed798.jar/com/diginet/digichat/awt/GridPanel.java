// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Panel;

public class GridPanel extends Panel
{
    private int width;
    private int height;
    private int[] nW;
    private int[] nH;
    private Component[][] cmpGrid;
    
    public GridPanel(final int n, final int n2) {
        super(null);
        this.nW = new int[n];
        this.nH = new int[n2];
        this.cmpGrid = new Component[n][n2];
    }
    
    public void add(final Component component, final int n, final int n2) {
        this.add(component);
        this.cmpGrid[n][n2] = component;
        final Dimension preferredSize = component.preferredSize();
        if (preferredSize.width > this.nW[n]) {
            this.nW[n] = preferredSize.width;
        }
        if (preferredSize.height > this.nH[n2]) {
            this.nH[n2] = preferredSize.height;
        }
    }
    
    public void addNotify() {
        this.height = 0;
        for (int i = 0; i < this.nH.length; ++i) {
            this.width = 0;
            for (int j = 0; j < this.nW.length; ++j) {
                final Dimension preferredSize = this.cmpGrid[j][i].preferredSize();
                this.cmpGrid[j][i].setLocation(this.width + (preferredSize.width - this.nW[j] >> 1), this.height + (preferredSize.height - this.nH[i] >> 1));
                this.width += this.nW[j];
            }
            this.height += this.nH[i];
        }
        super.setSize(this.width, this.height);
        super.addNotify();
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize();
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
}
