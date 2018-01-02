// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.layout;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.io.Serializable;
import java.awt.LayoutManager;

public class CenterLayout implements LayoutManager, Serializable
{
    public Dimension preferredLayoutSize(final Container container) {
        Dimension preferredSize = null;
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            if (container.getComponentCount() > 0) {
                preferredSize = container.getComponent(0).getPreferredSize();
            }
            return new Dimension((int)preferredSize.getWidth() + insets.left + insets.right, (int)preferredSize.getHeight() + insets.top + insets.bottom);
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            if (container.getComponentCount() > 0) {
                final Dimension minimumSize = container.getComponent(0).getMinimumSize();
                return new Dimension(minimumSize.width + insets.left + insets.right, minimumSize.height + insets.top + insets.bottom);
            }
            return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
        }
    }
    
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            if (container.getComponentCount() > 0) {
                final Insets insets = container.getInsets();
                final Dimension size = container.getSize();
                final Component component = container.getComponent(0);
                final Dimension preferredSize = component.getPreferredSize();
                component.setBounds(insets.left + Math.max((size.width - insets.left - insets.right - preferredSize.width) / 2, 0), insets.top + Math.max((size.height - insets.top - insets.bottom - preferredSize.height) / 2, 0), preferredSize.width, preferredSize.height);
            }
        }
    }
    
    public void addLayoutComponent(final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final String s, final Component component) {
    }
}
