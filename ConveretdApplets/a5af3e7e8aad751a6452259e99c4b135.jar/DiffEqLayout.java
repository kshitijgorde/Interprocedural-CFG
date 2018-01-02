import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class DiffEqLayout implements LayoutManager
{
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return new Dimension(500, 500);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(100, 100);
    }
    
    public void layoutContainer(final Container container) {
        final Insets insets = container.insets();
        final int n = container.size().width - insets.left - insets.right;
        final int n2 = (container.size().height - (insets.top + insets.bottom)) * 2 / 3;
        final Component component = container.getComponent(0);
        component.move(insets.left, insets.top);
        component.resize(n, n2);
        int left = insets.left;
        int height = 0;
        final int n3 = n2 + insets.top;
        int i;
        for (i = 1; i != 4; ++i) {
            final Component component2 = container.getComponent(i);
            final Dimension preferredSize = component2.getPreferredSize();
            component2.move(left, n3);
            component2.resize(preferredSize.width, preferredSize.height);
            left += preferredSize.width;
            if (preferredSize.height > height) {
                height = preferredSize.height;
            }
        }
        int n5;
        final int n4 = n5 = n3 + height;
        final int width = container.size().width / 3;
        int left2 = insets.left;
        int n6 = 0;
        while (i < container.getComponentCount()) {
            final Component component3 = container.getComponent(i);
            if (component3.isVisible()) {
                final Dimension preferredSize2 = component3.getPreferredSize();
                preferredSize2.width = width;
                component3.move(left2, n5);
                component3.resize(preferredSize2.width, preferredSize2.height);
                n5 += preferredSize2.height;
            }
            if (++n6 == 5) {
                n6 = 0;
                left2 += width;
                n5 = n4;
            }
            ++i;
        }
    }
}
