// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xpath;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class CapitalizeFunction extends Function
{
    public static final String name = "xi:capitalize";
    
    @Override
    public String getName() {
        return "xi:capitalize";
    }
    
    @Override
    public ResultType getType() {
        return ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final String evaluateString = this.getArgument(0).evaluateString(context);
        final StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(evaluateString.charAt(0)));
        sb.append(evaluateString, 1, evaluateString.length());
        return sb.toString();
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
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
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyChange(this, array[0]);
    }
}
