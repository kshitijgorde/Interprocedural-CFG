// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class UppercaseFunction extends Function
{
    public static final String name = "upper-case";
    
    @Override
    public String getName() {
        return "upper-case";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.getArgument(0).evaluateString(context).toUpperCase();
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        context.getModel().revert();
        final String upperCase = s2.toUpperCase();
        final String upperCase2 = s.toUpperCase();
        context.getModel().restore();
        if (!upperCase2.equals(upperCase)) {
            this.getParent().notifyChange(this, context, upperCase2, upperCase);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.getParent().notifyChange(this, context);
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
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyValue(this, array, modelObject, o, o2);
    }
}
