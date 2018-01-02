// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class SubstringAfterFunction extends Function
{
    @Override
    public String getName() {
        return "substring-after";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.STRING);
        return this.B(this.getArgument(0).evaluateString(context), this.getArgument(1).evaluateString(context));
    }
    
    private String B(final String s, final String s2) {
        final int index = s.indexOf(s2);
        final int n = index + s2.length();
        return (index >= 0) ? s.substring(n) : "";
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
                context.getModel().revert();
                final String b = this.B(s2, argument2.evaluateString(context));
                context.getModel().restore();
                final String b2 = this.B(s, argument2.evaluateString(context));
                if (!b2.equals(b)) {
                    parent.notifyChange(this, context, b2, b);
                }
            }
            else {
                context.getModel().revert();
                final String b3 = this.B(argument.evaluateString(context), s2);
                context.getModel().restore();
                final String b4 = this.B(argument.evaluateString(context), s);
                if (!b4.equals(b3)) {
                    parent.notifyChange(this, context, b4, b3);
                }
            }
        }
        catch (ExpressionException ex) {
            parent.handleException(this, context, ex);
        }
    }
}
