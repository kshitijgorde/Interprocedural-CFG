// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class NumberFunction extends Function
{
    private static /* synthetic */ int[] z;
    
    public NumberFunction() {
    }
    
    public NumberFunction(final IExpression expression) {
        this.addArgument(expression);
    }
    
    @Override
    public String getName() {
        return "number";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IExpression argument = this.getArgument(0);
        switch (I()[argument.getType(context).ordinal()]) {
            case 1: {
                return numericValue(argument.evaluateNodes(context));
            }
            case 3: {
                return argument.evaluateNumber(context);
            }
            case 2: {
                return numericValue(argument.evaluateString(context));
            }
            case 4: {
                return numericValue(argument.evaluateBoolean(context));
            }
            default: {
                return 0.0;
            }
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.getParent() != null) {
            this.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.getParent() != null) {
            this.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, numericValue(b), numericValue(!b));
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, n, n2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            final double numericValue = numericValue(s2);
            final double numericValue2 = numericValue(s);
            if (numericValue2 != numericValue) {
                parent.notifyChange(this, context, numericValue2, numericValue);
            }
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return true;
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        final IExpression argument = this.getArgument(0);
        for (final IContext context : array) {
            final IExpression parent = this.getParent();
            if (parent != null) {
                try {
                    if (modelObject.equals(argument.evaluateNodes(context).get(0))) {
                        final double n = (o2 != null) ? numericValue(o2.toString()) : 0.0;
                        final double n2 = (o != null) ? numericValue(o.toString()) : 0.0;
                        if (n2 != n) {
                            parent.notifyChange(this, context, n2, n);
                        }
                    }
                }
                catch (ExpressionException ex) {
                    parent.handleException(this, context, ex);
                }
            }
        }
    }
    
    public static double numericValue(final IModelObject modelObject) {
        try {
            return Double.parseDouble(StringFunction.stringValue(modelObject));
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }
    
    public static double numericValue(final List<IModelObject> list) {
        try {
            if (list.size() == 0) {
                return 0.0;
            }
            return Double.parseDouble(StringFunction.stringValue(list.get(0)));
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }
    
    public static double numericValue(final String s) {
        try {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }
    
    public static double numericValue(final boolean b) {
        return b ? 1 : 0;
    }
    
    static /* synthetic */ int[] I() {
        final int[] z = NumberFunction.z;
        if (z != null) {
            return z;
        }
        final int[] z2 = new int[IExpression.ResultType.values().length];
        try {
            z2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            z2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            z2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            z2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            z2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return NumberFunction.z = z2;
    }
}
