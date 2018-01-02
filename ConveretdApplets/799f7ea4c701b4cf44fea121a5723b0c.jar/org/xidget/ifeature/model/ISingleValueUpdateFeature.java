// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import org.xmodel.xpath.expression.IExpression;

public interface ISingleValueUpdateFeature
{
    void setDisplayTransform(final IExpression p0);
    
    void setCommitTransform(final IExpression p0);
    
    void updateWidget();
    
    void updateModel();
    
    void display(final Object p0);
    
    void commit(final Object p0);
    
    Object toDisplay(final Object p0);
    
    Object toModel(final Object p0);
}
