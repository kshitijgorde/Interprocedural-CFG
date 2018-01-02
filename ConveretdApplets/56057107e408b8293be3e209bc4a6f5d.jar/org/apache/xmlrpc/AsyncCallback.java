// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.net.URL;

public interface AsyncCallback
{
    void handleResult(final Object p0, final URL p1, final String p2);
    
    void handleError(final Exception p0, final URL p1, final String p2);
}
