import java.awt.Insets;
import java.awt.Button;
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

class EditDialogLayout implements LayoutManager
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
        int top = insets.top;
        int width = 300;
        int n3 = 0;
        for (int i = 0; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            boolean b = true;
            if (component.isVisible()) {
                final Dimension preferredSize = component.getPreferredSize();
                if (width < preferredSize.width) {
                    width = preferredSize.width;
                }
                if (component instanceof Scrollbar) {
                    top += 10;
                    preferredSize.width = n - n3;
                }
                if (component instanceof Choice && preferredSize.width > n) {
                    preferredSize.width = n - n3;
                }
                if (component instanceof Label) {
                    final Dimension preferredSize2 = container.getComponent(i + 1).getPreferredSize();
                    if (preferredSize.height < preferredSize2.height) {
                        preferredSize.height = preferredSize2.height;
                    }
                    top += preferredSize.height / 5;
                    b = false;
                }
                if (component instanceof Button) {
                    if (n3 == 0) {
                        top += 20;
                    }
                    if (i != container.getComponentCount() - 1) {
                        b = false;
                    }
                }
                component.move(insets.left + n3, top);
                component.resize(preferredSize.width, preferredSize.height);
                if (b) {
                    top += preferredSize.height;
                    n3 = 0;
                }
                else {
                    n3 += preferredSize.width;
                }
            }
        }
        if (container.size().height < top) {
            container.resize(width + insets.right, top + insets.bottom);
        }
    }
}
