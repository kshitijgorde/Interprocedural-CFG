// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import com.stonewall.cornerstone.utility.Encrypted;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class EncryptFunction extends Function
{
    public static final String name = "dsp:encrypt";
    
    @Override
    public String getName() {
        return "dsp:encrypt";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, 0, IExpression.ResultType.STRING);
        final String s = this.getArguments().get(0).evaluateString(context);
        return Encrypted.encrypt(s);
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
