// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;

public class TableauLayout2 extends TableauLayout
{
    public static String RIGHT;
    public static String THEREST;
    protected PaintMenu right;
    
    static {
        TableauLayout2.RIGHT = "RIGHT";
        TableauLayout2.THEREST = "THEREST";
    }
    
    public TableauLayout2() {
        this(200);
    }
    
    public TableauLayout2(final int n) {
        super(n);
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (s.equals(TableauLayout2.RIGHT)) {
            this.right = (PaintMenu)component;
        }
        else if (component == null) {
            this.right = null;
        }
        else {
            super.addLayoutComponent(s, component);
        }
    }
    
    public void layoutContainer(final Container container) {
        super.prefsize = new Dimension(container.getSize());
        Dimension dimension;
        if (this.right != null) {
            dimension = new Dimension(this.right.getPreferredWidth(super.prefsize.height), super.prefsize.height);
        }
        else {
            dimension = new Dimension(0, 0);
        }
        super.fixed.setBounds(0, 0, super.fixedPixels, super.prefsize.height);
        if (this.right != null) {
            this.right.setBounds(super.prefsize.width - dimension.width, 0, dimension.width, super.prefsize.height);
        }
        super.theRest.setBounds(super.fixedPixels, 0, super.prefsize.width - super.fixedPixels - dimension.width, super.prefsize.height);
    }
    
    public void removeLayoutComponent(final Component component) {
        if (super.fixed == component) {
            super.fixed = null;
        }
        else if (this.right == component) {
            this.right = null;
        }
        else {
            super.theRest = null;
        }
    }
}
