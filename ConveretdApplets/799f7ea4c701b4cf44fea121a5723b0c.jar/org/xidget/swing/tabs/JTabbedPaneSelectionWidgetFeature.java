// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tabs;

import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.Component;
import javax.swing.JTabbedPane;
import org.xidget.ifeature.IBindFeature;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISelectionWidgetFeature;

public class JTabbedPaneSelectionWidgetFeature implements ISelectionWidgetFeature
{
    private IXidget xidget;
    
    public JTabbedPaneSelectionWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void select(final Object o) {
        for (final IXidget xidget : this.xidget.getChildren()) {
            if (xidget.getFeature(IBindFeature.class).getBoundContext().getObject().equals(o)) {
                this.xidget.getFeature(JTabbedPane.class).setSelectedComponent((Component)xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets()[0]);
            }
        }
    }
    
    @Override
    public void deselect(final Object o) {
    }
    
    @Override
    public void setSelection(final List<?> list) {
        if (list.size() == 0) {
            return;
        }
        final Object value = list.get(0);
        for (final IXidget xidget : this.xidget.getChildren()) {
            if (xidget.getFeature(IBindFeature.class).getBoundContext().getObject().equals(value)) {
                this.xidget.getFeature(JTabbedPane.class).setSelectedComponent((Component)xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets()[0]);
            }
        }
    }
    
    @Override
    public List<?> getSelection() {
        final Component selectedComponent = this.xidget.getFeature(JTabbedPane.class).getSelectedComponent();
        for (final IXidget xidget : this.xidget.getChildren()) {
            if (xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets()[0] == selectedComponent) {
                return Collections.singletonList((Object)xidget.getFeature(IBindFeature.class).getBoundContext().getObject());
            }
        }
        return Collections.emptyList();
    }
}
