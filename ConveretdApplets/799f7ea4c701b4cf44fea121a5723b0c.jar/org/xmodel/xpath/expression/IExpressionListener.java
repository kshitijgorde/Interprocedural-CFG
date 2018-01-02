// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModelObject;
import java.util.List;

public interface IExpressionListener
{
    void notifyAdd(final IExpression p0, final IContext p1, final List<IModelObject> p2);
    
    void notifyRemove(final IExpression p0, final IContext p1, final List<IModelObject> p2);
    
    void notifyChange(final IExpression p0, final IContext p1, final String p2, final String p3);
    
    void notifyChange(final IExpression p0, final IContext p1, final double p2, final double p3);
    
    void notifyChange(final IExpression p0, final IContext p1, final boolean p2);
    
    void notifyChange(final IExpression p0, final IContext p1);
    
    boolean requiresValueNotification();
    
    void notifyValue(final IExpression p0, final IContext[] p1, final IModelObject p2, final Object p3, final Object p4);
    
    void handleException(final IExpression p0, final IContext p1, final Exception p2);
}
