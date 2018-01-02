// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.util.Hashtable;
import java.io.Serializable;
import java.awt.LayoutManager2;

public class a implements LayoutManager2, Serializable
{
    private static final long int = 200L;
    int do;
    int if;
    Hashtable for;
    static final b a;
    
    public a() {
        this.for = new Hashtable();
    }
    
    public a(final int do1, final int if1) {
        this.for = new Hashtable();
        this.do = do1;
        this.if = if1;
    }
    
    public int a() {
        return this.do;
    }
    
    public void if(final int do1) {
        this.do = do1;
    }
    
    public int if() {
        return this.if;
    }
    
    public void a(final int if1) {
        this.if = if1;
    }
    
    public String toString() {
        return "XYLayout[width=" + this.do + ",height=" + this.if + "]";
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
        this.for.remove(component);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.a(container, true);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.a(container, false);
    }
    
    public void layoutContainer(final Container container) {
        final Insets insets = container.getInsets();
        for (int componentCount = container.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Rectangle a = this.a(component, true);
                component.setBounds(insets.left + a.x, insets.top + a.y, a.width, a.height);
            }
        }
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (o instanceof b) {
            this.for.put(component, o);
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
    
    Rectangle a(final Component component, final boolean b) {
        b a = this.for.get(component);
        if (a == null) {
            a = a.a.a.a.a.a;
        }
        final Rectangle rectangle = new Rectangle(a.if, a.for, a.do, a.a);
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
    
    Dimension a(final Container container, final boolean b) {
        final Dimension dimension = new Dimension(0, 0);
        if (this.do <= 0 || this.if <= 0) {
            for (int componentCount = container.getComponentCount(), i = 0; i < componentCount; ++i) {
                final Component component = container.getComponent(i);
                if (component.isVisible()) {
                    final Rectangle a = this.a(component, b);
                    dimension.width = Math.max(dimension.width, a.x + a.width);
                    dimension.height = Math.max(dimension.height, a.y + a.height);
                }
            }
        }
        if (this.do > 0) {
            dimension.width = this.do;
        }
        if (this.if > 0) {
            dimension.height = this.if;
        }
        final Insets insets = container.getInsets();
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        return dimension;
    }
    
    static {
        a = new b();
    }
}
