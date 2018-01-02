// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class LastFunction extends Function
{
    @Override
    public String getName() {
        return "last";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public boolean requiresOrdinalContext() {
        return true;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(0, 0);
        return context.getSize();
    }
}
