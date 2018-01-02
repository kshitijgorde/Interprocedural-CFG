// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import org.xmodel.IModel;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.diff.ListDiffer;
import org.xmodel.IModelObject;
import java.util.List;

public abstract class ExactExpressionListener extends ExpressionListener
{
    public abstract void notifyInsert(final IExpression p0, final IContext p1, final List<IModelObject> p2, final int p3, final int p4);
    
    public abstract void notifyRemove(final IExpression p0, final IContext p1, final List<IModelObject> p2, final int p3, final int p4);
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (context.getLastUpdate(expression) == 0) {
            this.notifyInsert(expression, context, list, 0, list.size());
        }
        else {
            this.notifyChange(expression, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (context.getLastUpdate(expression) == 0) {
            this.notifyRemove(expression, context, list, 0, list.size());
        }
        else {
            this.notifyChange(expression, context);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        final IModel model = context.getModel();
        model.revert();
        final List<IModelObject> evaluateNodes = expression.evaluateNodes(context);
        model.restore();
        final List<IModelObject> evaluateNodes2 = expression.evaluateNodes(context);
        final ListDiffer listDiffer = new ListDiffer();
        listDiffer.diff(evaluateNodes, evaluateNodes2);
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        list.addAll((Collection<?>)evaluateNodes);
        for (final ListDiffer.Change change : listDiffer.getChanges()) {
            if (change.rIndex >= 0) {
                for (int i = 0; i < change.count; ++i) {
                    list.add(change.lIndex, evaluateNodes2.get(change.rIndex + i));
                }
                this.notifyInsert(expression, context, list, change.lIndex, change.count);
            }
            else {
                this.notifyRemove(expression, context, list, change.lIndex, change.count);
                for (int j = 0; j < change.count; ++j) {
                    list.remove(change.lIndex);
                }
            }
        }
    }
}
