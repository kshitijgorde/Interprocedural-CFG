// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModelObject;
import java.util.List;

public abstract class D extends L
{
    H \u00c1;
    
    protected D() {
        this.\u00c1 = new H();
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        return this.A(context, this.getArgument(0), this.getArgument(1));
    }
    
    protected abstract double A(final IContext p0, final IExpression p1, final IExpression p2) throws ExpressionException;
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.k != null) {
            this.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.k != null) {
            this.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        if (this.k == null) {
            return;
        }
        try {
            final IExpression argument = this.getArgument(0);
            final IExpression argument2 = this.getArgument(1);
            if (expression == argument) {
                context.getModel().revert();
                this.\u00c1.A(!b);
                final double a = this.A(context, this.\u00c1, argument2);
                context.getModel().restore();
                this.\u00c1.A(b);
                final double a2 = this.A(context, this.\u00c1, argument2);
                if (a != a2) {
                    this.k.notifyChange(this, context, a2, a);
                }
            }
            else {
                context.getModel().revert();
                this.\u00c1.A(!b);
                final double a3 = this.A(context, argument, this.\u00c1);
                context.getModel().restore();
                this.\u00c1.A(b);
                final double a4 = this.A(context, argument, this.\u00c1);
                if (a3 != a4) {
                    this.k.notifyChange(this, context, a4, a3);
                }
            }
        }
        catch (ExpressionException ex) {
            this.k.handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        if (this.k == null) {
            return;
        }
        try {
            final IExpression argument = this.getArgument(0);
            final IExpression argument2 = this.getArgument(1);
            if (expression == argument) {
                context.getModel().revert();
                this.\u00c1.A(n2);
                final double a = this.A(context, this.\u00c1, argument2);
                context.getModel().restore();
                this.\u00c1.A(n);
                final double a2 = this.A(context, this.\u00c1, argument2);
                if (a != a2) {
                    this.k.notifyChange(this, context, a2, a);
                }
            }
            else {
                context.getModel().revert();
                this.\u00c1.A(n2);
                final double a3 = this.A(context, argument, this.\u00c1);
                context.getModel().restore();
                this.\u00c1.A(n);
                final double a4 = this.A(context, argument, this.\u00c1);
                if (a3 != a4) {
                    this.k.notifyChange(this, context, a4, a3);
                }
            }
        }
        catch (ExpressionException ex) {
            this.k.handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        if (this.k == null) {
            return;
        }
        try {
            final IExpression argument = this.getArgument(0);
            final IExpression argument2 = this.getArgument(1);
            if (expression == argument) {
                context.getModel().revert();
                this.\u00c1.A(s2);
                final double a = this.A(context, this.\u00c1, argument2);
                context.getModel().restore();
                this.\u00c1.A(s);
                final double a2 = this.A(context, this.\u00c1, argument2);
                if (a != a2) {
                    this.k.notifyChange(this, context, a2, a);
                }
            }
            else {
                context.getModel().revert();
                this.\u00c1.A(s2);
                final double a3 = this.A(context, argument, this.\u00c1);
                context.getModel().restore();
                this.\u00c1.A(s);
                final double a4 = this.A(context, argument, this.\u00c1);
                if (a3 != a4) {
                    this.k.notifyChange(this, context, a4, a3);
                }
            }
        }
        catch (ExpressionException ex) {
            this.k.handleException(this, context, ex);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return true;
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.notifyChange(this, array[i]);
        }
    }
}
