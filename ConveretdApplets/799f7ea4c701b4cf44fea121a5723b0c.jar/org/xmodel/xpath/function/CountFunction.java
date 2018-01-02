// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class CountFunction extends Function
{
    @Override
    public String getName() {
        return "count";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, IExpression.ResultType.NODES);
        return this.getArgument(0).evaluateNodes(context).size();
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
}
