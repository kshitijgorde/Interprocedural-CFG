// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.function.NumberFunction;

public class C extends L
{
    public C(final IExpression expression) {
        this.addArgument(expression);
    }
    
    @Override
    public String getName() {
        return "negate";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public void addArgument(IExpression expression) {
        if (expression.getType() != IExpression.ResultType.NUMBER) {
            expression = new NumberFunction(expression);
        }
        super.addArgument(expression);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        return -this.getArgument(0).evaluateNumber(context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        if (this.k != null) {
            this.k.notifyChange(this, context, -n, -n2);
        }
    }
    
    @Override
    public String toString() {
        return "-" + this.getArgument(0).toString();
    }
}
