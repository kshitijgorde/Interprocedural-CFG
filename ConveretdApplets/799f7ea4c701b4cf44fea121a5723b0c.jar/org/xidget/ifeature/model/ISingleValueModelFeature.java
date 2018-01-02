// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import org.xmodel.IModelObject;

public interface ISingleValueModelFeature
{
    void setSourceNode(final IModelObject p0);
    
    void setSourceVariable(final String p0);
    
    void setValue(final Object p0);
    
    Object getValue();
}
