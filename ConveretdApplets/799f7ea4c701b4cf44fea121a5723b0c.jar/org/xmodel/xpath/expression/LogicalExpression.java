// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.function.BooleanFunction;

public class LogicalExpression extends M
{
    Operator \u00d5;
    private static /* synthetic */ int[] \u00d6;
    
    public LogicalExpression(final Operator \u00f5) {
        this.\u00d5 = \u00f5;
    }
    
    public LogicalExpression(final Operator \u00f5, final IExpression expression, final IExpression expression2) {
        this.\u00d5 = \u00f5;
        this.addArgument(expression);
        this.addArgument(expression2);
    }
    
    @Override
    public String getName() {
        return "logical";
    }
    
    @Override
    public void addArgument(IExpression expression) {
        if (expression.getType() != IExpression.ResultType.BOOLEAN) {
            expression = new BooleanFunction(expression);
        }
        super.addArgument(expression);
    }
    
    public boolean B(final IContext context, final IExpression expression, final IExpression expression2) throws ExpressionException {
        final boolean evaluateBoolean = expression.evaluateBoolean(context);
        final boolean evaluateBoolean2 = expression2.evaluateBoolean(context);
        return (this.\u00d5 == Operator.OR) ? (evaluateBoolean || evaluateBoolean2) : (evaluateBoolean && evaluateBoolean2);
    }
    
    @Override
    protected IExpression cloneOne() {
        return new LogicalExpression(this.\u00d5);
    }
    
    @Override
    public String toString() {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        switch (N()[this.\u00d5.ordinal()]) {
            case 1: {
                return String.valueOf(argument.toString()) + " or " + argument2.toString();
            }
            case 2: {
                return String.valueOf(argument.toString()) + " and " + argument2.toString();
            }
            default: {
                return null;
            }
        }
    }
    
    static /* synthetic */ int[] N() {
        final int[] \u00f6 = LogicalExpression.\u00d6;
        if (\u00f6 != null) {
            return \u00f6;
        }
        final int[] \u00f62 = new int[Operator.values().length];
        try {
            \u00f62[Operator.AND.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00f62[Operator.OR.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        return LogicalExpression.\u00d6 = \u00f62;
    }
    
    public enum Operator
    {
        OR("OR", 0), 
        AND("AND", 1);
        
        static {
            A = new Operator[] { Operator.OR, Operator.AND };
        }
        
        private Operator(final String s, final int n) {
        }
    }
}
