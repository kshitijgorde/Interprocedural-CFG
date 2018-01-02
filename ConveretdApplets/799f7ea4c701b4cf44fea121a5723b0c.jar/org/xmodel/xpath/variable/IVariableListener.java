// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.variable;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;

public interface IVariableListener
{
    void notifyAdd(final String p0, final IVariableScope p1, final IContext p2, final List<IModelObject> p3);
    
    void notifyRemove(final String p0, final IVariableScope p1, final IContext p2, final List<IModelObject> p3);
    
    void notifyChange(final String p0, final IVariableScope p1, final IContext p2, final String p3, final String p4);
    
    void notifyChange(final String p0, final IVariableScope p1, final IContext p2, final Number p3, final Number p4);
    
    void notifyChange(final String p0, final IVariableScope p1, final IContext p2, final Boolean p3);
}
