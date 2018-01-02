// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.function.NumberFunction;

public class ArithmeticExpression extends D
{
    Operator \u00c3;
    private static /* synthetic */ int[] \u00c2;
    
    public ArithmeticExpression(final Operator \u00e3) {
        this.\u00c3 = \u00e3;
    }
    
    public ArithmeticExpression(final Operator \u00e3, final IExpression expression, final IExpression expression2) {
        this.\u00c3 = \u00e3;
        this.addArgument(expression);
        this.addArgument(expression2);
    }
    
    @Override
    public String getName() {
        return "arithmetic";
    }
    
    @Override
    public void addArgument(IExpression expression) {
        if (expression.getType() != IExpression.ResultType.NUMBER) {
            expression = new NumberFunction(expression);
        }
        super.addArgument(expression);
    }
    
    public double A(final IContext context, final IExpression expression, final IExpression expression2) throws ExpressionException {
        final double evaluateNumber = expression.evaluateNumber(context);
        final double evaluateNumber2 = expression2.evaluateNumber(context);
        switch (K()[this.\u00c3.ordinal()]) {
            case 1: {
                return evaluateNumber + evaluateNumber2;
            }
            case 2: {
                return evaluateNumber - evaluateNumber2;
            }
            case 3: {
                return evaluateNumber * evaluateNumber2;
            }
            case 4: {
                return evaluateNumber / evaluateNumber2;
            }
            case 5: {
                return evaluateNumber % evaluateNumber2;
            }
            default: {
                return 0.0;
            }
        }
    }
    
    @Override
    public String toString() {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        switch (K()[this.\u00c3.ordinal()]) {
            case 1: {
                return String.valueOf(argument.toString()) + " + " + argument2.toString();
            }
            case 2: {
                return String.valueOf(argument.toString()) + " - " + argument2.toString();
            }
            case 3: {
                return String.valueOf(argument.toString()) + " * " + argument2.toString();
            }
            case 4: {
                return String.valueOf(argument.toString()) + " / " + argument2.toString();
            }
            case 5: {
                return String.valueOf(argument.toString()) + " % " + argument2.toString();
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    protected IExpression cloneOne() {
        return new ArithmeticExpression(this.\u00c3);
    }
    
    static /* synthetic */ int[] K() {
        final int[] \u00e2 = ArithmeticExpression.\u00c2;
        if (\u00e2 != null) {
            return \u00e2;
        }
        final int[] \u00e22 = new int[Operator.values().length];
        try {
            \u00e22[Operator.ADD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00e22[Operator.DIV.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00e22[Operator.MOD.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00e22[Operator.MUL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00e22[Operator.SUB.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return ArithmeticExpression.\u00c2 = \u00e22;
    }
    
    public enum Operator
    {
        ADD("ADD", 0), 
        SUB("SUB", 1), 
        MUL("MUL", 2), 
        DIV("DIV", 3), 
        MOD("MOD", 4);
        
        static {
            A = new Operator[] { Operator.ADD, Operator.SUB, Operator.MUL, Operator.DIV, Operator.MOD };
        }
        
        private Operator(final String s, final int n) {
        }
    }
}
