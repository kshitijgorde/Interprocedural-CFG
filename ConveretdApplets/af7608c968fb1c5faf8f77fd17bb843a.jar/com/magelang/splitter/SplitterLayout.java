// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.util.Hashtable;
import java.io.Serializable;
import java.awt.LayoutManager2;

public class SplitterLayout implements LayoutManager2, Serializable
{
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public static SplitterBar dragee;
    private int lastW;
    private int lastH;
    private boolean newComponentAdded;
    private Hashtable relations;
    private static final long serialVersionUID = -8658291919501921765L;
    private int fieldOrientation;
    
    public SplitterLayout(final int orientation) {
        this.lastW = -1;
        this.lastH = -1;
        this.newComponentAdded = false;
        this.relations = null;
        this.fieldOrientation = 0;
        this.setOrientation(orientation);
        this.relations = new Hashtable();
    }
    
    public int getOrientation() {
        return this.fieldOrientation;
    }
    
    public final void removeLayoutComponent(final Component component) {
        this.relations.remove(component);
        this.newComponentAdded = true;
    }
    
    public final Dimension preferredLayoutSize(final Container container) {
        return this.checkLayoutSize(container, true);
    }
    
    public final void addLayoutComponent(final Component component, Object o) {
        if (o == null) {
            o = "1";
        }
        if (o instanceof Integer) {
            this.relations.put(component, o);
            this.newComponentAdded = true;
        }
        else {
            this.addLayoutComponent((String)o, component);
        }
    }
    
    public final float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public void setOrientation(final int fieldOrientation) {
        this.fieldOrientation = fieldOrientation;
    }
    
    public SplitterLayout() {
        this(0);
    }
    
    public final float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    static {
        SplitterLayout.dragee = null;
    }
    
    public final void addLayoutComponent(String s, final Component component) {
        this.newComponentAdded = true;
        if (component instanceof SplitterBar) {
            ((SplitterBar)component).setOrientation(this.getOrientation());
        }
        else {
            if (s == null) {
                s = "1";
            }
            try {
                this.relations.put(component, Integer.decode(s));
            }
            catch (NumberFormatException ex) {
                this.relations.put(component, new Integer(1));
            }
        }
    }
    
    public final Dimension minimumLayoutSize(final Container container) {
        return this.checkLayoutSize(container, true);
    }
    
    public final Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public final void invalidateLayout(final Container container) {
    }
    
    public final String toString() {
        if (this.getOrientation() == 0) {
            return String.valueOf(this.getClass().getName()) + "[orientation=VERTICAL]";
        }
        return String.valueOf(this.getClass().getName()) + "[orientation=HORIZONTAL]";
    }
    
    public void swapOrientation(final Container container) {
        this.setOrientation((this.getOrientation() != 1) ? 1 : 0);
        final Component[] components = container.getComponents();
        for (int i = container.getComponentCount() - 1; i > -1; --i) {
            if (components[i] instanceof SplitterBar) {
                ((SplitterBar)components[i]).swapOrientation();
            }
            components[i].invalidate();
        }
        this.newComponentAdded = true;
        container.validate();
    }
    
    public final void layoutContainer(final Container container) {
        final Insets insets = container.getInsets();
        final Dimension size = container.getSize();
        int n = insets.top;
        final int n2 = size.height - insets.bottom;
        int n3 = insets.left;
        final int n4 = size.width - insets.right;
        boolean b = false;
        boolean b2 = false;
        float n5 = 0.0f;
        float n6 = 0.0f;
        if (this.lastW == -1) {
            this.lastW = size.width;
            this.lastH = size.height;
        }
        else {
            if (this.lastW != size.width) {
                b = true;
                n5 = size.width / this.lastW;
                this.lastW = size.width;
            }
            if (this.lastH != size.height) {
                b2 = true;
                n6 = size.height / this.lastH;
                this.lastH = size.height;
            }
        }
        size.width = n4 - n3;
        size.height = n2 - n;
        int n7 = 0;
        int n8 = 0;
        final Component[] components = container.getComponents();
        final Object[] array = new Object[components.length];
        final int orientation = this.getOrientation();
        for (int i = 0; i < components.length; ++i) {
            if (components[i].isVisible()) {
                if (components[i] instanceof SplitterBar) {
                    ((SplitterBar)components[i]).setOrientation(orientation);
                    array[i] = components[i].getPreferredSize();
                    if (orientation == 0) {
                        final Dimension dimension = size;
                        dimension.height -= ((Dimension)array[i]).height;
                        if (b2) {
                            final Point location = components[i].getLocation();
                            components[i].setLocation(location.x, (int)(location.y * n6));
                        }
                    }
                    else {
                        final Dimension dimension2 = size;
                        dimension2.width -= ((Dimension)array[i]).width;
                        if (b) {
                            final Point location2 = components[i].getLocation();
                            components[i].setLocation((int)(location2.x * n5), location2.y);
                        }
                    }
                }
                else {
                    array[i] = this.relations.get(components[i]);
                    n7 += (int)array[i];
                    ++n8;
                }
            }
        }
        for (int j = 0; j < components.length; ++j) {
            if (components[j].isVisible()) {
                final Rectangle bounds = components[j].getBounds();
                if (components[j] instanceof SplitterBar) {
                    if (orientation == 0) {
                        if (bounds.x != n3 || bounds.y != n || bounds.width != size.width || bounds.height != ((Dimension)array[j]).height) {
                            components[j].setBounds(n3, n, size.width, ((Dimension)array[j]).height);
                        }
                        n += ((Dimension)array[j]).height;
                    }
                    else {
                        if (bounds.x != n3 || bounds.y != n || bounds.height != size.height || bounds.width != ((Dimension)array[j]).width) {
                            components[j].setBounds(n3, n, ((Dimension)array[j]).width, size.height);
                        }
                        n3 += ((Dimension)array[j]).width;
                    }
                }
                else if (j == components.length - 1) {
                    if (orientation == 0) {
                        if (bounds.x != n3 || bounds.y != n || bounds.width != size.width || bounds.height != n2 - n) {
                            components[j].setBounds(n3, n, size.width, n2 - n);
                        }
                    }
                    else if (bounds.x != n3 || bounds.y != n || bounds.width != n4 - n3 || bounds.height != size.height) {
                        components[j].setBounds(n3, n, n4 - n3, size.height);
                    }
                }
                else {
                    final Point location3 = components[j + 1].getLocation();
                    if (!this.newComponentAdded && components[j + 1] instanceof SplitterBar && (location3.x != 0 || location3.y != 0)) {
                        if (orientation == 0) {
                            if (bounds.x != n3 || bounds.y != n || bounds.width != size.width || bounds.height != location3.y - n) {
                                components[j].setBounds(n3, n, size.width, location3.y - n);
                            }
                            n = location3.y;
                        }
                        else {
                            if (bounds.x != n3 || bounds.y != n || bounds.width != location3.x - n3 || bounds.height != size.height) {
                                components[j].setBounds(n3, n, location3.x - n3, size.height);
                            }
                            n3 = location3.x;
                        }
                    }
                    else {
                        final float n9 = (int)array[j] / n7;
                        if (orientation == 0) {
                            final float n10 = n9 * size.height;
                            if (bounds.x != n3 || bounds.y != n || bounds.width != size.width || bounds.height != (int)n10) {
                                components[j].setBounds(n3, n, size.width, (int)n10);
                            }
                            n += (int)n10;
                        }
                        else {
                            final float n11 = n9 * size.width;
                            if (bounds.x != n3 || bounds.y != n || bounds.width != (int)n11 || bounds.height != size.height) {
                                components[j].setBounds(n3, n, (int)n11, size.height);
                            }
                            n3 += (int)n11;
                        }
                    }
                }
            }
        }
        this.newComponentAdded = false;
    }
    
    public final Dimension checkLayoutSize(final Container container, final boolean b) {
        final Dimension dimension = new Dimension(0, 0);
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i].isVisible()) {
                Dimension dimension2;
                if (b || components[i] instanceof SplitterBar) {
                    dimension2 = components[i].getPreferredSize();
                }
                else {
                    dimension2 = components[i].getMinimumSize();
                }
                if (this.getOrientation() == 0) {
                    dimension.width = Math.max(dimension2.width, dimension.width);
                    final Dimension dimension3 = dimension;
                    dimension3.height += dimension2.height;
                }
                else {
                    dimension.height = Math.max(dimension2.height, dimension.height);
                    final Dimension dimension4 = dimension;
                    dimension4.width += dimension2.width;
                }
            }
        }
        final Insets insets = container.getInsets();
        final Dimension dimension5 = dimension;
        dimension5.width += insets.left + insets.right;
        final Dimension dimension6 = dimension;
        dimension6.height += insets.top + insets.bottom;
        return dimension;
    }
}
