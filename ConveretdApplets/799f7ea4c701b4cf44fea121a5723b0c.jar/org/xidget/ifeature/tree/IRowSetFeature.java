// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.tree;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.StatefulContext;

public interface IRowSetFeature
{
    void setRows(final StatefulContext p0, final List<IModelObject> p1);
    
    List<IModelObject> getRows(final StatefulContext p0);
}
