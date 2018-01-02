// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public interface NFDebugObserver
{
    void debugMask(final long p0);
    
    void debugMessage(final String p0);
    
    void debugException(final String p0, final Exception p1);
    
    void debugWarning(final Object p0);
    
    void debugInfo(final Object p0);
}
