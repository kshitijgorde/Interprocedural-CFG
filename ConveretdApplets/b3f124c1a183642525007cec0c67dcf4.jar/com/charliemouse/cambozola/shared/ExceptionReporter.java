// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.shared;

public interface ExceptionReporter
{
    void reportError(final Throwable p0);
    
    void reportFailure(final String p0);
    
    void reportNote(final String p0);
}
