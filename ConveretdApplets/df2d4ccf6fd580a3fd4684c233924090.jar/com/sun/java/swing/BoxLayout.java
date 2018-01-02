// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.swing;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.AWTError;
import java.io.PrintStream;
import java.awt.Container;
import java.io.Serializable;
import java.awt.LayoutManager2;

public class BoxLayout implements LayoutManager2, Serializable
{
    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    private int axis;
    private Container target;
    private transient SizeRequirements[] xChildren;
    private transient SizeRequirements[] yChildren;
    private transient SizeRequirements xTotal;
    private transient SizeRequirements yTotal;
    private transient PrintStream dbg;
    
    public BoxLayout(final Container target, final int axis) {
        if (axis != 0 && axis != 1) {
            throw new AWTError("Invalid axis");
        }
        this.axis = axis;
        this.target = target;
    }
    
    BoxLayout(final Container target, final int axis, final PrintStream dbg) {
        this(target, axis);
        this.dbg = dbg;
    }
    
    public void addLayoutComponent(final Component comp, final Object constraints) {
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
    }
    
    void checkContainer(final Container target) {
        if (this.target != target) {
            throw new AWTError("BoxLayout can't be shared");
        }
    }
    
    void checkRequests() {
        if (this.xChildren == null || this.yChildren == null) {
            final int n = this.target.getComponentCount();
            this.xChildren = new SizeRequirements[n];
            this.yChildren = new SizeRequirements[n];
            for (int i = 0; i < n; ++i) {
                final Component c = this.target.getComponent(i);
                final Dimension min = c.getMinimumSize();
                final Dimension typ = c.getPreferredSize();
                final Dimension max = c.getMaximumSize();
                this.xChildren[i] = new SizeRequirements(min.width, typ.width, max.width, c.getAlignmentX());
                this.yChildren[i] = new SizeRequirements(min.height, typ.height, max.height, c.getAlignmentY());
            }
            if (this.axis == 0) {
                this.xTotal = SizeRequirements.getTiledSizeRequirements(this.xChildren);
                this.yTotal = SizeRequirements.getAlignedSizeRequirements(this.yChildren);
            }
            else {
                this.xTotal = SizeRequirements.getAlignedSizeRequirements(this.xChildren);
                this.yTotal = SizeRequirements.getTiledSizeRequirements(this.yChildren);
            }
        }
    }
    
    public float getLayoutAlignmentX(final Container target) {
        this.checkContainer(target);
        this.checkRequests();
        return this.xTotal.alignment;
    }
    
    public float getLayoutAlignmentY(final Container target) {
        this.checkContainer(target);
        this.checkRequests();
        return this.yTotal.alignment;
    }
    
    public void invalidateLayout(final Container target) {
        this.checkContainer(target);
        this.xChildren = null;
        this.yChildren = null;
        this.xTotal = null;
        this.yTotal = null;
    }
    
    public void layoutContainer(final Container target) {
        this.checkContainer(target);
        this.checkRequests();
        final int nChildren = target.getComponentCount();
        final int[] xOffsets = new int[nChildren];
        final int[] xSpans = new int[nChildren];
        final int[] yOffsets = new int[nChildren];
        final int[] ySpans = new int[nChildren];
        final Dimension alloc = target.getSize();
        final Insets in = target.getInsets();
        final Dimension dimension = alloc;
        dimension.width -= in.left + in.right;
        final Dimension dimension2 = alloc;
        dimension2.height -= in.top + in.bottom;
        if (this.axis == 0) {
            SizeRequirements.calculateTiledPositions(alloc.width, this.xTotal, this.xChildren, xOffsets, xSpans);
            SizeRequirements.calculateAlignedPositions(alloc.height, this.yTotal, this.yChildren, yOffsets, ySpans);
        }
        else {
            SizeRequirements.calculateAlignedPositions(alloc.width, this.xTotal, this.xChildren, xOffsets, xSpans);
            SizeRequirements.calculateTiledPositions(alloc.height, this.yTotal, this.yChildren, yOffsets, ySpans);
        }
        for (int i = 0; i < nChildren; ++i) {
            final Component c = target.getComponent(i);
            c.setBounds((int)Math.min(in.left + xOffsets[i], 2147483647L), (int)Math.min(in.top + yOffsets[i], 2147483647L), xSpans[i], ySpans[i]);
        }
        if (this.dbg != null) {
            for (int i = 0; i < nChildren; ++i) {
                final Component c = target.getComponent(i);
                this.dbg.println(c.toString());
                this.dbg.println("X: " + this.xChildren[i]);
                this.dbg.println("Y: " + this.yChildren[i]);
            }
        }
    }
    
    public Dimension maximumLayoutSize(final Container target) {
        this.checkContainer(target);
        this.checkRequests();
        final Dimension size = new Dimension(this.xTotal.maximum, this.yTotal.maximum);
        final Insets insets = target.getInsets();
        size.width = (int)Math.min(size.width + insets.left + insets.right, 2147483647L);
        size.height = (int)Math.min(size.height + insets.top + insets.bottom, 2147483647L);
        return size;
    }
    
    public Dimension minimumLayoutSize(final Container target) {
        this.checkContainer(target);
        this.checkRequests();
        final Dimension size = new Dimension(this.xTotal.minimum, this.yTotal.minimum);
        final Insets insets = target.getInsets();
        size.width = (int)Math.min(size.width + insets.left + insets.right, 2147483647L);
        size.height = (int)Math.min(size.height + insets.top + insets.bottom, 2147483647L);
        return size;
    }
    
    public Dimension preferredLayoutSize(final Container target) {
        this.checkContainer(target);
        this.checkRequests();
        final Dimension size = new Dimension(this.xTotal.preferred, this.yTotal.preferred);
        final Insets insets = target.getInsets();
        size.width = (int)Math.min(size.width + insets.left + insets.right, 2147483647L);
        size.height = (int)Math.min(size.height + insets.top + insets.bottom, 2147483647L);
        return size;
    }
    
    public void removeLayoutComponent(final Component comp) {
    }
}
