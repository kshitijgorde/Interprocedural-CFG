import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class StackLayout implements LayoutManager
{
    int vgap;
    
    public StackLayout(final int vgap) {
        this.vgap = vgap;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Insets insets = container.insets();
        final int countComponents = container.countComponents();
        int width = 0;
        int n = 0;
        for (int i = 0; i < countComponents; ++i) {
            final Dimension preferredSize = container.getComponent(i).preferredSize();
            if (width < preferredSize.width) {
                width = preferredSize.width;
            }
            n += preferredSize.height;
            if (i != 0) {
                n += this.vgap;
            }
        }
        return new Dimension(insets.left + insets.right + width, insets.top + insets.bottom + n);
    }
    
    public void layoutContainer(final Container container) {
        final Insets insets = container.insets();
        final int left = insets.left;
        int top = insets.top;
        final int width = this.preferredLayoutSize(container).width;
        for (int countComponents = container.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            final Dimension preferredSize = component.preferredSize();
            component.reshape(left, top, width, preferredSize.height);
            top += preferredSize.height + this.vgap;
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
