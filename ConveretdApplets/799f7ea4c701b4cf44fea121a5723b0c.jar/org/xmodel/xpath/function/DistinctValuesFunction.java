// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class DistinctValuesFunction extends Function
{
    @Override
    public String getName() {
        return "distinct-values";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, IExpression.ResultType.NODES);
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        final ArrayList list = new ArrayList<IModelObject>(evaluateNodes.size());
        final HashMap hashMap = new HashMap<Object, IModelObject>(evaluateNodes.size());
        for (final IModelObject modelObject : evaluateNodes) {
            final Object value = modelObject.getValue();
            if (hashMap.get(value) == null) {
                hashMap.put(value, modelObject);
                list.add(modelObject);
            }
        }
        return (List<IModelObject>)list;
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        final IExpression parent = this.getParent();
        return parent != null && parent.requiresValueNotification(this);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyValue(this, array, modelObject, o, o2);
        }
    }
}
