// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class SumFunction extends Function
{
    @Override
    public String getName() {
        return "sum";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        double n = 0.0;
        final Iterator<IModelObject> iterator = this.getArgument(0).evaluateNodes(context).iterator();
        while (iterator.hasNext()) {
            n += NumberFunction.numericValue(iterator.next());
        }
        return n;
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
