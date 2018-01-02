// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.IModel;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class NosyncFunction extends Function
{
    public static final String name = "nosync";
    
    @Override
    public String getName() {
        return "nosync";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return this.getArgument(0).getType();
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        return this.getArgument(0).getType(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IModel model = context.getModel();
        try {
            model.setSyncLock(true);
            return this.getArgument(0).evaluateBoolean(context);
        }
        finally {
            model.setSyncLock(false);
        }
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IModel model = context.getModel();
        try {
            model.setSyncLock(true);
            return this.getArgument(0).evaluateNodes(context);
        }
        finally {
            model.setSyncLock(false);
        }
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IModel model = context.getModel();
        try {
            model.setSyncLock(true);
            return this.getArgument(0).evaluateNumber(context);
        }
        finally {
            model.setSyncLock(false);
        }
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IModel model = context.getModel();
        try {
            model.setSyncLock(true);
            return this.getArgument(0).evaluateString(context);
        }
        finally {
            model.setSyncLock(false);
        }
    }
    
    @Override
    public void bind(final IContext context) {
        final IModel model = context.getModel();
        try {
            model.setSyncLock(true);
            super.bind(context);
        }
        finally {
            model.setSyncLock(false);
        }
        model.setSyncLock(false);
    }
    
    @Override
    public void unbind(final IContext context) {
        final IModel model = context.getModel();
        try {
            model.setSyncLock(true);
            super.unbind(context);
        }
        finally {
            model.setSyncLock(false);
        }
        model.setSyncLock(false);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyAdd(this, context, list);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyRemove(this, context, list);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, b);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, n, n2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context, s, s2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        final IExpression parent = this.getParent();
        if (parent != null) {
            parent.notifyChange(this, context);
        }
    }
}
