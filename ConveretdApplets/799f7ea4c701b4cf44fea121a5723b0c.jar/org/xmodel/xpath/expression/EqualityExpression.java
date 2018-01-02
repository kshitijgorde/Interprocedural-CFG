// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.xpath.function.NumberFunction;
import org.xmodel.xpath.function.StringFunction;
import org.xmodel.IModelObject;
import java.util.List;

public class EqualityExpression extends M
{
    Operator \u00d3;
    private static /* synthetic */ int[] \u00d4;
    
    public EqualityExpression(final Operator \u00f3) {
        this.\u00d3 = \u00f3;
    }
    
    public EqualityExpression(final Operator \u00f3, final IExpression expression, final IExpression expression2) {
        this.\u00d3 = \u00f3;
        this.addArgument(expression);
        this.addArgument(expression2);
    }
    
    @Override
    public String getName() {
        return "equality";
    }
    
    @Override
    protected boolean B(final IContext context, final IExpression expression, final IExpression expression2) throws ExpressionException {
        final IExpression.ResultType type = expression.getType(context);
        final IExpression.ResultType type2 = expression2.getType(context);
        if (type == IExpression.ResultType.NODES) {
            if (type2 == IExpression.ResultType.BOOLEAN) {
                return this.A(expression.evaluateBoolean(context), expression2.evaluateBoolean(context));
            }
            if (type2 == IExpression.ResultType.NODES) {
                return this.A(expression.evaluateNodes(context), expression2.evaluateNodes(context));
            }
            if (type2 == IExpression.ResultType.NUMBER) {
                return this.A(expression.evaluateNodes(context), expression2.evaluateNumber(context));
            }
            return this.B(expression.evaluateNodes(context), expression2.evaluateString(context));
        }
        else if (type2 == IExpression.ResultType.NODES) {
            if (type == IExpression.ResultType.BOOLEAN) {
                return this.A(expression2.evaluateBoolean(context), expression.evaluateBoolean(context));
            }
            if (type == IExpression.ResultType.NODES) {
                return this.A(expression2.evaluateNodes(context), expression.evaluateNodes(context));
            }
            if (type == IExpression.ResultType.NUMBER) {
                return this.A(expression2.evaluateNodes(context), expression.evaluateNumber(context));
            }
            return this.B(expression2.evaluateNodes(context), expression.evaluateString(context));
        }
        else {
            if (type == IExpression.ResultType.BOOLEAN || type2 == IExpression.ResultType.BOOLEAN) {
                return this.A(expression.evaluateBoolean(context), expression2.evaluateBoolean(context));
            }
            if (type == IExpression.ResultType.NUMBER || type2 == IExpression.ResultType.NUMBER) {
                return this.A(expression.evaluateNumber(context), expression2.evaluateNumber(context));
            }
            return this.C(expression.evaluateString(context), expression2.evaluateString(context));
        }
    }
    
    private boolean A(final List<IModelObject> list, final List<IModelObject> list2) {
        for (int i = 0; i < list.size(); ++i) {
            final String stringValue = StringFunction.stringValue(list.get(i));
            for (int j = 0; j < list2.size(); ++j) {
                final String stringValue2 = StringFunction.stringValue(list2.get(j));
                if (this.\u00d3 == Operator.EQ) {
                    if (stringValue.equals(stringValue2)) {
                        return true;
                    }
                }
                else if (!stringValue.equals(stringValue2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean A(final List<IModelObject> list, final double n) {
        for (int i = 0; i < list.size(); ++i) {
            final double numericValue = NumberFunction.numericValue(StringFunction.stringValue(list.get(i)));
            if (this.\u00d3 == Operator.EQ) {
                if (numericValue == n) {
                    return true;
                }
            }
            else if (numericValue != n) {
                return true;
            }
        }
        return false;
    }
    
    private boolean B(final List<IModelObject> list, final String s) {
        for (int i = 0; i < list.size(); ++i) {
            final String stringValue = StringFunction.stringValue(list.get(i));
            if (this.\u00d3 == Operator.EQ) {
                if (stringValue.equals(s)) {
                    return true;
                }
            }
            else if (!stringValue.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean A(final boolean b, final boolean b2) {
        boolean b3 = b == b2;
        if (this.\u00d3 == Operator.NEQ) {
            b3 = !b3;
        }
        return b3;
    }
    
    private boolean A(final double n, final double n2) {
        boolean b = n == n2;
        if (this.\u00d3 == Operator.NEQ) {
            b = !b;
        }
        return b;
    }
    
    private boolean C(final String s, final String s2) {
        boolean equals = s.equals(s2);
        if (this.\u00d3 == Operator.NEQ) {
            equals = !equals;
        }
        return equals;
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        this.getArgument(0).createSubtree(context, modelObjectFactory, set);
        if (this.\u00d3 == Operator.EQ) {
            final IModelObject queryFirst = this.queryFirst(context);
            if (queryFirst != null) {
                queryFirst.setValue(this.getArgument(1).evaluateString(context));
            }
        }
    }
    
    @Override
    public String toString() {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        switch (M()[this.\u00d3.ordinal()]) {
            case 1: {
                return String.valueOf(argument.toString()) + " = " + argument2.toString();
            }
            case 2: {
                return String.valueOf(argument.toString()) + " != " + argument2.toString();
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    protected IExpression cloneOne() {
        return new EqualityExpression(this.\u00d3);
    }
    
    static /* synthetic */ int[] M() {
        final int[] \u00f4 = EqualityExpression.\u00d4;
        if (\u00f4 != null) {
            return \u00f4;
        }
        final int[] \u00f42 = new int[Operator.values().length];
        try {
            \u00f42[Operator.EQ.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00f42[Operator.NEQ.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        return EqualityExpression.\u00d4 = \u00f42;
    }
    
    public enum Operator
    {
        EQ("EQ", 0), 
        NEQ("NEQ", 1);
        
        static {
            A = new Operator[] { Operator.EQ, Operator.NEQ };
        }
        
        private Operator(final String s, final int n) {
        }
    }
}
