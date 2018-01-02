// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.ifeature.ILayoutFeature;
import org.xidget.ifeature.canvas.ICanvasFeature;
import org.xidget.ifeature.canvas.IPaintFeature;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.Container;
import org.xidget.IXidget;
import org.xidget.ifeature.IWidgetContainerFeature;

public class GenericContainerFeature implements IWidgetContainerFeature
{
    public static final int validationDelay = 0;
    protected IXidget xidget;
    private int spacing;
    
    public GenericContainerFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void addWidget(final IXidget xidget) {
        this.addWidget(-1, xidget);
    }
    
    @Override
    public void addWidget(final int n, final IXidget xidget) {
        final Container container = this.xidget.getFeature(Container.class);
        if (container != null) {
            final IWidgetCreationFeature widgetCreationFeature = xidget.getFeature(IWidgetCreationFeature.class);
            if (widgetCreationFeature != null) {
                final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
                if (lastWidgets.length > 0) {
                    if (n == -1) {
                        container.add((Component)lastWidgets[0]);
                    }
                    else {
                        container.add((Component)lastWidgets[0], n);
                    }
                }
            }
            if (xidget.getFeature(IPaintFeature.class) != null) {
                final ICanvasFeature canvasFeature = this.xidget.getFeature(ICanvasFeature.class);
                if (canvasFeature != null) {
                    canvasFeature.addChild(xidget);
                }
            }
        }
    }
    
    @Override
    public void removeWidget(final IXidget xidget) {
        final Container container = this.xidget.getFeature(Container.class);
        if (container != null) {
            final IWidgetCreationFeature widgetCreationFeature = xidget.getFeature(IWidgetCreationFeature.class);
            if (widgetCreationFeature != null) {
                final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
                if (lastWidgets.length > 0) {
                    container.remove((Component)lastWidgets[0]);
                }
            }
            if (xidget.getFeature(IPaintFeature.class) != null) {
                final ICanvasFeature canvasFeature = this.xidget.getFeature(ICanvasFeature.class);
                if (canvasFeature != null) {
                    canvasFeature.removeChild(xidget);
                }
            }
        }
    }
    
    @Override
    public void relayout() {
        final ILayoutFeature layoutFeature = this.xidget.getFeature(ILayoutFeature.class);
        if (layoutFeature != null) {
            layoutFeature.invalidate();
        }
        final Container container = this.xidget.getFeature(Container.class);
        if (container != null && container.isShowing()) {
            container.validate();
        }
    }
    
    @Override
    public void setSpacing(final int spacing) {
        this.spacing = spacing;
    }
    
    @Override
    public int getSpacing() {
        return this.spacing;
    }
    
    @Override
    public String toString() {
        return this.xidget.toString();
    }
}
