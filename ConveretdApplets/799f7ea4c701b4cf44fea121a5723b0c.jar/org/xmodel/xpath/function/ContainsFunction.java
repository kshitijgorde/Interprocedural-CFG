// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class ContainsFunction extends Function
{
    @Override
    public String getName() {
        return "contains";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.STRING);
        return this.getArgument(0).evaluateString(context).contains(this.getArgument(1).evaluateString(context));
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent == null) {
            return;
        }
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        try {
            if (expression == argument) {
                final String evaluateString = argument2.evaluateString(context);
                final boolean contains = s2.contains(evaluateString);
                final boolean contains2 = s.contains(evaluateString);
                if (contains2 != contains) {
                    parent.notifyChange(this, context, contains2);
                }
            }
            else {
                final String evaluateString2 = argument.evaluateString(context);
                final boolean contains3 = evaluateString2.contains(s2);
                final boolean contains4 = evaluateString2.contains(s);
                if (contains4 != contains3) {
                    parent.notifyChange(this, context, contains4);
                }
            }
        }
        catch (ExpressionException ex) {
            parent.handleException(this, context, ex);
        }
    }
}
