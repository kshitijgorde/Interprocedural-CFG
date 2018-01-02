// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class StringFunction extends Function
{
    private static /* synthetic */ int[] x;
    
    public StringFunction() {
    }
    
    public StringFunction(final IExpression expression) {
        this.addArgument(expression);
    }
    
    @Override
    public String getName() {
        return "string";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(0, 1);
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            return stringValue(context.getObject());
        }
        switch (I()[argument.getType(context).ordinal()]) {
            case 1: {
                return stringValue(argument.evaluateNodes(context));
            }
            case 4: {
                return stringValue(argument.evaluateBoolean(context));
            }
            case 3: {
                return stringValue(argument.evaluateNumber(context));
            }
            case 2: {
                return argument.evaluateString(context);
            }
            default: {
                return "";
            }
        }
    }
    
    public static String stringValue(final IModelObject modelObject) {
        final Object value = modelObject.getValue();
        if (value != null) {
            return value.toString();
        }
        return "";
    }
    
    public static String stringValue(final List<IModelObject> list) {
        if (list.size() == 0) {
            return "";
        }
        return stringValue(list.get(0));
    }
    
    public static String stringValue(final double n) {
        String s = Double.toString(n);
        if (s.endsWith(".0")) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }
    
    public static String stringValue(final boolean b) {
        return Boolean.toString(b);
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
            parent.notifyChange(this, context, stringValue(b), stringValue(!b));
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, stringValue(n), stringValue(n2));
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, s, s2);
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
                        parent.notifyChange(this, context, (o != null) ? o.toString() : "", (o2 != null) ? o2.toString() : "");
                    }
                }
                catch (ExpressionException ex) {
                    parent.handleException(this, context, ex);
                }
            }
        }
    }
    
    static /* synthetic */ int[] I() {
        final int[] x = StringFunction.x;
        if (x != null) {
            return x;
        }
        final int[] x2 = new int[IExpression.ResultType.values().length];
        try {
            x2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            x2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            x2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            x2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            x2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return StringFunction.x = x2;
    }
}
