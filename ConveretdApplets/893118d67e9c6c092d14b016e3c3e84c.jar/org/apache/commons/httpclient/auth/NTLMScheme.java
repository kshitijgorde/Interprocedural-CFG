// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.NTCredentials;

public class NTLMScheme extends AuthSchemeBase
{
    private String ntlmchallenge;
    
    public NTLMScheme(final String challenge) throws MalformedChallengeException {
        super(challenge);
        this.ntlmchallenge = null;
        String s = AuthChallengeParser.extractScheme(challenge);
        if (!s.equalsIgnoreCase(this.getSchemeName())) {
            throw new MalformedChallengeException("Invalid NTLM challenge: " + challenge);
        }
        final int i = challenge.indexOf(32);
        if (i != -1) {
            s = challenge.substring(i, challenge.length());
            this.ntlmchallenge = s.trim();
        }
        else {
            this.ntlmchallenge = "";
        }
    }
    
    public String getID() {
        return this.ntlmchallenge;
    }
    
    public String getParameter(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Parameter name may not be null");
        }
        return null;
    }
    
    public String getRealm() {
        return null;
    }
    
    public String getSchemeName() {
        return "ntlm";
    }
    
    public static String authenticate(final NTCredentials credentials, final String challenge) throws AuthenticationException {
        LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        final NTLM ntlm = new NTLM();
        final String s = ntlm.getResponseFor(challenge, credentials.getUserName(), credentials.getPassword(), credentials.getHost(), credentials.getDomain());
        return "NTLM " + s;
    }
    
    public String authenticate(final Credentials credentials, final String method, final String uri) throws AuthenticationException {
        LOG.trace("enter NTLMScheme.authenticate(Credentials, String, String)");
        NTCredentials ntcredentials = null;
        try {
            ntcredentials = (NTCredentials)credentials;
        }
        catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
        return authenticate(ntcredentials, this.ntlmchallenge);
    }
}
