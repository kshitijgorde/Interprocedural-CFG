// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.xmodel.IModelObject;
import java.util.List;

public class G extends L
{
    public G(final IExpression expression, final IExpression expression2) {
        this.addArgument(expression);
        this.addArgument(expression2);
    }
    
    @Override
    public String getName() {
        return "union";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final LinkedHashSet<Object> set = new LinkedHashSet<Object>();
        set.addAll(argument.evaluateNodes(context));
        set.addAll(argument2.evaluateNodes(context));
        return new ArrayList<IModelObject>((Collection<? extends IModelObject>)set);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        return argument.evaluateBoolean(context) || argument2.evaluateBoolean(context);
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        this.getArgument(0).createSubtree(context, modelObjectFactory, set);
        this.getArgument(1).createSubtree(context, modelObjectFactory, set);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.k != null) {
            this.k.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.k != null) {
            this.k.notifyChange(this, context);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return this.k != null && this.k.requiresValueNotification(this);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (this.k != null) {
            this.k.notifyValue(this, array, modelObject, o, o2);
        }
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.getArgument(0).toString()) + " | " + this.getArgument(1).toString();
    }
}
