// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.expression.IContext;

public interface IXAction
{
    void setDocument(final XActionDocument p0);
    
    XActionDocument getDocument();
    
    void configure(final XActionDocument p0);
    
    Object[] run(final IContext p0);
    
    IVariableScope run();
}
