// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.net;

import java.io.IOException;

public interface ProxyAuthenticator
{
    String getProxyUsername(final String p0, final String p1) throws IOException;
    
    String getProxyPassword(final String p0, final String p1) throws IOException;
}
