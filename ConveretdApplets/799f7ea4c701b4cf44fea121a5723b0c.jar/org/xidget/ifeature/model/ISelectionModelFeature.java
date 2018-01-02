// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import java.util.List;
import org.xmodel.IModelObject;

public interface ISelectionModelFeature
{
    void setSourceVariable(final String p0);
    
    void setSourceNode(final IModelObject p0);
    
    void select(final Object p0);
    
    void deselect(final Object p0);
    
    void setSelection(final List<?> p0);
    
    List<?> getSelection();
}
