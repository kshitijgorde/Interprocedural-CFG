// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

public interface AuthorizationHandler
{
    AuthorizationInfo[] orderChallenges(final AuthorizationInfo[] p0, final RoRequest p1, final RoResponse p2, final boolean p3);
    
    AuthorizationInfo getAuthorization(final AuthorizationInfo p0, final RoRequest p1, final RoResponse p2, final boolean p3) throws AuthSchemeNotImplException;
    
    AuthorizationInfo fixupAuthInfo(final AuthorizationInfo p0, final Request p1, final AuthorizationInfo p2, final RoResponse p3, final boolean p4) throws AuthSchemeNotImplException;
    
    void handleAuthHeaders(final Response p0, final RoRequest p1, final AuthorizationInfo p2, final AuthorizationInfo p3) throws IOException;
    
    void handleAuthTrailers(final Response p0, final RoRequest p1, final AuthorizationInfo p2, final AuthorizationInfo p3) throws IOException;
    
    void addAuthorizationInfo(final String p0, final String p1, final int p2, final String p3, final Object p4, final Object p5, final Object p6) throws AuthSchemeNotImplException;
}
