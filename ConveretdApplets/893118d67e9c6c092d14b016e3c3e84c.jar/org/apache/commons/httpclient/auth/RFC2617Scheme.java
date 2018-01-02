// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import java.util.Map;

public abstract class RFC2617Scheme extends AuthSchemeBase
{
    private Map params;
    
    public RFC2617Scheme(final String challenge) throws MalformedChallengeException {
        super(challenge);
        this.params = null;
        final String s = AuthChallengeParser.extractScheme(challenge);
        if (!s.equalsIgnoreCase(this.getSchemeName())) {
            throw new MalformedChallengeException("Invalid " + this.getSchemeName() + " challenge: " + challenge);
        }
        this.params = AuthChallengeParser.extractParams(challenge);
    }
    
    public String getID() {
        return this.getRealm();
    }
    
    public String getParameter(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Parameter name may not be null");
        }
        return this.params.get(name.toLowerCase());
    }
    
    public String getRealm() {
        return this.getParameter("realm");
    }
    
    protected Map getParameters() {
        return this.params;
    }
}
