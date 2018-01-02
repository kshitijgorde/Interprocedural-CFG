// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class CeilingFunction extends Function
{
    @Override
    public String getName() {
        return "ceiling";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, 0, IExpression.ResultType.NUMBER);
        return Math.ceil(this.getArgument(0).evaluateNumber(context));
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, Math.ceil(n), Math.ceil(n2));
        }
    }
}
