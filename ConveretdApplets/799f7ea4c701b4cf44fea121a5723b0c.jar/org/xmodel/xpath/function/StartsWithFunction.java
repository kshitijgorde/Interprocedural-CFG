// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class StartsWithFunction extends Function
{
    @Override
    public String getName() {
        return "starts-with";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.STRING);
        return this.getArgument(0).evaluateString(context).startsWith(this.getArgument(1).evaluateString(context));
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
                final boolean startsWith = s2.startsWith(argument2.evaluateString(context));
                context.getModel().restore();
                final boolean startsWith2 = s.startsWith(argument2.evaluateString(context));
                if (startsWith2 != startsWith) {
                    parent.notifyChange(this, context, startsWith2);
                }
            }
            else {
                context.getModel().revert();
                final boolean startsWith3 = argument.evaluateString(context).startsWith(s2);
                context.getModel().restore();
                final boolean startsWith4 = argument.evaluateString(context).startsWith(s);
                if (startsWith4 != startsWith3) {
                    parent.notifyChange(this, context, startsWith4);
                }
            }
        }
        catch (ExpressionException ex) {
            parent.handleException(this, context, ex);
        }
    }
}
