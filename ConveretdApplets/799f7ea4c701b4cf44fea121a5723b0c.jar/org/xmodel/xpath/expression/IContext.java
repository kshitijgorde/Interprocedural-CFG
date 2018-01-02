// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.IModel;

public interface IContext
{
    IModel getModel();
    
    IContext getParent();
    
    IContext getRoot();
    
    IModelObject getObject();
    
    int getPosition();
    
    int getSize();
    
    String set(final String p0, final String p1);
    
    Number set(final String p0, final Number p1);
    
    Boolean set(final String p0, final Boolean p1);
    
    List<IModelObject> set(final String p0, final List<IModelObject> p1);
    
    List<IModelObject> set(final String p0, final IModelObject p1);
    
    Object get(final String p0);
    
    void notifyBind(final IExpression p0);
    
    void notifyUnbind(final IExpression p0);
    
    void notifyUpdate(final IExpression p0);
    
    void markUpdate(final IExpression p0);
    
    boolean shouldUpdate(final IExpression p0);
    
    int getLastUpdate(final IExpression p0);
    
    IVariableScope getScope();
}
