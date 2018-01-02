// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.tree;

import org.xidget.util.XidgetUtil;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.IXidget;
import org.xidget.feature.model.SelectionModelFeature;

public class SubTreeSelectionModelFeature extends SelectionModelFeature
{
    public SubTreeSelectionModelFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected StatefulContext getContext() {
        return XidgetUtil.findTreeRoot(this.xidget).getFeature(IBindFeature.class).getBoundContext();
    }
}
