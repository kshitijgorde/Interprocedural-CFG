// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class BoundFunction extends Function
{
    public static final String name = "dsp:bound";
    
    @Override
    public String getName() {
        return "dsp:bound";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(3, 4);
        this.assertType(context, 0, IExpression.ResultType.NUMBER);
        this.assertType(context, 1, IExpression.ResultType.NUMBER);
        this.assertType(context, 2, IExpression.ResultType.NUMBER);
        boolean allow0 = false;
        final int arguments = this.getArguments().size();
        if (arguments > 3) {
            allow0 = this.getArguments().get(3).evaluateBoolean();
        }
        int value = (int)this.getArguments().get(0).evaluateNumber(context);
        if (allow0 && value == 0) {
            return String.valueOf(value);
        }
        final int min = (int)this.getArguments().get(1).evaluateNumber();
        final int max = (int)this.getArguments().get(2).evaluateNumber();
        if (value < min) {
            value = min;
        }
        if (value > max) {
            value = max;
        }
        return String.valueOf(value);
    }
    
    @Override
    public void bind(final IContext context) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void unbind(final IContext context) {
        throw new UnsupportedOperationException();
    }
}
