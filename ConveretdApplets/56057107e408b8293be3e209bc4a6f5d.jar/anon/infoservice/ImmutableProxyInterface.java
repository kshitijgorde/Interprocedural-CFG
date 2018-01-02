// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import HTTPClient.NVPair;
import anon.util.IXMLEncodable;

public interface ImmutableProxyInterface extends ImmutableListenerInterface, IXMLEncodable
{
    boolean isAuthenticationUsed();
    
    String getAuthenticationPassword();
    
    String getAuthenticationUserID();
    
    String getProxyAuthorizationHeaderAsString();
    
    NVPair getProxyAuthorizationHeader();
}
