// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class StringJoinFunction extends Function
{
    public static final String name = "string-join";
    
    @Override
    public String getName() {
        return "string-join";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        this.assertType(context, 1, IExpression.ResultType.STRING);
        return this.A(this.getArgument(0).evaluateNodes(context), this.getArgument(1).evaluateString(context));
    }
    
    private String A(final List<IModelObject> list, final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() - 1; ++i) {
            sb.append(Xlate.get((IModelObject)list.get(i), ""));
            sb.append(s);
        }
        if (list.size() > 0) {
            sb.append(Xlate.get((IModelObject)list.get(list.size() - 1), ""));
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
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.getParent().notifyChange(this, array[i]);
        }
    }
}
