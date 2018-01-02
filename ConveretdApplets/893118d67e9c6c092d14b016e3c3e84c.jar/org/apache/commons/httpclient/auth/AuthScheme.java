// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;

public interface AuthScheme
{
    String getID();
    
    String getParameter(final String p0);
    
    String getRealm();
    
    String getSchemeName();
    
    String authenticate(final Credentials p0, final String p1, final String p2) throws AuthenticationException;
}
