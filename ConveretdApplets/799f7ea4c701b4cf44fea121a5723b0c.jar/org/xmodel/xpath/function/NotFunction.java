// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class NotFunction extends Function
{
    public NotFunction() {
    }
    
    public NotFunction(final IExpression expression) {
        this.addArgument(expression);
    }
    
    @Override
    public String getName() {
        return "not";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IExpression.ResultType type = this.getArgument(0).getType(context);
        if (type == IExpression.ResultType.NUMBER || type == IExpression.ResultType.STRING) {
            throw new ExpressionException(this, "Illegal argument type: " + type + " in expression: " + this.toString());
        }
        return !this.getArgument(0).evaluateBoolean(context);
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
    public boolean requiresValueNotification(final IExpression expression) {
        return false;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.getParent().notifyChange(this, context, !b);
    }
}
