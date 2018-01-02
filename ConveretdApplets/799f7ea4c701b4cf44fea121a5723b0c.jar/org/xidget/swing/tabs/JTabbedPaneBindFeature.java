// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tabs;

import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;

public class JTabbedPaneBindFeature extends BindFeature
{
    public JTabbedPaneBindFeature(final IXidget xidget) {
        super(xidget);
    }
    
    public JTabbedPaneBindFeature(final IXidget xidget, final String[] array) {
        super(xidget, array);
    }
    
    @Override
    protected boolean shouldIgnore(final IModelObject modelObject) {
        if (super.shouldIgnore(modelObject)) {
            return true;
        }
        final IModelObject config = this.xidget.getConfig();
        return config.getAttribute("children") != null || config.getFirstChild("children") != null;
    }
}
