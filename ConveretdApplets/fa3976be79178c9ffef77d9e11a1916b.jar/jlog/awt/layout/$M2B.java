// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.layout;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class $M2B implements LayoutManager
{
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void layoutContainer(final Container container) {
        final Component[] components = container.getComponents();
        int i = components.length;
        if (i == 0) {
            return;
        }
        final Dimension size = container.getSize();
        final Insets insets = container.getInsets();
        size.width = size.width - insets.left - insets.right;
        size.height = size.height - insets.top - insets.bottom;
        final Rectangle bounds = new Rectangle(0, 0, size.width, size.height);
        while (i > 0) {
            --i;
            components[i].setBounds(bounds);
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        final Dimension dimension = new Dimension(-1, -1);
        final Component[] components = container.getComponents();
        int i = components.length;
        while (i > 0) {
            --i;
            final Dimension minimumSize = components[i].getMinimumSize();
            if (minimumSize != null) {
                dimension.width = Math.max(dimension.width, minimumSize.width);
                dimension.height = Math.max(dimension.height, minimumSize.height);
            }
        }
        return dimension;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Dimension dimension = new Dimension(-1, -1);
        final Component[] components = container.getComponents();
        int i = components.length;
        while (i > 0) {
            --i;
            final Dimension preferredSize = components[i].getPreferredSize();
            if (preferredSize != null) {
                dimension.width = Math.max(dimension.width, preferredSize.width);
                dimension.height = Math.max(dimension.height, preferredSize.height);
            }
        }
        return dimension;
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
