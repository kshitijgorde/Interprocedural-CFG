// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

public class ExpressionException extends RuntimeException
{
    public ExpressionException(final IExpression expression, final String s) {
        super(createMessage(expression, s));
    }
    
    public ExpressionException(final IExpression expression, final String s, final Throwable t) {
        super(createMessage(expression, s), t);
    }
    
    protected static String createMessage(final IExpression expression, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append('\n');
        sb.append(createExpressionSummary(expression));
        sb.append('\n');
        return sb.toString();
    }
    
    protected static String createExpressionSummary(final IExpression expression) {
        return expression.toString();
    }
}
