// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.variable.VariableScope;
import org.xmodel.xpath.variable.VariableSource;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.variable.IVariableSource;

public class P extends L
{
    IVariableSource \u00dd;
    ExpressionListenerList \u00dc;
    boolean \u00db;
    private static /* synthetic */ int[] \u00da;
    
    public P() {
    }
    
    public P(final IExpression expression) {
        this.addArgument(expression);
    }
    
    @Override
    public String getName() {
        return "root";
    }
    
    @Override
    public IExpression.ResultType getType() {
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            throw new IllegalStateException("RootExpression is missing child.");
        }
        return argument.getType();
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            throw new IllegalStateException("RootExpression is missing child.");
        }
        return argument.getType(context);
    }
    
    @Override
    public IExpression getRoot() {
        return this;
    }
    
    @Override
    public void internal_setParent(final IExpression expression) {
        super.internal_setParent(expression);
        System.err.println("warning: RootExpression was parented.");
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            throw new ExpressionException(this, "RootExpression has no arguments.");
        }
        return argument.evaluateBoolean(context);
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            throw new ExpressionException(this, "RootExpression has no arguments.");
        }
        return argument.evaluateNodes(context);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            throw new ExpressionException(this, "RootExpression has no arguments.");
        }
        return argument.evaluateNumber(context);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        if (argument == null) {
            throw new ExpressionException(this, "RootExpression has no arguments.");
        }
        return argument.evaluateString(context);
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        this.getArgument(0).createSubtree(context, modelObjectFactory, set);
    }
    
    @Override
    public IVariableScope getLocalScope() {
        return this.getVariableSource().getScope("local");
    }
    
    public void A(final IVariableSource \u00fd) {
        this.\u00dd = \u00fd;
    }
    
    @Override
    public IVariableSource getVariableSource() {
        if (this.\u00dd == null) {
            this.\u00dd = new VariableSource();
            if (this.\u00dd.getScope("local") == null) {
                this.\u00dd.addScope(new VariableScope("local", 1));
            }
        }
        return this.\u00dd;
    }
    
    @Override
    public void addListener(final IContext context, final IExpressionListener expressionListener) {
        if (context == null) {
            throw new IllegalArgumentException("Attempt to add listener on null context: path:" + this.toString());
        }
        if (this.\u00dc == null) {
            this.\u00dc = new ExpressionListenerList();
        }
        this.\u00dc.addListener(context, expressionListener);
        try {
            this.getVariableSource().addScope(context.getScope());
            this.bind(context);
        }
        finally {
            this.getVariableSource().removeScope(context.getScope());
        }
        this.getVariableSource().removeScope(context.getScope());
        context.notifyBind(this);
        context.notifyUpdate(this);
    }
    
    @Override
    public void removeListener(final IContext context, final IExpressionListener expressionListener) {
        if (context == null) {
            throw new IllegalArgumentException("Attempt to remove listener from null context: path:" + this.toString());
        }
        if (this.\u00dc != null) {
            this.\u00dc.removeListener(context, expressionListener);
            try {
                this.getVariableSource().addScope(context.getScope());
                this.unbind(context);
            }
            finally {
                this.getVariableSource().removeScope(context.getScope());
            }
            this.getVariableSource().removeScope(context.getScope());
            context.notifyUnbind(this);
        }
    }
    
    @Override
    public void addNotifyListener(final IContext context, final IExpressionListener expressionListener) {
        if (context == null) {
            throw new IllegalArgumentException("Attempt to add listener on null context: path:" + this.toString());
        }
        if (this.\u00dc == null) {
            this.\u00dc = new ExpressionListenerList();
        }
        this.\u00dc.addListener(context, expressionListener);
        try {
            this.getVariableSource().addScope(context.getScope());
            this.bind(context);
        }
        finally {
            this.getVariableSource().removeScope(context.getScope());
        }
        this.getVariableSource().removeScope(context.getScope());
        try {
            context.notifyBind(this);
            this.A(context, expressionListener);
            context.notifyUpdate(this);
        }
        catch (ExpressionException ex) {
            expressionListener.handleException(this, context, ex);
        }
    }
    
    @Override
    public void removeNotifyListener(final IContext context, final IExpressionListener expressionListener) {
        if (context == null) {
            throw new IllegalArgumentException("Attempt to remove listener from null context: path:" + this.toString());
        }
        if (this.\u00dc != null && this.\u00dc.removeListener(context, expressionListener)) {
            try {
                this.getVariableSource().addScope(context.getScope());
                this.unbind(context);
            }
            finally {
                this.getVariableSource().removeScope(context.getScope());
            }
            this.getVariableSource().removeScope(context.getScope());
            try {
                context.notifyUnbind(this);
                this.B(context, expressionListener);
            }
            catch (ExpressionException ex) {
                expressionListener.handleException(this, context, ex);
            }
        }
    }
    
    @Override
    public ExpressionListenerList getListeners() {
        if (this.\u00dc == null) {
            this.\u00dc = new ExpressionListenerList();
        }
        return this.\u00dc;
    }
    
    @Override
    protected IExpression cloneOne() {
        final P p = new P();
        final IVariableScope scope = this.getVariableSource().getScope("local");
        if (scope != null) {
            p.getVariableSource().addScope(scope.cloneOne());
        }
        final IVariableScope scope2 = this.getVariableSource().getScope("let");
        if (scope2 != null) {
            p.getVariableSource().addScope(scope2.cloneOne());
        }
        return p;
    }
    
    protected void A(final IContext context, final IExpressionListener expressionListener) throws ExpressionException {
        final boolean shouldUpdate = context.shouldUpdate(this);
        try {
            switch (I()[this.getType(context).ordinal()]) {
                case 1: {
                    final List<IModelObject> evaluateNodes = this.evaluateNodes(context);
                    if (evaluateNodes.size() > 0) {
                        expressionListener.notifyAdd(this, context, evaluateNodes);
                        break;
                    }
                    break;
                }
                case 2: {
                    expressionListener.notifyChange(this, context, this.evaluateString(context), "");
                    break;
                }
                case 3: {
                    expressionListener.notifyChange(this, context, this.evaluateNumber(context), 0.0);
                    break;
                }
                case 4: {
                    expressionListener.notifyChange(this, context, this.evaluateBoolean(context));
                    break;
                }
            }
        }
        catch (ExpressionException ex) {
            expressionListener.handleException(this, context, ex);
        }
        if (shouldUpdate) {
            context.markUpdate(this);
        }
    }
    
    protected void B(final IContext context, final IExpressionListener expressionListener) throws ExpressionException {
        final boolean shouldUpdate = context.shouldUpdate(this);
        try {
            switch (I()[this.getType(context).ordinal()]) {
                case 1: {
                    final List<IModelObject> evaluateNodes = this.evaluateNodes(context);
                    if (evaluateNodes.size() > 0) {
                        expressionListener.notifyRemove(this, context, evaluateNodes);
                        break;
                    }
                    break;
                }
                case 2: {
                    expressionListener.notifyChange(this, context, "", this.evaluateString(context));
                    break;
                }
                case 3: {
                    expressionListener.notifyChange(this, context, 0.0, this.evaluateNumber(context));
                    break;
                }
                case 4: {
                    expressionListener.notifyChange(this, context, !this.evaluateBoolean(context));
                    break;
                }
            }
        }
        catch (ExpressionException ex) {
            expressionListener.handleException(this, context, ex);
        }
        if (shouldUpdate) {
            context.markUpdate(this);
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.\u00db = true;
        try {
            if (this.\u00dc != null) {
                this.\u00dc.notifyAdd(this, context, list);
            }
        }
        finally {
            this.\u00db = false;
        }
        this.\u00db = false;
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.\u00db = true;
        try {
            if (this.\u00dc != null) {
                this.\u00dc.notifyRemove(this, context, list);
            }
        }
        finally {
            this.\u00db = false;
        }
        this.\u00db = false;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.\u00db = true;
        try {
            if (this.\u00dc != null) {
                this.\u00dc.notifyChange(this, context, b);
            }
        }
        finally {
            this.\u00db = false;
        }
        this.\u00db = false;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.\u00db = true;
        try {
            if (this.\u00dc != null) {
                this.\u00dc.notifyChange(this, context, n, n2);
            }
        }
        finally {
            this.\u00db = false;
        }
        this.\u00db = false;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.\u00db = true;
        try {
            if (this.\u00dc != null) {
                this.\u00dc.notifyChange(this, context, s, s2);
            }
        }
        finally {
            this.\u00db = false;
        }
        this.\u00db = false;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        this.\u00db = true;
        try {
            if (this.\u00dc != null && context.shouldUpdate(this)) {
                context.notifyUpdate(this);
                this.\u00dc.notifyChange(this, context);
            }
        }
        finally {
            this.\u00db = false;
        }
        this.\u00db = false;
    }
    
    @Override
    public void handleException(final IExpression expression, final IContext context, final Exception ex) {
        if (this.\u00dc != null) {
            this.\u00dc.handleException(this, context, ex);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return this.\u00dc != null && this.\u00dc.requiresValueNotification();
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (this.\u00dc != null) {
            this.\u00dc.notifyValue(this, array, modelObject, o, o2);
        }
    }
    
    @Override
    public String toString() {
        final IExpression argument = this.getArgument(0);
        return (argument != null) ? argument.toString() : "(?)";
    }
    
    static /* synthetic */ int[] I() {
        final int[] \u00fa = P.\u00da;
        if (\u00fa != null) {
            return \u00fa;
        }
        final int[] \u00fa2 = new int[IExpression.ResultType.values().length];
        try {
            \u00fa2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00fa2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00fa2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00fa2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00fa2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return P.\u00da = \u00fa2;
    }
}
