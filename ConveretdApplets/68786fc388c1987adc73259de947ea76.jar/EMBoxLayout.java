import java.awt.Insets;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class EMBoxLayout implements LayoutManager
{
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return new Dimension(500, 550);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(100, 100);
    }
    
    public void layoutContainer(final Container container) {
        int width = 0;
        for (int i = 1; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize = component.getPreferredSize();
                if (preferredSize.width > width) {
                    width = preferredSize.width;
                }
            }
        }
        final Insets insets = container.insets();
        final int n = container.size().width - insets.left - insets.right - width;
        final int n2 = container.size().height - (insets.top + insets.bottom);
        container.getComponent(0).move(insets.left, insets.top);
        container.getComponent(0).resize(n, n2);
        final int n3 = n + insets.left;
        int top = insets.top;
        for (int j = 1; j < container.getComponentCount(); ++j) {
            final Component component2 = container.getComponent(j);
            if (component2.isVisible()) {
                final Dimension preferredSize2 = component2.getPreferredSize();
                if (component2 instanceof Scrollbar) {
                    preferredSize2.width = width;
                }
                if (component2 instanceof Label) {
                    top += preferredSize2.height / 5;
                    preferredSize2.width = width;
                }
                component2.move(n3, top);
                component2.resize(preferredSize2.width, preferredSize2.height);
                top += preferredSize2.height;
            }
        }
    }
}
