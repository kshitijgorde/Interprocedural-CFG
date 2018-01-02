// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.builtin;

public interface InternalVariables
{
    boolean hasInternalVariable(final String p0);
    
    boolean fastHasInternalVariable(final String p0);
    
    Object getInternalVariable(final String p0);
    
    Object fastGetInternalVariable(final String p0);
    
    void setInternalVariable(final String p0, final Object p1);
    
    void fastSetInternalVariable(final String p0, final Object p1);
    
    Object removeInternalVariable(final String p0);
}
