// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;
import org.xmodel.xpath.expression.IContext;

public interface B
{
    int axis();
    
    void setAxis(final int p0);
    
    boolean hasAxis(final int p0);
    
    String type();
    
    G predicate();
    
    boolean evaluate(final IContext p0, final IPath p1, final IModelObject p2);
    
    List<IModelObject> query(final IContext p0, final IModelObject p1, final List<IModelObject> p2);
    
    List<IModelObject> query(final IContext p0, final List<IModelObject> p1, final List<IModelObject> p2);
    
    B clone();
}
