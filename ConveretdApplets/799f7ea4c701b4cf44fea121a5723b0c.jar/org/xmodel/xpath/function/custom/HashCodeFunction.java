// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.List;
import org.xmodel.util.Radix;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class HashCodeFunction extends Function
{
    public static final String name = "hash-code";
    
    @Override
    public String getName() {
        return "hash-code";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, IExpression.ResultType.NODES);
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        if (evaluateNodes.size() == 0) {
            return "";
        }
        return Radix.convert(evaluateNodes.get(0).hashCode(), 10);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
}
