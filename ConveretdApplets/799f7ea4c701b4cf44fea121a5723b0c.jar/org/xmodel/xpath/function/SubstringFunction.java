// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class SubstringFunction extends Function
{
    @Override
    public String getName() {
        return "substring";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 3);
        this.assertType(context, 0, IExpression.ResultType.STRING);
        this.assertType(context, 1, IExpression.ResultType.NUMBER);
        this.assertType(context, 2, IExpression.ResultType.NUMBER);
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        final String evaluateString = argument.evaluateString(context);
        final int n = (int)argument2.evaluateNumber(context);
        final int n2;
        if (argument3 != null) {
            n2 = (int)argument3.evaluateNumber(context);
        }
        return this.A(evaluateString, n, n2);
    }
    
    private String A(final String s, final int n, final int n2) {
        if (n2 < 0) {
            return s.substring(n);
        }
        return s.substring(n, n2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent == null) {
            return;
        }
        final IExpression argument = this.getArgument(1);
        final IExpression argument2 = this.getArgument(2);
        try {
            context.getModel().revert();
            final int n = (int)argument.evaluateNumber(context);
            final int n2;
            if (argument2 != null) {
                n2 = (int)argument2.evaluateNumber(context);
            }
            final String a = this.A(s2, n, n2);
            context.getModel().restore();
            final int n3 = (int)argument.evaluateNumber(context);
            final int n4;
            if (argument2 != null) {
                n4 = (int)argument2.evaluateNumber(context);
            }
            final String a2 = this.A(s, n3, n4);
            if (!a2.equals(a)) {
                parent.notifyChange(this, context, a2, a);
            }
        }
        catch (ExpressionException ex) {
            parent.handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression parent = this.getParent();
        if (parent == null) {
            return;
        }
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        try {
            if (expression == argument2) {
                context.getModel().revert();
                final String evaluateString = argument.evaluateString(context);
                final int n3;
                if (argument3 != null) {
                    n3 = (int)argument3.evaluateNumber(context);
                }
                final String a = this.A(evaluateString, (int)n2, n3);
                context.getModel().restore();
                final String evaluateString2 = argument.evaluateString(context);
                final int n4;
                if (argument3 != null) {
                    n4 = (int)argument3.evaluateNumber(context);
                }
                final String a2 = this.A(evaluateString2, (int)n, n4);
                if (!a2.equals(a)) {
                    parent.notifyChange(this, context, a2, a);
                }
            }
            else {
                context.getModel().revert();
                final String a3 = this.A(argument.evaluateString(context), (int)argument2.evaluateNumber(context), (int)n2);
                context.getModel().restore();
                final String a4 = this.A(argument.evaluateString(context), (int)argument2.evaluateNumber(context), (int)n);
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
