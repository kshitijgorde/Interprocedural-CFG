import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class GasLayout implements LayoutManager
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
        final int n = container.size().width * 2 / 3;
        container.getComponent(0).move(0, 0);
        container.getComponent(0).resize(n, container.size().height - 100);
        container.getComponent(1).move(0, container.size().height - 100);
        container.getComponent(1).resize(n, 100);
        int n2 = 0;
        for (int i = 2; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize = component.getPreferredSize();
                if (component instanceof Scrollbar) {
                    preferredSize.width = container.size().width - n;
                }
                int n3 = 0;
                if (component instanceof Label) {
                    n2 += preferredSize.height / 3;
                    n3 = (container.size().width - n - preferredSize.width) / 2;
                }
                component.move(n + n3, n2);
                component.resize(preferredSize.width, preferredSize.height);
                n2 += preferredSize.height;
            }
        }
    }
}
