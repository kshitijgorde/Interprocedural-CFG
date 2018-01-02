// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.xpath.variable.IVariableSource;
import java.util.Iterator;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.ArrayList;
import org.xmodel.xpath.expression.LetExpression;
import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpression;
import java.util.List;
import org.xmodel.xpath.expression.P;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.function.Function;

public class DelegateFunction extends Function
{
    final IExpressionListener u;
    String v;
    String t;
    P s;
    List<IExpression> l;
    boolean r;
    
    public DelegateFunction(final String v, final String t) {
        this.u = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
                DelegateFunction.this.getParent().notifyAdd(DelegateFunction.this, context, list);
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
                DelegateFunction.this.getParent().notifyRemove(DelegateFunction.this, context, list);
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
                DelegateFunction.this.getParent().notifyChange(DelegateFunction.this, context, b);
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
                DelegateFunction.this.getParent().notifyChange(DelegateFunction.this, context, n, n2);
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
                DelegateFunction.this.getParent().notifyChange(DelegateFunction.this, context, s, s2);
            }
            
            @Override
            public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
                DelegateFunction.this.getParent().notifyValue(DelegateFunction.this, array, modelObject, o, o2);
            }
            
            @Override
            public boolean requiresValueNotification() {
                return DelegateFunction.this.requiresValueNotification(DelegateFunction.this);
            }
            
            @Override
            public void handleException(final IExpression expression, final IContext context, final Exception ex) {
                DelegateFunction.this.getParent().handleException(DelegateFunction.this, context, ex);
            }
        };
        this.v = v;
        this.t = t;
        this.s = new P(new LetExpression(XPath.createExpression(t).getArgument(0)));
    }
    
    @Override
    public String getName() {
        return this.v;
    }
    
    @Override
    public void addArgument(final IExpression expression) {
        if (this.l == null) {
            this.l = new ArrayList<IExpression>(1);
        }
        final P p = new P(expression);
        ((LetExpression)this.s.getArgument(0)).A(p, "arg" + this.l.size());
        this.l.add(p);
    }
    
    @Override
    public void removeArgument(final IExpression expression) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IExpression.ResultType getType() {
        this.J();
        return this.s.getType();
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        this.J();
        return this.s.getType(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.J();
        return this.s.evaluateBoolean(context);
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.J();
        return this.s.evaluateNodes(context);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.J();
        return this.s.evaluateNumber(context);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.J();
        return this.s.evaluateString(context);
    }
    
    @Override
    public void bind(final IContext context) {
        this.J();
        this.s.addListener(context, this.u);
    }
    
    @Override
    public void unbind(final IContext context) {
        this.J();
        this.s.removeListener(context, this.u);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyAdd(this, context, list);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyRemove(this, context, list);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.getParent().notifyChange(this, context, b);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.getParent().notifyChange(this, context, n, n2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.getParent().notifyChange(this, context, s, s2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyValue(this, array, modelObject, o, o2);
    }
    
    @Override
    public Object clone() {
        return this.cloneOne();
    }
    
    @Override
    protected IExpression cloneOne() {
        final DelegateFunction delegateFunction = new DelegateFunction(this.v, this.t);
        if (this.l != null) {
            final Iterator<IExpression> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                delegateFunction.addArgument((IExpression)iterator.next().clone());
            }
        }
        return delegateFunction;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append('(');
        if (this.l != null) {
            final boolean b = this.l.size() > 1;
            for (int i = 0; i < this.l.size(); ++i) {
                if (i > 0 && b) {
                    sb.append(", ");
                }
                sb.append(this.l.get(i).toString());
            }
        }
        sb.append(')');
        return sb.toString();
    }
    
    private void J() {
        if (this.r) {
            return;
        }
        this.r = true;
        final IVariableSource variableSource = this.getVariableSource();
        this.s.getVariableSource().setParent(variableSource);
        if (this.l != null) {
            final Iterator<IExpression> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                iterator.next().getVariableSource().setParent(variableSource);
            }
        }
    }
}
