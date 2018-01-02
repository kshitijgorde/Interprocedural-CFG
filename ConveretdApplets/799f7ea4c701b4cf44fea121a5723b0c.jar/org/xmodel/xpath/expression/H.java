// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import java.util.Set;
import org.xmodel.IModelListener;
import org.xmodel.IModelObject;
import java.util.List;
import java.util.HashSet;

public class H extends L
{
    IExpression.ResultType \u00c9;
    Object \u00ca;
    private static /* synthetic */ int[] \u00c8;
    
    public H() {
        this.A("");
    }
    
    public H(final Object o) {
        this.A(o);
    }
    
    @Override
    public String getName() {
        return "literal";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return this.\u00c9;
    }
    
    public void A(Object \u00ea) {
        Set<IContext> set = null;
        if (this.\u00c9 == IExpression.ResultType.NODES && this.k != null && this.k.requiresValueNotification(this)) {
            set = new HashSet<IContext>();
            for (final IModelObject modelObject : (List)this.\u00ca) {
                for (final B b : B.A(modelObject, this)) {
                    modelObject.removeModelListener(b);
                    set.add(b.H());
                }
            }
        }
        if (\u00ea == null) {
            \u00ea = "";
        }
        this.\u00ca = \u00ea;
        if (\u00ea instanceof List) {
            this.\u00c9 = IExpression.ResultType.NODES;
            if (this.k != null && this.k.requiresValueNotification(this) && set != null && set.size() > 0) {
                final Iterator<IContext> iterator3 = set.iterator();
                while (iterator3.hasNext()) {
                    final B b2 = new B(this, iterator3.next());
                    final Iterator<IModelObject> iterator4 = (Iterator<IModelObject>)((List)\u00ea).iterator();
                    while (iterator4.hasNext()) {
                        iterator4.next().addModelListener(b2);
                    }
                }
            }
        }
        else if (\u00ea instanceof Number) {
            this.\u00c9 = IExpression.ResultType.NUMBER;
        }
        else if (\u00ea instanceof Boolean) {
            this.\u00c9 = IExpression.ResultType.BOOLEAN;
        }
        else {
            this.\u00c9 = IExpression.ResultType.STRING;
        }
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        if (this.\u00c9 == IExpression.ResultType.NODES) {
            return (List<IModelObject>)this.\u00ca;
        }
        return super.evaluateNodes(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        if (this.\u00c9 == IExpression.ResultType.BOOLEAN) {
            return (boolean)this.\u00ca;
        }
        return super.evaluateBoolean(context);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        if (this.\u00c9 == IExpression.ResultType.NUMBER) {
            return ((Number)this.\u00ca).doubleValue();
        }
        return super.evaluateNumber(context);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        if (this.\u00c9 == IExpression.ResultType.STRING) {
            return this.\u00ca.toString();
        }
        return super.evaluateString(context);
    }
    
    @Override
    public void bind(final IContext context) {
        if (this.getType(context) == IExpression.ResultType.NODES && this.k != null && this.k.requiresValueNotification(this)) {
            final B b = new B(this, context);
            final Iterator<IModelObject> iterator = (Iterator<IModelObject>)((List)this.\u00ca).iterator();
            while (iterator.hasNext()) {
                iterator.next().addModelListener(b);
            }
        }
    }
    
    @Override
    public void unbind(final IContext context) {
        if (this.getType(context) == IExpression.ResultType.NODES && this.k != null && this.k.requiresValueNotification(this)) {
            IModelListener a = null;
            for (final IModelObject modelObject : (List)this.\u00ca) {
                if (a == null) {
                    a = B.A(modelObject, this, context);
                }
                if (a != null) {
                    modelObject.removeModelListener(a);
                }
            }
        }
    }
    
    @Override
    protected IExpression cloneOne() {
        final H h = new H();
        h.A(this.\u00ca);
        return h;
    }
    
    @Override
    public String toString() {
        if (this.\u00c9 == null) {
            return "";
        }
        switch (I()[this.\u00c9.ordinal()]) {
            case 1: {
                final List list = (List)this.\u00ca;
                final StringBuilder sb = new StringBuilder();
                sb.append("{ ");
                for (int i = 0; i < list.size(); ++i) {
                    sb.append(list.get(i).toString());
                    if (i < list.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append('}');
                return sb.toString();
            }
            case 3: {
                return this.\u00ca.toString();
            }
            default: {
                return (this.\u00ca != null) ? ("'" + this.\u00ca.toString() + "'") : "''";
            }
        }
    }
    
    static /* synthetic */ int[] I() {
        final int[] \u00e8 = H.\u00c8;
        if (\u00e8 != null) {
            return \u00e8;
        }
        final int[] \u00e82 = new int[IExpression.ResultType.values().length];
        try {
            \u00e82[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00e82[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00e82[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00e82[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00e82[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return H.\u00c8 = \u00e82;
    }
}
