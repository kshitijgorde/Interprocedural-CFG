// 
// Decompiled by Procyon v0.5.30
// 

package a.a.b.a;

import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.util.Hashtable;
import java.io.Serializable;
import java.awt.LayoutManager2;

public class b implements LayoutManager2, Serializable
{
    static final long if = -1919857869177070440L;
    protected Hashtable a;
    
    public b() {
        this.a = new Hashtable();
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        throw new IllegalArgumentException();
    }
    
    public void removeLayoutComponent(final Component component) {
        this.a.remove(component);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        int n = 0;
        int n2 = 0;
        final Enumeration<Component> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final Component component = keys.nextElement();
            final a a = this.a.get(component);
            final Dimension preferredSize = component.getPreferredSize();
            int n3 = a.if();
            if (n3 == -1) {
                n3 = preferredSize.width;
            }
            int n4 = a.for();
            if (n4 == -1) {
                n4 = preferredSize.height;
            }
            if (a.if + n3 > n) {
                n = a.if + n3;
            }
            if (a.for + n4 > n2) {
                n2 = a.for + n4;
            }
        }
        return new Dimension(n, n2);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        int n = 0;
        int n2 = 0;
        final Enumeration<Component> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final Component component = keys.nextElement();
            final a a = this.a.get(component);
            final Dimension minimumSize = component.getMinimumSize();
            int n3 = a.if();
            if (n3 == -1) {
                n3 = minimumSize.width;
            }
            int n4 = a.for();
            if (n4 == -1) {
                n4 = minimumSize.height;
            }
            if (a.if + n3 > n) {
                n = a.if + n3;
            }
            if (a.for + n4 > n2) {
                n2 = a.for + n4;
            }
        }
        return new Dimension(n, n2);
    }
    
    public void layoutContainer(final Container container) {
        final Enumeration<Component> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final Component component = keys.nextElement();
            final a a = this.a.get(component);
            final Dimension preferredSize = component.getPreferredSize();
            int n = a.if();
            if (n == -1) {
                n = preferredSize.width;
            }
            int n2 = a.for();
            if (n2 == -1) {
                n2 = preferredSize.height;
            }
            component.setBounds(a.if, a.for, n, n2);
        }
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (!(o instanceof a)) {
            throw new IllegalArgumentException();
        }
        this.a.put(component, o);
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.0f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.0f;
    }
    
    public void invalidateLayout(final Container container) {
    }
}
