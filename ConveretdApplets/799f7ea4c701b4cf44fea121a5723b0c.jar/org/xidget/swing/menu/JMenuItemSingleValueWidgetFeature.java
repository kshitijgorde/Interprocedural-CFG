// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.JMenuItem;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class JMenuItemSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public JMenuItemSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public Object getValue() {
        if (this.xidget.getFeature(JMenuItem.class).getSelectedObjects() != null) {
            return true;
        }
        return false;
    }
    
    @Override
    public void setValue(final Object o) {
    }
}
