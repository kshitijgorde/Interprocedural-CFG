// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.layout;

import org.xidget.layout.Bounds;
import org.xidget.ifeature.IWidgetContextFeature;
import org.xidget.ifeature.ILayoutFeature;
import org.xidget.ifeature.IWidgetFeature;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import org.xidget.IXidget;
import java.awt.LayoutManager2;

public class AdapterLayoutManager implements LayoutManager2
{
    private IXidget xidget;
    private LayoutManager delegate;
    
    public AdapterLayoutManager(final IXidget xidget, final LayoutManager delegate) {
        this.xidget = xidget;
        this.delegate = delegate;
    }
    
    @Override
    public void addLayoutComponent(final Component component, final Object o) {
        if (this.delegate instanceof LayoutManager2) {
            ((LayoutManager2)this.delegate).addLayoutComponent(component, o);
        }
    }
    
    @Override
    public float getLayoutAlignmentX(final Container container) {
        if (this.delegate instanceof LayoutManager2) {
            return ((LayoutManager2)this.delegate).getLayoutAlignmentX(container);
        }
        return 0.0f;
    }
    
    @Override
    public float getLayoutAlignmentY(final Container container) {
        if (this.delegate instanceof LayoutManager2) {
            return ((LayoutManager2)this.delegate).getLayoutAlignmentY(container);
        }
        return 0.0f;
    }
    
    @Override
    public void invalidateLayout(final Container container) {
        if (this.delegate instanceof LayoutManager2) {
            ((LayoutManager2)this.delegate).invalidateLayout(container);
        }
    }
    
    @Override
    public Dimension maximumLayoutSize(final Container container) {
        if (this.delegate instanceof LayoutManager2) {
            return ((LayoutManager2)this.delegate).maximumLayoutSize(container);
        }
        return new Dimension(0, 0);
    }
    
    @Override
    public void addLayoutComponent(final String s, final Component component) {
        this.delegate.addLayoutComponent(s, component);
    }
    
    @Override
    public Dimension minimumLayoutSize(final Container container) {
        return this.delegate.minimumLayoutSize(container);
    }
    
    @Override
    public void removeLayoutComponent(final Component component) {
        this.delegate.removeLayoutComponent(component);
    }
    
    @Override
    public void layoutContainer(final Container container) {
        this.delegate.layoutContainer(container);
        final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
        if (widgetFeature != null) {
            final Component component = this.xidget.getFeature(Component.class);
            if (component != null) {
                widgetFeature.setComputedBounds(component.getX(), component.getY(), component.getWidth(), component.getHeight());
            }
        }
    }
    
    @Override
    public Dimension preferredLayoutSize(final Container container) {
        final ILayoutFeature layoutFeature = this.xidget.getFeature(ILayoutFeature.class);
        if (layoutFeature != null) {
            layoutFeature.layout(this.xidget.getFeature(IWidgetContextFeature.class).getContext(this.xidget.getFeature(Component.class)));
            final Bounds computedBounds = this.xidget.getFeature(IWidgetFeature.class).getComputedBounds();
            return new Dimension(Math.round(computedBounds.width), Math.round(computedBounds.height));
        }
        return new Dimension(0, 0);
    }
}
