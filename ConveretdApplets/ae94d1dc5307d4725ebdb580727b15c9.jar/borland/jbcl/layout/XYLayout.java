// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.layout;

import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.LayoutManager2;

public class XYLayout implements LayoutManager2
{
    int width;
    int height;
    Hashtable info;
    static final XYConstraints defaultConstraints;
    
    public XYLayout() {
        this.info = new Hashtable();
    }
    
    public XYLayout(final int width, final int height) {
        this.info = new Hashtable();
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf("XYLayout[width=").concat(String.valueOf(this.width))).concat(String.valueOf(",height="))).concat(String.valueOf(this.height))).concat(String.valueOf("]"));
    }
    
    public void addLayoutComponent(final String name, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
        this.info.remove(component);
    }
    
    public Dimension preferredLayoutSize(final Container target) {
        return this.getLayoutSize(target, true);
    }
    
    public Dimension minimumLayoutSize(final Container target) {
        return this.getLayoutSize(target, false);
    }
    
    public void layoutContainer(final Container target) {
        final Insets insets = target.getInsets();
        for (int count = target.getComponentCount(), i = 0; i < count; ++i) {
            final Component component = target.getComponent(i);
            if (component.isVisible()) {
                final Rectangle r = this.getComponentBounds(component, true);
                component.setBounds(insets.left + r.x, insets.top + r.y, r.width, r.height);
            }
        }
    }
    
    public void addLayoutComponent(final Component component, final Object constraints) {
        if (constraints instanceof XYConstraints) {
            this.info.put(component, constraints);
        }
    }
    
    public Dimension maximumLayoutSize(final Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public float getLayoutAlignmentX(final Container target) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container target) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container target) {
    }
    
    Rectangle getComponentBounds(final Component component, final boolean doPreferred) {
        XYConstraints constraints = this.info.get(component);
        if (constraints == null) {
            constraints = XYLayout.defaultConstraints;
        }
        final Rectangle r = new Rectangle(constraints.x, constraints.y, constraints.width, constraints.height);
        if (r.width <= 0 || r.height <= 0) {
            final Dimension d = doPreferred ? component.getPreferredSize() : component.getMinimumSize();
            if (r.width <= 0) {
                r.width = d.width;
            }
            if (r.height <= 0) {
                r.height = d.height;
            }
        }
        return r;
    }
    
    Dimension getLayoutSize(final Container target, final boolean doPreferred) {
        final Dimension dim = new Dimension(0, 0);
        if (this.width <= 0 || this.height <= 0) {
            for (int count = target.getComponentCount(), i = 0; i < count; ++i) {
                final Component component = target.getComponent(i);
                if (component.isVisible()) {
                    final Rectangle r = this.getComponentBounds(component, doPreferred);
                    dim.width = Math.max(dim.width, r.x + r.width);
                    dim.height = Math.max(dim.height, r.y + r.height);
                }
            }
        }
        if (this.width > 0) {
            dim.width = this.width;
        }
        if (this.height > 0) {
            dim.height = this.height;
        }
        final Insets insets = target.getInsets();
        final Dimension dimension = dim;
        dimension.width += insets.left + insets.right;
        final Dimension dimension2 = dim;
        dimension2.height += insets.top + insets.bottom;
        return dim;
    }
    
    static {
        defaultConstraints = new XYConstraints();
    }
}
