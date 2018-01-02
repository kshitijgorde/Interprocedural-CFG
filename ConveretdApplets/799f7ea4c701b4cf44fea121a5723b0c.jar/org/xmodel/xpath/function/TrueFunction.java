// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class TrueFunction extends Function
{
    @Override
    public String getName() {
        return "true";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(0, 0);
        return true;
    }
}
