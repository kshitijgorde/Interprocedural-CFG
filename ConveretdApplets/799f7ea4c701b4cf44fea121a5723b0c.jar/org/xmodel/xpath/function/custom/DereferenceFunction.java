// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.ModelAlgorithms;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class DereferenceFunction extends Function
{
    public static final String name = "dereference";
    
    @Override
    public String getName() {
        return "dereference";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, IExpression.ResultType.NODES);
        final ArrayList<Object> list = new ArrayList<Object>(this.getArgument(0).evaluateNodes(context));
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, ModelAlgorithms.dereference(list.get(i)));
        }
        return (List<IModelObject>)list;
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final ArrayList list2 = new ArrayList<Object>(list);
        for (int i = 0; i < list2.size(); ++i) {
            list2.set(i, ModelAlgorithms.dereference((IModelObject)list2.get(i)));
        }
        this.getParent().notifyAdd(this, context, list2);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final ArrayList list2 = new ArrayList<Object>(list);
        for (int i = 0; i < list2.size(); ++i) {
            list2.set(i, ModelAlgorithms.dereference((IModelObject)list2.get(i)));
        }
        this.getParent().notifyRemove(this, context, list2);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyValue(this, array, ModelAlgorithms.dereference(modelObject), o, o2);
    }
}
