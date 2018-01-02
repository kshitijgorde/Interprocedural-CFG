// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.util.Base64;
import org.apache.commons.httpclient.HttpConstants;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

public class BasicScheme extends RFC2617Scheme
{
    public BasicScheme(final String challenge) throws MalformedChallengeException {
        super(challenge);
    }
    
    public String getSchemeName() {
        return "basic";
    }
    
    public static String authenticate(final UsernamePasswordCredentials credentials) {
        LOG.trace("enter BasicScheme.authenticate(UsernamePasswordCredentials)");
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append(credentials.getUserName());
        buffer.append(":");
        buffer.append(credentials.getPassword());
        return "Basic " + HttpConstants.getAsciiString(Base64.encode(HttpConstants.getBytes(buffer.toString())));
    }
    
    public String authenticate(final Credentials credentials, final String method, final String uri) throws AuthenticationException {
        LOG.trace("enter BasicScheme.authenticate(Credentials, String, String)");
        UsernamePasswordCredentials usernamepassword = null;
        try {
            usernamepassword = (UsernamePasswordCredentials)credentials;
        }
        catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for basic authentication: " + credentials.getClass().getName());
        }
        return authenticate(usernamepassword);
    }
}
