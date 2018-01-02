// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import java.awt.event.ComponentEvent;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JComponent;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.ifeature.IBindFeature;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.xidget.IXidget;
import java.awt.event.MouseListener;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.event.ComponentAdapter;

public abstract class SwingWidgetCreationFeature extends ComponentAdapter implements IWidgetCreationFeature
{
    private final MouseListener mouseListener;
    protected IXidget xidget;
    
    protected SwingWidgetCreationFeature(final IXidget xidget) {
        this.mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                final IBindFeature bindFeature = SwingWidgetCreationFeature.this.xidget.getFeature(IBindFeature.class);
                final IScriptFeature scriptFeature = SwingWidgetCreationFeature.this.xidget.getFeature(IScriptFeature.class);
                if (mouseEvent.getClickCount() == 1) {
                    scriptFeature.runScript("onClick", bindFeature.getBoundContext());
                }
                else if (mouseEvent.getClickCount() == 2) {
                    scriptFeature.runScript("onDoubleClick", bindFeature.getBoundContext());
                }
            }
        };
        this.xidget = xidget;
    }
    
    @Override
    public void createWidgets() {
        this.createSwingWidget();
        final IWidgetContainerFeature widgetContainerFeature = this.xidget.getParent().getFeature(IWidgetContainerFeature.class);
        if (widgetContainerFeature != null) {
            widgetContainerFeature.addWidget(this.xidget);
        }
        final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
        if (scriptFeature != null && (scriptFeature.hasScript("onClick") || scriptFeature.hasScript("onDoubleClick"))) {
            final Object[] lastWidgets = this.getLastWidgets();
            if (lastWidgets.length > 0) {
                ((JComponent)lastWidgets[lastWidgets.length - 1]).addMouseListener(this.mouseListener);
            }
        }
    }
    
    @Override
    public void destroyWidgets() {
        final JComponent component = this.xidget.getFeature(JComponent.class);
        if (component != null) {
            final Container parent = component.getParent();
            if (parent != null) {
                parent.remove(component);
                parent.validate();
                parent.repaint();
            }
        }
    }
    
    protected abstract JComponent createSwingWidget();
    
    @Override
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentHidden(final ComponentEvent componentEvent) {
    }
}
