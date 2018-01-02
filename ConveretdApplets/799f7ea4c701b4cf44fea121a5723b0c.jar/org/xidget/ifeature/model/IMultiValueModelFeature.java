// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import java.util.List;
import org.xmodel.xpath.expression.IExpression;

public interface IMultiValueModelFeature
{
    void setSourceExpression(final IExpression p0);
    
    void setSourceVariable(final String p0);
    
    void insertValue(final int p0, final Object p1);
    
    void updateValue(final int p0, final Object p1);
    
    void removeValue(final int p0);
    
    void setValues(final List<?> p0);
    
    List<?> getValues();
}
