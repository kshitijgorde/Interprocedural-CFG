// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.debug;

import org.xmodel.xaction.IXAction;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.expression.IContext;

public interface IDebugger
{
    void stepOver();
    
    void stepIn();
    
    void stepOut();
    
    void push(final IContext p0, final ScriptAction p1);
    
    Object[] run(final IContext p0, final IXAction p1);
    
    void pop();
}
