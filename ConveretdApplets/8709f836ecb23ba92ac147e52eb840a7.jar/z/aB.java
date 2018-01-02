// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Insets;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;

public final class aB implements LayoutManager
{
    private Dimension a;
    
    public aB(final Dimension a) {
        this.a = a;
    }
    
    public final void addLayoutComponent(final String s, final Component component) {
    }
    
    public final void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int n = container.getWidth() - insets.left - insets.right;
            final int n2 = container.getHeight() - insets.top - insets.bottom;
            for (int i = 0; i < container.getComponentCount(); ++i) {
                final Component component;
                (component = container.getComponent(i)).setBounds(insets.left, insets.top, n, n2);
                container.setComponentZOrder(component, i);
            }
        }
    }
    
    public final Dimension minimumLayoutSize(final Container container) {
        return this.a;
    }
    
    public final Dimension preferredLayoutSize(final Container container) {
        return this.a;
    }
    
    public final void removeLayoutComponent(final Component component) {
    }
}
