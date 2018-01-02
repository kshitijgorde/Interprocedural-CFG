// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager2;

public class TableauLayout implements LayoutManager2
{
    public static String FIXED;
    public static String THEREST;
    protected int fixedPixels;
    protected Component fixed;
    protected Component theRest;
    protected Dimension prefsize;
    
    static {
        TableauLayout.FIXED = "FIXED";
        TableauLayout.THEREST = "THEREST";
    }
    
    public TableauLayout() {
        this(200);
    }
    
    public TableauLayout(final int fixedPixels) {
        this.prefsize = new Dimension(0, 0);
        this.fixedPixels = fixedPixels;
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (o == null || o instanceof String) {
            this.addLayoutComponent((String)o, component);
            return;
        }
        throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (s.equals(TableauLayout.FIXED)) {
            this.fixed = component;
        }
        else {
            this.theRest = component;
        }
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
    }
    
    public void layoutContainer(final Container container) {
        this.prefsize = new Dimension(container.getSize());
        this.fixed.setBounds(0, 0, this.fixedPixels, this.prefsize.height);
        this.theRest.setBounds(this.fixedPixels, 0, this.prefsize.width - this.fixedPixels, this.prefsize.height);
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.prefsize = new Dimension(container.getSize());
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.prefsize = new Dimension(container.getSize());
    }
    
    public void removeLayoutComponent(final Component component) {
        if (this.fixed == component) {
            this.fixed = null;
        }
        else {
            this.theRest = null;
        }
    }
    
    public void setFixedWidth(final int fixedPixels) {
        this.fixedPixels = fixedPixels;
    }
}
