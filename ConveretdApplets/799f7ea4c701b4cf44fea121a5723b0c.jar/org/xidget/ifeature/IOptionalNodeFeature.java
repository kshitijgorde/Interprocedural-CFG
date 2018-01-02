// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IExpression;

public interface IOptionalNodeFeature
{
    void setSourceExpression(final String p0, final IExpression p1);
    
    void createOptionalNodes(final String p0, final StatefulContext p1);
    
    void deleteOptionalNodes(final String p0, final StatefulContext p1);
}
