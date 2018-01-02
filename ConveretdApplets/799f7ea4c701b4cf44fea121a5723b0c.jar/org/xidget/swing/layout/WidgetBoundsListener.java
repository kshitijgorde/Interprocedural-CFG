// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.layout;

import org.xmodel.IModelObject;
import org.xidget.layout.Bounds;
import org.xmodel.Xlate;
import org.xidget.ifeature.IWidgetFeature;
import java.awt.event.ComponentEvent;
import org.xidget.IXidget;
import java.awt.event.ComponentAdapter;

public class WidgetBoundsListener extends ComponentAdapter
{
    private IXidget xidget;
    
    public WidgetBoundsListener(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void componentMoved(final ComponentEvent componentEvent) {
        final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
        final Bounds computedBounds = widgetFeature.getComputedBounds();
        final IModelObject boundsNode = widgetFeature.getBoundsNode();
        if (boundsNode != null) {
            Xlate.set(boundsNode, computedBounds.toString());
        }
        widgetFeature.setComputedBounds(componentEvent.getComponent().getX(), componentEvent.getComponent().getY(), computedBounds.width, computedBounds.height);
    }
    
    @Override
    public void componentResized(final ComponentEvent componentEvent) {
        final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
        final Bounds computedBounds = widgetFeature.getComputedBounds();
        final IModelObject boundsNode = widgetFeature.getBoundsNode();
        if (boundsNode != null) {
            Xlate.set(boundsNode, computedBounds.toString());
        }
        widgetFeature.setComputedBounds(computedBounds.x, computedBounds.y, componentEvent.getComponent().getWidth(), componentEvent.getComponent().getHeight());
    }
}
