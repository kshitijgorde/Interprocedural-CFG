// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class TranslateFunction extends Function
{
    @Override
    public String getName() {
        return "translate";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(3, 3);
        this.assertType(context, IExpression.ResultType.STRING);
        return this.A(this.getArgument(0).evaluateString(context), this.getArgument(1).evaluateString(context), this.getArgument(2).evaluateString(context));
    }
    
    private String A(final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder(s);
        final int length = s2.length();
        final int length2 = s3.length();
        final int n = (length < length2) ? length : length2;
        for (int i = 0; i < sb.length(); ++i) {
            for (int j = 0; j < n; ++j) {
                if (sb.charAt(i) == s2.charAt(j)) {
                    sb.setCharAt(i, s3.charAt(j));
                }
            }
        }
        return sb.toString();
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent == null) {
            return;
        }
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        try {
            if (expression == argument) {
                context.getModel().revert();
                final String a = this.A(s2, argument2.evaluateString(context), argument3.evaluateString(context));
                context.getModel().restore();
                final String a2 = this.A(s, argument2.evaluateString(context), argument3.evaluateString(context));
                if (!a2.equals(a)) {
                    parent.notifyChange(this, context, a2, a);
                }
            }
            else if (expression == argument2) {
                context.getModel().revert();
                final String a3 = this.A(argument.evaluateString(context), s2, argument3.evaluateString(context));
                context.getModel().restore();
                final String a4 = this.A(argument.evaluateString(context), s, argument3.evaluateString(context));
                if (!a4.equals(a3)) {
                    parent.notifyChange(this, context, a4, a3);
                }
            }
            else {
                context.getModel().revert();
                final String a5 = this.A(argument.evaluateString(context), argument2.evaluateString(context), s2);
                context.getModel().restore();
                final String a6 = this.A(argument.evaluateString(context), argument2.evaluateString(context), s);
                if (!a6.equals(a5)) {
                    parent.notifyChange(this, context, a6, a5);
                }
            }
        }
        catch (ExpressionException ex) {
            parent.handleException(this, context, ex);
        }
    }
}
