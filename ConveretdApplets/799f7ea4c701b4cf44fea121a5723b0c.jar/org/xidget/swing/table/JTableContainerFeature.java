// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import org.xidget.IXidget;
import org.xidget.swing.feature.GenericContainerFeature;

public class JTableContainerFeature extends GenericContainerFeature
{
    public JTableContainerFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void addWidget(final IXidget xidget) {
        if (!this.isEditor(xidget)) {
            super.addWidget(xidget);
        }
    }
    
    @Override
    public void removeWidget(final IXidget xidget) {
        if (!this.isEditor(xidget)) {
            super.removeWidget(xidget);
        }
    }
    
    private boolean isEditor(final IXidget xidget) {
        return xidget.getConfig().getParent().isType("editor");
    }
}
