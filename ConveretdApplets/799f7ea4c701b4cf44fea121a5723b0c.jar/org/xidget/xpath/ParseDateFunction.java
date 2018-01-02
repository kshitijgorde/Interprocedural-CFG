// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xpath;

import java.text.ParseException;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xidget.util.DateFormat;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class ParseDateFunction extends Function
{
    public static final String name = "xi:parse-date";
    
    @Override
    public String getName() {
        return "xi:parse-date";
    }
    
    @Override
    public ResultType getType() {
        return ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        try {
            return new DateFormat().parse(argument.evaluateString(context), argument2.evaluateString(context));
        }
        catch (Exception ex) {
            throw new ExpressionException(this, "Unable to parse date.", ex);
        }
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
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (expression == argument) {
            final DateFormat dateFormat = new DateFormat();
            final String evaluateString = argument2.evaluateString(context);
            try {
                this.getParent().notifyChange(this, context, dateFormat.parse(s, evaluateString), dateFormat.parse(s2, evaluateString));
            }
            catch (ParseException ex) {}
        }
        else {
            final DateFormat dateFormat2 = new DateFormat();
            final String evaluateString2 = argument.evaluateString(context);
            try {
                this.getParent().notifyChange(this, context, dateFormat2.parse(evaluateString2, s), dateFormat2.parse(evaluateString2, s2));
            }
            catch (ParseException ex2) {}
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
}
