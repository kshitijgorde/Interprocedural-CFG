import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class ImportDialogLayout implements LayoutManager
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
        final int n2 = container.size().height - (insets.top + insets.bottom);
        if (container.getComponentCount() == 0) {
            return;
        }
        final Dimension preferredSize = container.getComponent(container.getComponentCount() - 1).getPreferredSize();
        container.getComponent(0).move(insets.left, insets.top);
        final int n3 = container.size().width - insets.left - insets.right;
        final int n4 = container.size().height - insets.top - insets.bottom - preferredSize.height;
        container.getComponent(0).resize(n3, n4);
        final int n5 = n4 + insets.top;
        int n6 = 0;
        for (int i = 1; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize2 = component.getPreferredSize();
                component.move(insets.left + n6, n5);
                component.resize(preferredSize2.width, preferredSize2.height);
                n6 += preferredSize2.width;
            }
        }
    }
}
