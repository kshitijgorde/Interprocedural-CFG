// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class SubstringBeforeFunction extends Function
{
    @Override
    public String getName() {
        return "substring-before";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.STRING);
        return this.A(this.getArgument(0).evaluateString(context), this.getArgument(1).evaluateString(context));
    }
    
    private String A(final String s, final String s2) {
        final int index = s.indexOf(s2);
        return (index >= 0) ? s.substring(0, index) : "";
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
                final String a = this.A(s2, argument2.evaluateString(context));
                context.getModel().restore();
                final String a2 = this.A(s, argument2.evaluateString(context));
                if (!a2.equals(a)) {
                    parent.notifyChange(this, context, a2, a);
                }
            }
            else {
                context.getModel().revert();
                final String a3 = this.A(argument.evaluateString(context), s2);
                context.getModel().restore();
                final String a4 = this.A(argument.evaluateString(context), s);
                if (!a4.equals(a3)) {
                    parent.notifyChange(this, context, a4, a3);
                }
            }
        }
        catch (ExpressionException ex) {
            parent.handleException(this, context, ex);
        }
    }
}
