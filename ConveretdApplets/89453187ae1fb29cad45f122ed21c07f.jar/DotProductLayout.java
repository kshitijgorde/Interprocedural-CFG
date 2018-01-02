import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class DotProductLayout implements LayoutManager
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
        final int width = container.size().width;
        final int componentCount = container.getComponentCount();
        container.getComponent(componentCount - 1).move(0, 0);
        container.getComponent(componentCount - 1).resize(width, container.size().height);
        int n = 0;
        for (int i = 0; i < componentCount - 1; ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize = component.getPreferredSize();
                component.move(width - preferredSize.width, n);
                component.resize(preferredSize.width, preferredSize.height);
                n += preferredSize.height;
            }
        }
    }
}
