// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class ReturnAction extends GuardedAction
{
    private IExpression \u011b;
    private static /* synthetic */ int[] \u011a;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u011b = xActionDocument.getExpression();
        if (this.\u011b == null) {
            this.\u011b = xActionDocument.getExpression("result", true);
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.\u011b != null) {
            switch (H()[this.\u011b.getType(context).ordinal()]) {
                case 1: {
                    return new Object[] { this.\u011b.evaluateNodes(context) };
                }
                case 3: {
                    return new Object[] { this.\u011b.evaluateNumber(context) };
                }
                case 2: {
                    return new Object[] { this.\u011b.evaluateString(context) };
                }
                case 4: {
                    return new Object[] { this.\u011b.evaluateBoolean(context) };
                }
            }
        }
        return new Object[0];
    }
    
    static /* synthetic */ int[] H() {
        final int[] \u011b = ReturnAction.\u011a;
        if (\u011b != null) {
            return \u011b;
        }
        final int[] \u011b2 = new int[IExpression.ResultType.values().length];
        try {
            \u011b2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u011b2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u011b2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u011b2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u011b2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return ReturnAction.\u011a = \u011b2;
    }
}
