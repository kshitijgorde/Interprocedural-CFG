// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class RatioLayout implements LayoutManager
{
    private float ratio;
    
    public RatioLayout(final float ratio) {
        this.ratio = 0.5f;
        this.ratio = ratio;
    }
    
    public RatioLayout() {
        this.ratio = 0.5f;
    }
    
    public void setRatio(final float ratio) {
        this.ratio = ratio;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void layoutContainer(final Container container) {
        final Dimension size = container.getSize();
        final int n = (int)(size.width * this.ratio);
        for (int i = 0; i < Math.min(container.getComponentCount(), 2); ++i) {
            final Component component = container.getComponent(i);
            if (i == 0) {
                component.setBounds(0, 0, n, size.height);
            }
            else {
                component.setBounds(n, 0, size.width - n, size.height);
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        final Dimension dimension = new Dimension();
        for (int i = 0; i < Math.min(container.getComponentCount(), 2); ++i) {
            final Dimension minimumSize = container.getComponent(i).getMinimumSize();
            dimension.height = Math.max(dimension.height, minimumSize.height);
            final Dimension dimension2 = dimension;
            dimension2.width += minimumSize.width;
        }
        return dimension;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Dimension dimension = new Dimension();
        for (int i = 0; i < Math.min(container.getComponentCount(), 2); ++i) {
            final Dimension preferredSize = container.getComponent(i).getPreferredSize();
            dimension.height = Math.max(dimension.height, preferredSize.height);
            final Dimension dimension2 = dimension;
            dimension2.width += preferredSize.width;
        }
        return dimension;
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
