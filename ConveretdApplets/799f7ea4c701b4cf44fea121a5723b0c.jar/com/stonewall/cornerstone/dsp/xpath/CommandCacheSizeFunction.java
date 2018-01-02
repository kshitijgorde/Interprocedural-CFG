// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import com.stonewall.cornerstone.dsp.command.CommandCache;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class CommandCacheSizeFunction extends Function
{
    public static final String name = "dsp:command-size";
    
    @Override
    public String getName() {
        return "dsp:command-size";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NUMBER;
    }
    
    @Override
    public double evaluateNumber(final IContext context, final double defaultResult) {
        return this.evaluateNumber();
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        return this.evaluateNumber();
    }
    
    @Override
    public double evaluateNumber() throws ExpressionException {
        final CommandCache cache = CommandCache.getCurrent();
        return cache.getCommands().size();
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
