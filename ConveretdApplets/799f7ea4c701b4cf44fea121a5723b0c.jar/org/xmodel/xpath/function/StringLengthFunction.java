// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionListenerList;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class StringLengthFunction extends Function
{
    @Override
    public String getName() {
        return "string-length";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(0, 1);
        this.assertType(context, IExpression.ResultType.STRING);
        final IExpression argument = this.getArgument(0);
        if (argument != null) {
            return argument.evaluateString(context).length();
        }
        return StringFunction.stringValue(context.getObject()).length();
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final ExpressionListenerList listeners = this.getListeners();
        if (listeners != null) {
            final double n = s2.length();
            final double n2 = s.length();
            if (n2 != n) {
                listeners.notifyChange(this, context, n2, n);
            }
        }
    }
}
