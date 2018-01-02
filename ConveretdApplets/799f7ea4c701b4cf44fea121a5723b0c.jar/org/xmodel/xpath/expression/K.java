// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.IModelObject;
import java.util.List;

public class K extends L
{
    @Override
    public String getName() {
        return "if-then-else";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return this.getArgument(1).getType();
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        return this.getArgument(1).getType(context);
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertType(context, 2, this.getArgument(1).getType(context));
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        if (argument.evaluateBoolean(context)) {
            return argument2.evaluateNodes(context);
        }
        return argument3.evaluateNodes(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertType(context, 2, this.getArgument(1).getType(context));
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        if (argument.evaluateBoolean(context)) {
            return argument2.evaluateBoolean(context);
        }
        return argument3.evaluateBoolean(context);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertType(context, 2, this.getArgument(1).getType(context));
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        if (argument.evaluateBoolean(context)) {
            return argument2.evaluateNumber(context);
        }
        return argument3.evaluateNumber(context);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertType(context, 2, this.getArgument(1).getType(context));
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        if (argument.evaluateBoolean(context)) {
            return argument2.evaluateString(context);
        }
        return argument3.evaluateString(context);
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        if (this.getArgument(0).evaluateBoolean(context)) {
            this.getArgument(1).createSubtree(context, modelObjectFactory, set);
        }
        else {
            this.getArgument(2).createSubtree(context, modelObjectFactory, set);
        }
    }
    
    @Override
    public void bind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        argument.bind(context);
        try {
            if (argument.evaluateBoolean(context)) {
                this.getArgument(1).bind(context);
            }
            else {
                this.getArgument(2).bind(context);
            }
        }
        catch (ExpressionException ex) {
            if (this.k != null) {
                this.k.handleException(this, context, ex);
            }
        }
    }
    
    @Override
    public void rebind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression argument3 = this.getArgument(2);
        boolean b = false;
        final boolean evaluateBoolean = argument.evaluateBoolean(context);
        try {
            context.getModel().revert();
            final boolean evaluateBoolean2 = argument.evaluateBoolean(context);
            if (evaluateBoolean != evaluateBoolean2) {
                if (evaluateBoolean2) {
                    argument2.unbind(context);
                }
                else {
                    argument3.unbind(context);
                }
                b = true;
            }
        }
        finally {
            context.getModel().restore();
        }
        context.getModel().restore();
        if (b) {
            if (evaluateBoolean) {
                argument2.bind(context);
            }
            else {
                argument3.bind(context);
            }
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (expression == this.getArgument(0)) {
            this.rebind(context);
            this.notifyChange(this, context);
        }
        else {
            this.k.notifyAdd(this, context, list);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (expression == this.getArgument(0)) {
            this.rebind(context);
            this.notifyChange(this, context);
        }
        else {
            this.k.notifyRemove(this, context, list);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        if (expression == this.getArgument(0)) {
            this.rebind(context);
            this.notifyChange(this, context);
        }
        else {
            this.k.notifyChange(this, context, b);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        if (expression == this.getArgument(0)) {
            this.rebind(context);
            this.notifyChange(this, context);
        }
        else {
            this.k.notifyChange(this, context, n, n2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        if (expression == this.getArgument(0)) {
            this.rebind(context);
            this.notifyChange(this, context);
        }
        else {
            this.k.notifyChange(this, context, s, s2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        if (expression == this.getArgument(0)) {
            this.rebind(context);
        }
        this.k.notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (expression == this.getArgument(0)) {
            this.rebind(array[0]);
        }
        this.k.notifyValue(this, array, modelObject, o, o2);
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("if (");
        sb.append(this.getArgument(0).toString());
        sb.append(") then (");
        sb.append(this.getArgument(1).toString());
        sb.append(") else (");
        sb.append(this.getArgument(2).toString());
        sb.append(')');
        return sb.toString();
    }
}
