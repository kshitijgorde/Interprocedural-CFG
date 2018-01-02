// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public interface ErrorReporter
{
    void reportWarning(final String p0, final int p1, final Object[] p2);
    
    void reportRecoverableError(final String p0, final int p1, final Object[] p2);
    
    void reportFatalError(final String p0, final int p1, final Object[] p2);
}
