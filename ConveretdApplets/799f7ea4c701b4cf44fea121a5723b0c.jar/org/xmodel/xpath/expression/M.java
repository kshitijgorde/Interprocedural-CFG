// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModelObject;
import java.util.List;

public abstract class M extends L
{
    private H \u00d2;
    
    protected M() {
        this.\u00d2 = new H();
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        return this.B(context, this.getArgument(0), this.getArgument(1));
    }
    
    protected abstract boolean B(final IContext p0, final IExpression p1, final IExpression p2) throws ExpressionException;
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.H(context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.H(context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        try {
            final IExpression argument = this.getArgument(0);
            final IExpression argument2 = this.getArgument(1);
            if (expression == argument) {
                context.getModel().revert();
                this.\u00d2.A(!b);
                final boolean b2 = this.B(context, this.\u00d2, argument2);
                context.getModel().restore();
                this.\u00d2.A(b);
                final boolean b3 = this.B(context, this.\u00d2, argument2);
                if (b2 != b3) {
                    this.k.notifyChange(this, context, b3);
                }
            }
            else {
                context.getModel().revert();
                this.\u00d2.A(!b);
                final boolean b4 = this.B(context, argument, this.\u00d2);
                context.getModel().restore();
                this.\u00d2.A(b);
                final boolean b5 = this.B(context, argument, this.\u00d2);
                if (b4 != b5) {
                    this.k.notifyChange(this, context, b5);
                }
            }
        }
        catch (ExpressionException ex) {
            this.k.handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        try {
            final IExpression argument = this.getArgument(0);
            final IExpression argument2 = this.getArgument(1);
            if (expression == argument) {
                context.getModel().revert();
                this.\u00d2.A(n2);
                final boolean b = this.B(context, this.\u00d2, argument2);
                context.getModel().restore();
                this.\u00d2.A(n);
                final boolean b2 = this.B(context, this.\u00d2, argument2);
                if (b != b2) {
                    this.k.notifyChange(this, context, b2);
                }
            }
            else {
                context.getModel().revert();
                this.\u00d2.A(n2);
                final boolean b3 = this.B(context, argument, this.\u00d2);
                context.getModel().restore();
                this.\u00d2.A(n);
                final boolean b4 = this.B(context, argument, this.\u00d2);
                if (b3 != b4) {
                    this.k.notifyChange(this, context, b4);
                }
            }
        }
        catch (ExpressionException ex) {
            this.k.handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        try {
            final IExpression argument = this.getArgument(0);
            final IExpression argument2 = this.getArgument(1);
            if (expression == argument) {
                context.getModel().revert();
                this.\u00d2.A(s2);
                final boolean b = this.B(context, this.\u00d2, argument2);
                context.getModel().restore();
                this.\u00d2.A(s);
                final boolean b2 = this.B(context, this.\u00d2, argument2);
                if (b != b2) {
                    this.k.notifyChange(this, context, b2);
                }
            }
            else {
                context.getModel().revert();
                this.\u00d2.A(s2);
                final boolean b3 = this.B(context, argument, this.\u00d2);
                context.getModel().restore();
                this.\u00d2.A(s);
                final boolean b4 = this.B(context, argument, this.\u00d2);
                if (b3 != b4) {
                    this.k.notifyChange(this, context, b4);
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
        this.H(array[0]);
    }
    
    private void H(final IContext context) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        context.getModel().revert();
        final boolean b = this.B(context, argument, argument2);
        context.getModel().restore();
        final boolean b2 = this.B(context, argument, argument2);
        if (b != b2) {
            this.k.notifyChange(this, context, b2);
        }
    }
}
