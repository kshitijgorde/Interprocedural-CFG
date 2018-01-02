// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.IExpression;

public interface IFunctionFactory
{
    void register(final String p0, final Class<? extends Function> p1);
    
    void register(final String p0, final Function p1);
    
    IExpression createFunction(final String p0);
}
