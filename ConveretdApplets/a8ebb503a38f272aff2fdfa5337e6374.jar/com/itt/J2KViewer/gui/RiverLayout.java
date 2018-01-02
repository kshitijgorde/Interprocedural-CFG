// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.awt.Insets;
import java.util.Map;
import java.io.Serializable;
import java.awt.LayoutManager;
import java.awt.FlowLayout;

public class RiverLayout extends FlowLayout implements LayoutManager, Serializable
{
    public static final String LINE_BREAK = "br";
    public static final String PARAGRAPH_BREAK = "p";
    public static final String TAB_STOP = "tab";
    public static final String HFILL = "hfill";
    public static final String VFILL = "vfill";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String CENTER = "center";
    public static final String VTOP = "vtop";
    public static final String VCENTER = "vcenter";
    Map constraints;
    String valign;
    int hgap;
    int vgap;
    Insets extraInsets;
    Insets totalInsets;
    
    public RiverLayout() {
        this(10, 5);
    }
    
    public RiverLayout(final int hgap, final int vgap) {
        this.constraints = new HashMap();
        this.valign = "vcenter";
        this.totalInsets = new Insets(0, 0, 0, 0);
        this.hgap = hgap;
        this.vgap = vgap;
        this.setExtraInsets(new Insets(0, hgap, hgap, hgap));
    }
    
    public int getHgap() {
        return this.hgap;
    }
    
    public void setHgap(final int hgap) {
        this.hgap = hgap;
    }
    
    public int getVgap() {
        return this.vgap;
    }
    
    public Insets getExtraInsets() {
        return this.extraInsets;
    }
    
    public void setExtraInsets(final Insets extraInsets) {
        this.extraInsets = extraInsets;
    }
    
    protected Insets getInsets(final Container container) {
        final Insets insets = container.getInsets();
        this.totalInsets.top = insets.top + this.extraInsets.top;
        this.totalInsets.left = insets.left + this.extraInsets.left;
        this.totalInsets.bottom = insets.bottom + this.extraInsets.bottom;
        this.totalInsets.right = insets.right + this.extraInsets.right;
        return this.totalInsets;
    }
    
    public void setVgap(final int vgap) {
        this.vgap = vgap;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        this.constraints.put(component, s);
    }
    
    public void removeLayoutComponent(final Component component) {
        this.constraints.remove(component);
    }
    
    boolean isFirstInRow(final Component component) {
        final String s = this.constraints.get(component);
        return s != null && (s.indexOf("br") != -1 || s.indexOf("p") != -1);
    }
    
    boolean hasHfill(final Component component) {
        return this.hasConstraint(component, "hfill");
    }
    
    boolean hasVfill(final Component component) {
        return this.hasConstraint(component, "vfill");
    }
    
    boolean hasConstraint(final Component component, final String s) {
        final String s2 = this.constraints.get(component);
        if (s2 == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        while (stringTokenizer.hasMoreTokens()) {
            if (stringTokenizer.nextToken().equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    protected Ruler calcTabs(final Container container) {
        final Ruler ruler = new Ruler();
        final int componentCount = container.getComponentCount();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < componentCount; ++i) {
            final Component component = container.getComponent(i);
            int tab;
            if (this.isFirstInRow(component) || i == 0) {
                tab = 0;
                n2 = 0;
            }
            else {
                tab = n + this.hgap;
            }
            if (this.hasConstraint(component, "tab")) {
                ruler.setTab(n2, tab);
                tab = ruler.getTab(n2++);
            }
            n = tab + component.getPreferredSize().width;
        }
        return ruler;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final Dimension dimension = new Dimension(0, 0);
            Dimension dimension2 = new Dimension(0, 0);
            final int componentCount = container.getComponentCount();
            int n = 1;
            int n2 = 0;
            final Ruler calcTabs = this.calcTabs(container);
            for (int i = 0; i < componentCount; ++i) {
                final Component component = container.getComponent(i);
                if (this.isFirstInRow(component)) {
                    n2 = 0;
                    dimension.width = Math.max(dimension.width, dimension2.width);
                    final Dimension dimension3 = dimension;
                    dimension3.height += dimension2.height + this.vgap;
                    if (this.hasConstraint(component, "p")) {
                        final Dimension dimension4 = dimension;
                        dimension4.height += 2 * this.vgap;
                    }
                    dimension2 = new Dimension(0, 0);
                }
                if (this.hasConstraint(component, "tab")) {
                    dimension2.width = calcTabs.getTab(n2++);
                }
                final Dimension preferredSize = component.getPreferredSize();
                dimension2.height = Math.max(dimension2.height, preferredSize.height);
                if (n != 0) {
                    n = 0;
                }
                else {
                    final Dimension dimension5 = dimension2;
                    dimension5.width += this.hgap;
                }
                final Dimension dimension6 = dimension2;
                dimension6.width += preferredSize.width;
            }
            dimension.width = Math.max(dimension.width, dimension2.width);
            final Dimension dimension7 = dimension;
            dimension7.height += dimension2.height;
            final Insets insets = this.getInsets(container);
            final Dimension dimension8 = dimension;
            dimension8.width += insets.left + insets.right;
            final Dimension dimension9 = dimension;
            dimension9.height += insets.top + insets.bottom;
            return dimension;
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final Dimension dimension = new Dimension(0, 0);
            Dimension dimension2 = new Dimension(0, 0);
            final int componentCount = container.getComponentCount();
            int n = 1;
            int n2 = 0;
            final Ruler calcTabs = this.calcTabs(container);
            for (int i = 0; i < componentCount; ++i) {
                final Component component = container.getComponent(i);
                if (this.isFirstInRow(component)) {
                    n2 = 0;
                    dimension.width = Math.max(dimension.width, dimension2.width);
                    final Dimension dimension3 = dimension;
                    dimension3.height += dimension2.height + this.vgap;
                    if (this.hasConstraint(component, "p")) {
                        final Dimension dimension4 = dimension;
                        dimension4.height += 2 * this.vgap;
                    }
                    dimension2 = new Dimension(0, 0);
                }
                if (this.hasConstraint(component, "tab")) {
                    dimension2.width = calcTabs.getTab(n2++);
                }
                final Dimension minimumSize = component.getMinimumSize();
                dimension2.height = Math.max(dimension2.height, minimumSize.height);
                if (n != 0) {
                    n = 0;
                }
                else {
                    final Dimension dimension5 = dimension2;
                    dimension5.width += this.hgap;
                }
                final Dimension dimension6 = dimension2;
                dimension6.width += minimumSize.width;
            }
            dimension.width = Math.max(dimension.width, dimension2.width);
            final Dimension dimension7 = dimension;
            dimension7.height += dimension2.height;
            final Insets insets = this.getInsets(container);
            final Dimension dimension8 = dimension;
            dimension8.width += insets.left + insets.right;
            final Dimension dimension9 = dimension;
            dimension9.height += insets.top + insets.bottom;
            return dimension;
        }
    }
    
    protected void moveComponents(final Container container, int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final Ruler ruler) {
        synchronized (container.getTreeLock()) {
            switch (this.getAlignment()) {
                case 0: {
                    n += (b ? 0 : n3);
                    break;
                }
                case 1: {
                    n += n3 / 2;
                    break;
                }
                case 2: {
                    n += (b ? n3 : 0);
                }
                case 4: {
                    n += n3;
                    break;
                }
            }
            int n7 = 0;
            for (int i = n5; i < n6; ++i) {
                final Component component = container.getComponent(i);
                if (this.hasConstraint(component, "tab")) {
                    n = this.getInsets(container).left + ruler.getTab(n7++);
                }
                final int n8 = (this.valign == "vtop") ? 0 : ((n4 - component.getHeight()) / 2);
                if (b) {
                    component.setLocation(n, n2 + n8);
                }
                else {
                    component.setLocation(container.getWidth() - n - component.getWidth(), n2 + n8);
                }
                n += component.getWidth() + this.hgap;
            }
        }
    }
    
    protected void relMove(final Container container, final int n, final int n2, final int n3, final int n4) {
        synchronized (container.getTreeLock()) {
            for (int i = n3; i < n4; ++i) {
                final Component component = container.getComponent(i);
                component.setLocation(component.getX() + n, component.getY() + n2);
            }
        }
    }
    
    protected void adjustAlignment(final Component component) {
        if (this.hasConstraint(component, "left")) {
            this.setAlignment(0);
        }
        else if (this.hasConstraint(component, "right")) {
            this.setAlignment(2);
        }
        else if (this.hasConstraint(component, "center")) {
            this.setAlignment(1);
        }
        if (this.hasConstraint(component, "vtop")) {
            this.valign = "vtop";
        }
        else if (this.hasConstraint(component, "vcenter")) {
            this.valign = "vcenter";
        }
    }
    
    public void layoutContainer(final Container container) {
        this.setAlignment(0);
        synchronized (container.getTreeLock()) {
            final Insets insets = this.getInsets(container);
            final int n = container.getWidth() - (insets.left + insets.right);
            final int n2 = container.getHeight() - (insets.top + insets.bottom);
            final int componentCount = container.getComponentCount();
            int n3 = 0;
            int n4 = insets.top + this.vgap;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            final boolean leftToRight = container.getComponentOrientation().isLeftToRight();
            Component component = null;
            Component component2 = null;
            final Ruler calcTabs = this.calcTabs(container);
            int n8 = 0;
            for (int i = 0; i < componentCount; ++i) {
                final Component component3 = container.getComponent(i);
                final Dimension preferredSize = component3.getPreferredSize();
                component3.setSize(preferredSize.width, preferredSize.height);
                if (this.isFirstInRow(component3)) {
                    n8 = 0;
                }
                if (this.hasConstraint(component3, "tab")) {
                    n3 = calcTabs.getTab(n8++);
                }
                if (!this.isFirstInRow(component3)) {
                    if (i > 0 && !this.hasConstraint(component3, "tab")) {
                        n3 += this.hgap;
                    }
                    n3 += preferredSize.width;
                    n5 = Math.max(n5, preferredSize.height);
                }
                else {
                    if (component2 != null && n7 == 0) {
                        n7 = i;
                    }
                    if (component != null) {
                        component.setSize(component.getWidth() + n - n3, component.getHeight());
                        n3 = n;
                    }
                    this.moveComponents(container, insets.left, n4, n - n3, n5, n6, i, leftToRight, calcTabs);
                    n3 = preferredSize.width;
                    n4 += this.vgap + n5;
                    if (this.hasConstraint(component3, "p")) {
                        n4 += 2 * this.vgap;
                    }
                    n5 = preferredSize.height;
                    n6 = i;
                    component = null;
                }
                if (this.hasHfill(component3)) {
                    component = component3;
                }
                if (this.hasVfill(component3)) {
                    component2 = component3;
                }
                this.adjustAlignment(component3);
            }
            if (component2 != null && n7 == 0) {
                n7 = componentCount;
            }
            if (component != null) {
                component.setSize(component.getWidth() + n - n3, component.getHeight());
                n3 = n;
            }
            this.moveComponents(container, insets.left, n4, n - n3, n5, n6, componentCount, leftToRight, calcTabs);
            final int n9 = n2 - (n4 + n5);
            if (n9 != 0 && component2 != null) {
                component2.setSize(component2.getWidth(), n9 + component2.getHeight());
                this.relMove(container, 0, n9, n7, componentCount);
            }
        }
    }
}
