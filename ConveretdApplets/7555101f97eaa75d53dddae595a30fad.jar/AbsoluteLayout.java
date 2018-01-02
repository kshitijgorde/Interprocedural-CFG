import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class AbsoluteLayout implements LayoutManager
{
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Insets insets = container.insets();
        final int countComponents = container.countComponents();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            final Dimension preferredSize = component.preferredSize();
            final Point location = component.location();
            if (location.x + preferredSize.width > n) {
                n = location.x + preferredSize.width;
            }
            if (location.y + preferredSize.height > n2) {
                n2 = location.y + preferredSize.height;
            }
        }
        return new Dimension(insets.left + insets.right + n, insets.top + insets.bottom + n2);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        final Insets insets = container.insets();
        final int countComponents = container.countComponents();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            final Dimension preferredSize = component.preferredSize();
            final Point location = component.location();
            if (location.x + preferredSize.width > n) {
                n = location.x + preferredSize.width;
            }
            if (location.y + preferredSize.height > n2) {
                n2 = location.y + preferredSize.height;
            }
        }
        return new Dimension(insets.left + insets.right + n, insets.top + insets.bottom + n2);
    }
    
    public void layoutContainer(final Container container) {
        for (int countComponents = container.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            final Dimension preferredSize = component.preferredSize();
            component.resize(preferredSize.width, preferredSize.height);
        }
    }
}
