// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Component;
import java.util.HashSet;
import java.awt.LayoutManager;

public class gv implements LayoutManager
{
    int a;
    private HashSet b;
    private HashSet c;
    private HashSet d;
    
    public gv() {
        this(0);
    }
    
    public gv(final int a) {
        this.a = a;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if ("CENTER".equals(s)) {
            if (this.b == null) {
                this.b = new HashSet();
            }
            this.b.add(component);
        }
        else if ("RIGHT".equals(s)) {
            if (this.c == null) {
                this.c = new HashSet();
            }
            this.c.add(component);
        }
        else if ("JUSTIFY".equals(s)) {
            if (this.d == null) {
                this.d = new HashSet();
            }
            this.d.add(component);
        }
    }
    
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            if (componentCount == 0) {
                // monitorexit(container.getTreeLock())
                return;
            }
            final int width = container.getSize().width;
            int right = insets.right;
            int top = insets.top;
            for (int i = 0; i < componentCount; ++i) {
                final Component component = container.getComponent(i);
                final Dimension minimumSize = component.getMinimumSize();
                int width2 = minimumSize.width;
                final int height = minimumSize.height;
                if (this.a(component)) {
                    right = (width - minimumSize.width) / 2;
                }
                else if (this.b(component)) {
                    right = width - minimumSize.width;
                }
                else if (this.c(component)) {
                    width2 = width;
                }
                component.setBounds(right, top, width2, height);
                top = top + height + this.a;
            }
        }
        // monitorexit(container.getTreeLock())
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            int width = 0;
            int n = 0;
            final int componentCount = container.getComponentCount();
            for (int i = 0; i < componentCount; ++i) {
                final Dimension preferredSize = container.getComponent(i).getPreferredSize();
                n += preferredSize.height;
                if (preferredSize.width > width) {
                    width = preferredSize.width;
                }
            }
            // monitorexit(container.getTreeLock())
            return new Dimension(insets.left + insets.right + width, insets.top + insets.bottom + n + (componentCount - 1) * this.a);
        }
    }
    
    public void removeLayoutComponent(final Component component) {
        if (this.b != null) {
            this.b.remove(component);
        }
        if (this.c != null) {
            this.c.remove(component);
        }
    }
    
    private boolean a(final Component component) {
        return this.b != null && this.b.contains(component);
    }
    
    private boolean b(final Component component) {
        return this.c != null && this.c.contains(component);
    }
    
    private boolean c(final Component component) {
        return this.d != null && this.d.contains(component);
    }
}
