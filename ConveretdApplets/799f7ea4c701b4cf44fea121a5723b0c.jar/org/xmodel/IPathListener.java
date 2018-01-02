// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;
import org.xmodel.xpath.expression.IContext;

public interface IPathListener
{
    void notifyAdd(final IContext p0, final IPath p1, final int p2, final List<IModelObject> p3);
    
    void notifyRemove(final IContext p0, final IPath p1, final int p2, final List<IModelObject> p3);
}
