// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class ConcatFunction extends Function
{
    @Override
    public String getName() {
        return "concat";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(0, Integer.MAX_VALUE);
        this.assertType(context, IExpression.ResultType.STRING);
        final StringBuffer sb = new StringBuffer();
        final Iterator<IExpression> iterator = this.getArguments().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().evaluateString(context));
        }
        return sb.toString();
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent == null) {
            return;
        }
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        for (final IExpression expression2 : this.getArguments()) {
            if (expression2 == expression) {
                sb.append(s2);
                sb2.append(s);
            }
            else {
                try {
                    final String evaluateString = expression2.evaluateString(context);
                    sb.append(evaluateString);
                    sb2.append(evaluateString);
                }
                catch (ExpressionException ex) {
                    parent.handleException(this, context, ex);
                }
            }
        }
        parent.notifyChange(this, context, sb2.toString(), sb.toString());
    }
}
