// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.form;

import javax.swing.Icon;
import javax.swing.JTabbedPane;
import org.xidget.IXidget;
import org.xidget.ifeature.IIconFeature;

public class TabIconFeature implements IIconFeature
{
    private IXidget xidget;
    
    public TabIconFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setIcon(final Object o) {
        final IXidget parent = this.xidget.getParent();
        if (parent == null) {
            return;
        }
        if (parent.getConfig().isType("tabs")) {
            parent.getFeature(JTabbedPane.class).setIconAt(parent.getChildren().indexOf(this.xidget), (Icon)o);
        }
    }
}
