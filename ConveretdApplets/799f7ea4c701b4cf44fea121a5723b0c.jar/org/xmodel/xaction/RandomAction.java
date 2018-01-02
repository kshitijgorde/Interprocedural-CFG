// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import java.util.Random;

public class RandomAction extends GuardedAction
{
    private Random \u00ce;
    private boolean \u00ca;
    private int \u00cd;
    private String \u00cc;
    private IExpression \u00cf;
    private IExpression \u00c9;
    private IExpression \u00cb;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u00c9 = xActionDocument.getExpression("min", true);
        this.\u00cb = xActionDocument.getExpression("max", true);
        this.\u00ca = Xlate.get(xActionDocument.getRoot(), "decimal", false);
        this.\u00cd = Xlate.get(root, "radix", -1);
        this.\u00cc = Conventions.getVarName(root, false, "assign");
        this.\u00cf = xActionDocument.getExpression();
        this.\u00ce = new Random();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        double evaluateNumber = 0.0;
        double evaluateNumber2 = 0.0;
        if (this.\u00c9 != null) {
            evaluateNumber = this.\u00c9.evaluateNumber(context);
        }
        if (this.\u00cb != null) {
            evaluateNumber2 = this.\u00cb.evaluateNumber(context);
        }
        final Number a = this.A(evaluateNumber, evaluateNumber2, this.\u00ca);
        if (this.\u00cd != -1) {
            final String a2 = this.A(a, this.\u00cd);
            if (this.\u00cc != null) {
                context.getScope().set(this.\u00cc, a2);
            }
            if (this.\u00cf != null) {
                final Iterator<IModelObject> iterator = this.\u00cf.query(context, null).iterator();
                while (iterator.hasNext()) {
                    iterator.next().setValue(a2);
                }
            }
        }
        else {
            if (this.\u00cc != null) {
                context.getScope().set(this.\u00cc, a);
            }
            if (this.\u00cf != null) {
                final Iterator<IModelObject> iterator2 = this.\u00cf.query(context, null).iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().setValue(a);
                }
            }
        }
        return null;
    }
    
    private Number A(final double n, final double n2, final boolean b) {
        final double n3 = n2 - n;
        if (b) {
            final double nextDouble = this.\u00ce.nextDouble();
            if (n3 == 0.0) {
                return nextDouble;
            }
            return nextDouble * n3 + n;
        }
        else {
            if (n3 == 0.0) {
                return this.\u00ce.nextLong();
            }
            return (long)(this.\u00ce.nextDouble() * n3 + n);
        }
    }
    
    private String A(final Number n, final int n2) {
        if (n2 == -1) {
            return null;
        }
        if (n instanceof Long) {
            return Long.toString((long)n, n2);
        }
        if (n2 == 16) {
            return Double.toHexString((double)n);
        }
        return Double.toString((double)n);
    }
}
