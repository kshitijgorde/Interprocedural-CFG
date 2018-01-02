// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.Collection;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import javax.security.auth.login.LoginException;
import javax.security.auth.callback.Callback;
import java.util.Map;
import java.util.HashSet;
import java.security.Principal;
import java.util.Set;
import java.security.cert.X509Certificate;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import org.slf4j.Logger;
import javax.security.auth.spi.LoginModule;

public abstract class CertificateLoginModule implements LoginModule
{
    private static final Logger LOG;
    private CallbackHandler callbackHandler;
    private Subject subject;
    private X509Certificate[] certificates;
    private String username;
    private Set groups;
    private Set<Principal> principals;
    private boolean debug;
    
    public CertificateLoginModule() {
        this.principals = new HashSet<Principal>();
    }
    
    @Override
    public void initialize(final Subject subject, final CallbackHandler callbackHandler, final Map sharedState, final Map options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.debug = "true".equalsIgnoreCase(options.get("debug"));
        if (this.debug) {
            CertificateLoginModule.LOG.debug("Initialized debug");
        }
    }
    
    @Override
    public boolean login() throws LoginException {
        final Callback[] callbacks = { new CertificateCallback() };
        try {
            this.callbackHandler.handle(callbacks);
        }
        catch (IOException ioe) {
            throw new LoginException(ioe.getMessage());
        }
        catch (UnsupportedCallbackException uce) {
            throw new LoginException(uce.getMessage() + " Unable to obtain client certificates.");
        }
        this.certificates = ((CertificateCallback)callbacks[0]).getCertificates();
        this.username = this.getUserNameForCertificates(this.certificates);
        if (this.username == null) {
            throw new FailedLoginException("No user for client certificate: " + this.getDistinguishedName(this.certificates));
        }
        this.groups = this.getUserGroups(this.username);
        if (this.debug) {
            CertificateLoginModule.LOG.debug("Certificate for user: " + this.username);
        }
        return true;
    }
    
    @Override
    public boolean commit() throws LoginException {
        this.principals.add(new UserPrincipal(this.username));
        String currentGroup = null;
        final Iterator iter = this.groups.iterator();
        while (iter.hasNext()) {
            currentGroup = iter.next();
            this.principals.add(new GroupPrincipal(currentGroup));
        }
        this.subject.getPrincipals().addAll(this.principals);
        this.clear();
        if (this.debug) {
            CertificateLoginModule.LOG.debug("commit");
        }
        return true;
    }
    
    @Override
    public boolean abort() throws LoginException {
        this.clear();
        if (this.debug) {
            CertificateLoginModule.LOG.debug("abort");
        }
        return true;
    }
    
    @Override
    public boolean logout() {
        this.subject.getPrincipals().removeAll(this.principals);
        this.principals.clear();
        if (this.debug) {
            CertificateLoginModule.LOG.debug("logout");
        }
        return true;
    }
    
    private void clear() {
        this.groups.clear();
        this.certificates = null;
    }
    
    protected abstract String getUserNameForCertificates(final X509Certificate[] p0) throws LoginException;
    
    protected abstract Set getUserGroups(final String p0) throws LoginException;
    
    protected String getDistinguishedName(final X509Certificate[] certs) {
        if (certs != null && certs.length > 0 && certs[0] != null) {
            return certs[0].getSubjectDN().getName();
        }
        return null;
    }
    
    static {
        LOG = LoggerFactory.getLogger(CertificateLoginModule.class);
    }
}
