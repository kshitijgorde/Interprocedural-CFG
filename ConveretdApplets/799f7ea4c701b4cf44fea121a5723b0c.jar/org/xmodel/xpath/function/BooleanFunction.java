// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class BooleanFunction extends Function
{
    public BooleanFunction() {
    }
    
    public BooleanFunction(final IExpression expression) {
        this.addArgument(expression);
    }
    
    @Override
    public String getName() {
        return "boolean";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.getArgument(0).evaluateBoolean(context);
    }
    
    public static boolean booleanValue(final List<IModelObject> list) {
        return list.size() > 0;
    }
    
    public static boolean booleanValue(final double n) {
        return n != 0.0;
    }
    
    public static boolean booleanValue(final String s) {
        return s.length() > 0;
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
            parent.notifyChange(this, context, b);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            final boolean booleanValue = booleanValue(n2);
            final boolean booleanValue2 = booleanValue(n);
            if (booleanValue2 != booleanValue) {
                parent.notifyChange(this, context, booleanValue2);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            final boolean booleanValue = booleanValue(s2);
            final boolean booleanValue2 = booleanValue(s);
            if (booleanValue2 != booleanValue) {
                parent.notifyChange(this, context, booleanValue2);
            }
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return false;
    }
}
