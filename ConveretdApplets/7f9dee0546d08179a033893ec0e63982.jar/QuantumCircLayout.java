import java.awt.Insets;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Scrollbar;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class QuantumCircLayout implements LayoutManager
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
        final int n2 = n * 7 / 10;
        final int n3 = container.size().height - (insets.top + insets.bottom);
        container.getComponent(0).move(insets.left, insets.top);
        container.getComponent(0).resize(n2, n3);
        final int width = n - n2;
        final int n4 = n2 + insets.left;
        int top = insets.top;
        for (int i = 1; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize = component.getPreferredSize();
                if (component instanceof Scrollbar) {
                    preferredSize.width = width;
                }
                if (component instanceof Choice) {
                    preferredSize.width = width;
                }
                if (component instanceof Label) {
                    top += preferredSize.height / 5;
                    preferredSize.width = width;
                }
                component.move(n4, top);
                component.resize(preferredSize.width, preferredSize.height);
                top += preferredSize.height;
            }
        }
    }
}
