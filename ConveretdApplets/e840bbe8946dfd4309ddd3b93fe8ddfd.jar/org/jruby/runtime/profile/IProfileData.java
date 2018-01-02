// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

public interface IProfileData
{
    int profileEnter(final int p0);
    
    int profileExit(final int p0, final long p1);
    
    void clear();
    
    Invocation getResults();
}
