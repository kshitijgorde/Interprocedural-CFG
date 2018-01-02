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
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
        this.info.remove(component);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.getLayoutSize(container, true);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.getLayoutSize(container, false);
    }
    
    public void layoutContainer(final Container container) {
        final Insets insets = container.getInsets();
        for (int componentCount = container.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Rectangle componentBounds = this.getComponentBounds(component, true);
                component.setBounds(insets.left + componentBounds.x, insets.top + componentBounds.y, componentBounds.width, componentBounds.height);
            }
        }
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (o instanceof XYConstraints) {
            this.info.put(component, o);
        }
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
    }
    
    Rectangle getComponentBounds(final Component component, final boolean b) {
        XYConstraints defaultConstraints = this.info.get(component);
        if (defaultConstraints == null) {
            defaultConstraints = XYLayout.defaultConstraints;
        }
        final Rectangle rectangle = new Rectangle(defaultConstraints.x, defaultConstraints.y, defaultConstraints.width, defaultConstraints.height);
        if (rectangle.width <= 0 || rectangle.height <= 0) {
            final Dimension dimension = b ? component.getPreferredSize() : component.getMinimumSize();
            if (rectangle.width <= 0) {
                rectangle.width = dimension.width;
            }
            if (rectangle.height <= 0) {
                rectangle.height = dimension.height;
            }
        }
        return rectangle;
    }
    
    Dimension getLayoutSize(final Container container, final boolean b) {
        final Dimension dimension = new Dimension(0, 0);
        if (this.width <= 0 || this.height <= 0) {
            for (int componentCount = container.getComponentCount(), i = 0; i < componentCount; ++i) {
                final Component component = container.getComponent(i);
                if (component.isVisible()) {
                    final Rectangle componentBounds = this.getComponentBounds(component, b);
                    dimension.width = Math.max(dimension.width, componentBounds.x + componentBounds.width);
                    dimension.height = Math.max(dimension.height, componentBounds.y + componentBounds.height);
                }
            }
        }
        if (this.width > 0) {
            dimension.width = this.width;
        }
        if (this.height > 0) {
            dimension.height = this.height;
        }
        final Insets insets = container.getInsets();
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        return dimension;
    }
    
    static {
        defaultConstraints = new XYConstraints();
    }
}
