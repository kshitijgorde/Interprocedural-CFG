// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.layout;

import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IWidgetContextFeature;
import java.awt.Component;
import java.awt.Dimension;
import org.xidget.ifeature.ILayoutFeature;
import java.awt.Container;
import org.xidget.IXidget;
import java.awt.LayoutManager2;

public class AnchorLayoutManager implements LayoutManager2
{
    private IXidget xidget;
    
    public AnchorLayoutManager(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public float getLayoutAlignmentX(final Container container) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public float getLayoutAlignmentY(final Container container) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void invalidateLayout(final Container container) {
        final ILayoutFeature layoutFeature = this.xidget.getFeature(ILayoutFeature.class);
        if (layoutFeature != null) {
            layoutFeature.invalidate();
        }
    }
    
    @Override
    public Dimension maximumLayoutSize(final Container container) {
        return null;
    }
    
    @Override
    public void addLayoutComponent(final Component component, final Object o) {
    }
    
    @Override
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    @Override
    public void removeLayoutComponent(final Component component) {
    }
    
    @Override
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final ILayoutFeature layoutFeature = this.xidget.getFeature(ILayoutFeature.class);
            if (layoutFeature != null) {
                final StatefulContext context = this.xidget.getFeature(IWidgetContextFeature.class).getContext(container);
                if (context != null) {
                    layoutFeature.layout(context);
                }
            }
        }
        // monitorexit(container.getTreeLock())
    }
    
    @Override
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(5, 5);
    }
    
    @Override
    public Dimension preferredLayoutSize(final Container container) {
        throw new UnsupportedOperationException();
    }
}
