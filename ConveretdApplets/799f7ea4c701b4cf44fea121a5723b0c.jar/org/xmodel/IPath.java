// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public interface IPath
{
    void setParent(final IExpression p0);
    
    boolean isAbsolute(final IContext p0);
    
    B getPathElement(final int p0);
    
    IVariableSource getVariableSource();
    
    void setVariable(final String p0, final String p1);
    
    List<IModelObject> query(final IModelObject p0, final List<IModelObject> p1);
    
    List<IModelObject> query(final IContext p0, final List<IModelObject> p1);
    
    IModelObject queryFirst(final IModelObject p0);
    
    int length();
    
    void addPathListener(final IContext p0, final IPathListener p1);
    
    void removePathListener(final IContext p0, final IPathListener p1);
    
    IPath clone();
}
