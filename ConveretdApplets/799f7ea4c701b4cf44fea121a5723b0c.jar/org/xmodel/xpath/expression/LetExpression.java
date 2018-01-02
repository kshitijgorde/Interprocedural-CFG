// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;

public class LetExpression extends L
{
    private IExpressionListener º;
    private List<Clause> \u00c0;
    private static /* synthetic */ int[] µ;
    
    public LetExpression() {
        this.º = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
                LetExpression.this.rebind(context.getParent());
                LetExpression.this.A((LocalContext)context, 0);
                LetExpression.this.k.notifyChange(LetExpression.this, context.getParent());
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
                LetExpression.this.rebind(context.getParent());
                LetExpression.this.A((LocalContext)context, 0);
                LetExpression.this.k.notifyChange(LetExpression.this, context.getParent());
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
                LetExpression.this.rebind(context.getParent());
                LetExpression.this.A((LocalContext)context, 0);
                LetExpression.this.k.notifyChange(LetExpression.this, context.getParent());
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
                LetExpression.this.rebind(context.getParent());
                LetExpression.this.A((LocalContext)context, 0);
                LetExpression.this.k.notifyChange(LetExpression.this, context.getParent());
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
                LetExpression.this.rebind(context.getParent());
                LetExpression.this.A((LocalContext)context, 0);
                LetExpression.this.k.notifyChange(LetExpression.this, context.getParent());
            }
            
            @Override
            public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
                for (final IContext context : array) {
                    LetExpression.this.rebind(context.getParent());
                    LetExpression.this.A((LocalContext)context, 0);
                    LetExpression.this.k.notifyChange(LetExpression.this, context.getParent());
                }
            }
            
            @Override
            public boolean requiresValueNotification() {
                return true;
            }
        };
        this.\u00c0 = new ArrayList<Clause>();
    }
    
    public LetExpression(final IExpression expression) {
        this();
        this.addArgument(expression);
    }
    
    public void A(final P p2, final String s) {
        this.\u00c0.add(new Clause(s, p2));
    }
    
    @Override
    public String getName() {
        return "let";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return this.getArgument(0).getType();
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        return this.getArgument(0).getType(localContext);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        return this.getArgument(0).evaluateBoolean(localContext);
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        return this.getArgument(0).evaluateNodes(localContext);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        return this.getArgument(0).evaluateNumber(localContext);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        return this.getArgument(0).evaluateString(localContext);
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        this.getArgument(0).createSubtree(localContext, modelObjectFactory, set);
    }
    
    @Override
    public void bind(final IContext context) {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        final Iterator<Clause> iterator = this.\u00c0.iterator();
        while (iterator.hasNext()) {
            iterator.next().C.addListener(localContext, this.º);
        }
        this.getArgument(0).bind(localContext);
    }
    
    @Override
    public void unbind(final IContext context) {
        final LocalContext localContext = new LocalContext(context);
        this.A(localContext, 0);
        this.getArgument(0).unbind(localContext);
        final Iterator<Clause> iterator = this.\u00c0.iterator();
        while (iterator.hasNext()) {
            iterator.next().C.removeListener(localContext, this.º);
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.k.notifyAdd(this, context.getParent(), list);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.k.notifyRemove(this, context.getParent(), list);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.k.notifyChange(this, context.getParent(), b);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.k.notifyChange(this, context.getParent(), n, n2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.k.notifyChange(this, context.getParent(), s, s2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        this.k.notifyChange(this, context.getParent());
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = array[i].getParent();
        }
        this.k.notifyValue(this, array, modelObject, o, o2);
    }
    
    private void A(final LocalContext localContext, final int n) {
        for (int i = n; i < this.\u00c0.size(); ++i) {
            final Clause clause = this.\u00c0.get(i);
            switch (I()[clause.C.getType(localContext).ordinal()]) {
                case 1: {
                    localContext.set(clause.B, clause.C.evaluateNodes(localContext));
                    break;
                }
                case 3: {
                    localContext.set(clause.B, clause.C.evaluateNumber(localContext));
                    break;
                }
                case 2: {
                    localContext.set(clause.B, clause.C.evaluateString(localContext));
                    break;
                }
                case 4: {
                    localContext.set(clause.B, Boolean.valueOf(clause.C.evaluateBoolean(localContext)));
                    break;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Clause clause : this.\u00c0) {
            sb.append("let $");
            sb.append(clause.B);
            sb.append(" := ");
            sb.append(clause.C);
            sb.append(";\n");
        }
        sb.append(this.getArgument(0));
        return sb.toString();
    }
    
    static /* synthetic */ int[] I() {
        final int[] µ = LetExpression.µ;
        if (µ != null) {
            return µ;
        }
        final int[] µ2 = new int[IExpression.ResultType.values().length];
        try {
            µ2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            µ2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            µ2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            µ2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            µ2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return LetExpression.µ = µ2;
    }
    
    public class Clause
    {
        String B;
        IExpression C;
        
        public Clause(final String b, final IExpression c) {
            this.B = b;
            this.C = c;
        }
    }
}
