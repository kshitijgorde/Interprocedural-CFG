// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.xml.sax.Locator;

public interface XMLErrorReporter
{
    public static final int ERRORTYPE_WARNING = 0;
    public static final int ERRORTYPE_RECOVERABLE_ERROR = 1;
    public static final int ERRORTYPE_FATAL_ERROR = 2;
    
    Locator getLocator();
    
    void reportError(final Locator p0, final String p1, final int p2, final int p3, final Object[] p4, final int p5) throws Exception;
}
