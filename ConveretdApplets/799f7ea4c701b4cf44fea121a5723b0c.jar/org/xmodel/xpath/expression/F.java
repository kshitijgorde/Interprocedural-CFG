// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.HashSet;
import org.xmodel.IModel;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableSource;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.variable.VariableScope;
import org.xmodel.xpath.variable.IVariableScope;

public class F extends L
{
    String \u00c6;
    IVariableScope \u00c7;
    
    public F(final String \u00e6) {
        this.\u00c6 = \u00e6;
        this.\u00c7 = new VariableScope("for", 3);
    }
    
    @Override
    public String getName() {
        return "for-return";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(this.\u00c7);
            final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
            final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<IModelObject>();
            final Iterator<IModelObject> iterator = evaluateNodes.iterator();
            while (iterator.hasNext()) {
                this.\u00c7.set(this.\u00c6, iterator.next());
                this.assertType(context, 1, IExpression.ResultType.NODES);
                list.addAll(argument2.evaluateNodes(context));
            }
            return (List<IModelObject>)list;
        }
        finally {
            variableSource.removeScope(this.\u00c7);
        }
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            argument2.createSubtree(new O(context, this.\u00c6, evaluateNodes.get(i)), modelObjectFactory, set);
        }
    }
    
    @Override
    public void bind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        argument.bind(context);
        try {
            final Iterator<IModelObject> iterator = argument.evaluateNodes(context).iterator();
            while (iterator.hasNext()) {
                argument2.bind(new O(context, this.\u00c6, iterator.next()));
            }
        }
        catch (ExpressionException ex) {
            if (this.k != null) {
                this.k.handleException(this, context, ex);
            }
        }
    }
    
    @Override
    public void unbind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        argument.unbind(context);
        try {
            final Iterator<IModelObject> iterator = argument.evaluateNodes(context).iterator();
            while (iterator.hasNext()) {
                argument2.unbind(new O(context, this.\u00c6, iterator.next()));
            }
        }
        catch (ExpressionException ex) {
            if (this.k != null) {
                this.k.handleException(this, context, ex);
            }
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (expression == argument) {
            final Iterator<IModelObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                final O o = new O(context, this.\u00c6, iterator.next());
                try {
                    argument2.bind(o);
                    final List<IModelObject> evaluateNodes = argument2.evaluateNodes(o);
                    if (evaluateNodes.size() <= 0) {
                        continue;
                    }
                    this.k.notifyAdd(this, context, evaluateNodes);
                }
                catch (ExpressionException ex) {
                    this.k.handleException(this, context, ex);
                }
            }
        }
        else {
            if (expression != argument2) {
                throw new IllegalStateException("Notification from expression which is not an argument.");
            }
            if (this.k != null) {
                this.k.notifyAdd(this, context.getParent(), list);
            }
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (expression == argument) {
            final IModel model = context.getModel();
            for (final IModelObject modelObject : list) {
                model.revert();
                final O o = new O(context, this.\u00c6, modelObject);
                try {
                    argument2.unbind(o);
                    final List<IModelObject> evaluateNodes = argument2.evaluateNodes(o);
                    model.restore();
                    if (this.k == null || evaluateNodes.size() <= 0) {
                        continue;
                    }
                    this.k.notifyRemove(this, context, evaluateNodes);
                }
                catch (ExpressionException ex) {
                    if (this.k == null) {
                        continue;
                    }
                    this.k.handleException(this, context, ex);
                }
            }
        }
        else {
            if (expression != argument2) {
                throw new IllegalStateException("Notification from expression which is not an argument.");
            }
            if (this.k != null) {
                this.k.notifyRemove(this, context.getParent(), list);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, IContext parent) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        parent.getModel().revert();
        Object evaluateNodes = argument.evaluateNodes(parent, null);
        if (((Collection)evaluateNodes).size() > 5) {
            evaluateNodes = new HashSet<Object>((Collection<?>)evaluateNodes);
        }
        parent.getModel().restore();
        Object evaluateNodes2 = argument.evaluateNodes(parent, null);
        if (((Collection)evaluateNodes2).size() > 5) {
            evaluateNodes2 = new HashSet<Object>((Collection<?>)evaluateNodes2);
        }
        parent.getModel().revert();
        for (final IModelObject modelObject : evaluateNodes) {
            if (!((Collection)evaluateNodes2).contains(modelObject)) {
                argument2.unbind(new O(parent, this.\u00c6, modelObject));
            }
        }
        parent.getModel().restore();
        for (final IModelObject modelObject2 : evaluateNodes2) {
            if (!((Collection)evaluateNodes).contains(modelObject2)) {
                argument2.bind(new O(parent, this.\u00c6, modelObject2));
            }
        }
        if (this.k != null) {
            if (expression == argument2) {
                parent = parent.getParent();
            }
            this.k.notifyChange(this, parent);
        }
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (expression == this.getArgument(0)) {
            super.notifyValue(expression, array, modelObject, o, o2);
        }
        else {
            final IContext[] array2 = new IContext[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i].getParent();
            }
            super.notifyValue(expression, array2, modelObject, o, o2);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return expression == this.getArgument(0) || (this.k.requiresValueNotification(this) && expression == this.getArgument(1));
    }
    
    @Override
    protected IExpression cloneOne() {
        return new F(this.\u00c6);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("for ");
        sb.append('$');
        sb.append(this.\u00c6);
        sb.append(" in ");
        sb.append(this.getArgument(0));
        sb.append(" return\n");
        sb.append("  ");
        sb.append(this.getArgument(1));
        return sb.toString();
    }
}
