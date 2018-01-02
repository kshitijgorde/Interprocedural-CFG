// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xpath;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xidget.util.DateFormat;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class FormatDateFunction extends Function
{
    public static final String name = "xi:format-date";
    
    @Override
    public String getName() {
        return "xi:format-date";
    }
    
    @Override
    public ResultType getType() {
        return ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        return new DateFormat().format(this.getArgument(0).evaluateString(context), (long)this.getArgument(1).evaluateNumber(context));
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
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression argument = this.getArgument(0);
        if (expression == this.getArgument(1)) {
            final DateFormat dateFormat = new DateFormat();
            final String evaluateString = argument.evaluateString(context);
            this.getParent().notifyChange(this, context, dateFormat.format(evaluateString, (long)n), dateFormat.format(evaluateString, (long)n2));
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (expression == argument) {
            final DateFormat dateFormat = new DateFormat();
            final long n = (long)argument2.evaluateNumber(context);
            this.getParent().notifyChange(this, context, dateFormat.format(s, n), dateFormat.format(s2, n));
        }
        else {
            final DateFormat dateFormat2 = new DateFormat();
            final String evaluateString = argument.evaluateString(context);
            final double double1 = this.parseDouble(s2);
            final String s3 = (double1 == Double.MIN_VALUE) ? "" : dateFormat2.format(evaluateString, (long)double1);
            final double double2 = this.parseDouble(s);
            this.getParent().notifyChange(this, context, (double2 == Double.MIN_VALUE) ? "" : dateFormat2.format(evaluateString, (long)double2), s3);
        }
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.notifyChange(expression, array[0], (o != null) ? o.toString() : "", (o2 != null) ? o2.toString() : "");
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return true;
    }
    
    private double parseDouble(final String s) {
        try {
            return Double.parseDouble(s);
        }
        catch (Exception ex) {
            return Double.MIN_VALUE;
        }
    }
}
