// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.variable;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import java.util.List;

public interface IVariableSource
{
    void setParent(final IVariableSource p0);
    
    IVariableSource getParent();
    
    void addScope(final IVariableScope p0);
    
    void removeScope(final IVariableScope p0);
    
    List<IVariableScope> getScopes();
    
    IVariableScope getScope(final String p0);
    
    IVariableScope getVariableScope(final String p0);
    
    IExpression.ResultType getVariableType(final String p0);
    
    IExpression.ResultType getVariableType(final String p0, final IContext p1);
    
    Object getVariable(final String p0, final IContext p1) throws ExpressionException;
}
