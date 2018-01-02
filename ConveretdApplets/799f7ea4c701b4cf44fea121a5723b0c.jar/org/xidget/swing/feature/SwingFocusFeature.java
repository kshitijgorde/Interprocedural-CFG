// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.xidget.Creator;
import java.awt.KeyboardFocusManager;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.IXidget;
import org.xidget.ifeature.IFocusFeature;

public class SwingFocusFeature implements IFocusFeature
{
    @Override
    public void setFocus(final IXidget xidget) {
        final IWidgetCreationFeature widgetCreationFeature = xidget.getFeature(IWidgetCreationFeature.class);
        if (widgetCreationFeature != null) {
            final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
            if (lastWidgets.length > 0) {
                ((Component)lastWidgets[lastWidgets.length - 1]).requestFocus();
            }
        }
    }
    
    @Override
    public IXidget getFocus() {
        for (Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner(); component != null; component = component.getParent()) {
            final IXidget xidget = Creator.getInstance().getXidget(component);
            if (xidget != null) {
                return xidget;
            }
        }
        return null;
    }
    
    @Override
    public void addFocusListener(final IFocusListener focusListener) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner", new GlobalFocusListener(focusListener));
    }
    
    @Override
    public void removeFocusListener(final IFocusListener focusListener) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removePropertyChangeListener("focusOwner", new GlobalFocusListener(focusListener));
    }
    
    private static class GlobalFocusListener implements PropertyChangeListener
    {
        private IFocusListener focusListener;
        
        public GlobalFocusListener(final IFocusListener focusListener) {
            this.focusListener = focusListener;
        }
        
        @Override
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            this.focusListener.notifyFocus(Creator.getInstance().getXidget(propertyChangeEvent.getNewValue()), Creator.getInstance().getXidget(propertyChangeEvent.getOldValue()));
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof GlobalFocusListener && ((GlobalFocusListener)o).focusListener == this.focusListener;
        }
        
        @Override
        public int hashCode() {
            return this.focusListener.hashCode();
        }
    }
}
