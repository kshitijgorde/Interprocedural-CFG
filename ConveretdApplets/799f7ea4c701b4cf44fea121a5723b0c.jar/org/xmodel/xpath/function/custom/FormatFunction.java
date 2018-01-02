// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Formatter;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class FormatFunction extends Function
{
    public static final String name = "format";
    private static /* synthetic */ int[] p;
    
    @Override
    public String getName() {
        return "format";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, -1);
        this.assertType(context, 0, IExpression.ResultType.STRING);
        final String b = this.B(this.getArgument(0).evaluateString(context));
        final Object[] array = new Object[this.getArguments().size() - 1];
        for (int i = 0; i < array.length; ++i) {
            final IExpression argument = this.getArgument(i + 1);
            switch (I()[argument.getType(context).ordinal()]) {
                case 2: {
                    array[i] = argument.evaluateString(context);
                    break;
                }
                case 3: {
                    array[i] = argument.evaluateNumber(context);
                    break;
                }
                case 4: {
                    array[i] = argument.evaluateBoolean(context);
                    break;
                }
                case 1: {
                    array[i] = argument.evaluateString(context);
                    break;
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        new Formatter(sb).format(b, array);
        return sb.toString();
    }
    
    private String B(final String s) {
        int n = 0;
        boolean b = false;
        boolean b2 = false;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (n != 0) {
                if (char1 == '\\') {
                    n = 0;
                    sb.append('\\');
                }
                else if (char1 == 'n') {
                    n = 0;
                    sb.append('\n');
                }
                else if (char1 == 'f') {
                    n = 0;
                    sb.append('\f');
                }
                else if (char1 == 'r') {
                    n = 0;
                    sb.append('\r');
                }
                else if (char1 == 't') {
                    n = 0;
                    sb.append('\t');
                }
                else if (char1 == 'b') {
                    n = 0;
                    sb.append('\b');
                }
                else if (char1 == 'u') {
                    n = 0;
                    b = true;
                }
                else {
                    if (!Character.isDigit(char1)) {
                        throw new ExpressionException(this, "Unrecognized escape character.");
                    }
                    n = 0;
                    b2 = true;
                }
            }
            else {
                if (b) {
                    throw new ExpressionException(this, "Unicode character expansion not supported.");
                }
                if (b2) {
                    throw new ExpressionException(this, "Octal character expansion not supported.");
                }
                if (char1 == '\\') {
                    n = 1;
                }
                else {
                    sb.append(char1);
                }
            }
        }
        return sb.toString();
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyChange(this, array[0]);
    }
    
    static /* synthetic */ int[] I() {
        final int[] p = FormatFunction.p;
        if (p != null) {
            return p;
        }
        final int[] p2 = new int[IExpression.ResultType.values().length];
        try {
            p2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            p2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            p2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            p2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            p2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return FormatFunction.p = p2;
    }
}
