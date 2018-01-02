// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

public interface Closure
{
    boolean inInnerClass();
    
    Closure getParentClosure();
    
    String getInnerClassName();
    
    void addVariable(final VariableRefBase p0);
}
