// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Collections;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class ReverseFunction extends Function
{
    @Override
    public String getName() {
        return "reverse";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        Collections.reverse(evaluateNodes);
        return evaluateNodes;
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list);
            Collections.reverse(list2);
            parent.notifyAdd(this, context, list2);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list);
            Collections.reverse(list2);
            parent.notifyRemove(this, context, list2);
        }
    }
}
