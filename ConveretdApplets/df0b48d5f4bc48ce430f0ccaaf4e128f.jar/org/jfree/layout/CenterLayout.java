// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.layout;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Component;
import java.io.Serializable;
import java.awt.LayoutManager;

public class CenterLayout implements LayoutManager, Serializable
{
    private static final long serialVersionUID = 469319532333015042L;
    
    public void addLayoutComponent(final Component comp) {
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
    }
    
    public void layoutContainer(final Container parent) {
        synchronized (parent.getTreeLock()) {
            if (parent.getComponentCount() > 0) {
                final Insets insets = parent.getInsets();
                final Dimension parentSize = parent.getSize();
                final Component component = parent.getComponent(0);
                final Dimension componentSize = component.getPreferredSize();
                final int xx = insets.left + Math.max((parentSize.width - insets.left - insets.right - componentSize.width) / 2, 0);
                final int yy = insets.top + Math.max((parentSize.height - insets.top - insets.bottom - componentSize.height) / 2, 0);
                component.setBounds(xx, yy, componentSize.width, componentSize.height);
            }
        }
        // monitorexit(parent.getTreeLock())
    }
    
    public Dimension minimumLayoutSize(final Container parent) {
        synchronized (parent.getTreeLock()) {
            final Insets insets = parent.getInsets();
            if (parent.getComponentCount() > 0) {
                final Component component = parent.getComponent(0);
                final Dimension d = component.getMinimumSize();
                // monitorexit(parent.getTreeLock())
                return new Dimension(d.width + insets.left + insets.right, d.height + insets.top + insets.bottom);
            }
            // monitorexit(parent.getTreeLock())
            return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
        }
    }
    
    public Dimension preferredLayoutSize(final Container parent) {
        synchronized (parent.getTreeLock()) {
            final Insets insets = parent.getInsets();
            if (parent.getComponentCount() > 0) {
                final Component component = parent.getComponent(0);
                final Dimension d = component.getPreferredSize();
                // monitorexit(parent.getTreeLock())
                return new Dimension((int)d.getWidth() + insets.left + insets.right, (int)d.getHeight() + insets.top + insets.bottom);
            }
            // monitorexit(parent.getTreeLock())
            return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
        }
    }
    
    public void removeLayoutComponent(final Component comp) {
    }
    
    public void removeLayoutComponent(final String name, final Component comp) {
    }
}
