// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import org.slf4j.LoggerFactory;
import java.util.Collection;
import javax.security.auth.login.LoginException;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.PasswordCallback;
import java.util.Map;
import java.util.HashSet;
import javax.security.auth.callback.CallbackHandler;
import java.security.Principal;
import java.util.Set;
import javax.security.auth.Subject;
import org.slf4j.Logger;
import javax.security.auth.spi.LoginModule;

public class GuestLoginModule implements LoginModule
{
    private static final String GUEST_USER = "org.apache.activemq.jaas.guest.user";
    private static final String GUEST_GROUP = "org.apache.activemq.jaas.guest.group";
    private static final Logger LOG;
    private String userName;
    private String groupName;
    private Subject subject;
    private boolean debug;
    private boolean credentialsInvalidate;
    private Set<Principal> principals;
    private CallbackHandler callbackHandler;
    private boolean loginSucceeded;
    
    public GuestLoginModule() {
        this.userName = "guest";
        this.groupName = "guests";
        this.principals = new HashSet<Principal>();
    }
    
    @Override
    public void initialize(final Subject subject, final CallbackHandler callbackHandler, final Map sharedState, final Map options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.debug = "true".equalsIgnoreCase(options.get("debug"));
        this.credentialsInvalidate = "true".equalsIgnoreCase(options.get("credentialsInvalidate"));
        if (options.get("org.apache.activemq.jaas.guest.user") != null) {
            this.userName = options.get("org.apache.activemq.jaas.guest.user");
        }
        if (options.get("org.apache.activemq.jaas.guest.group") != null) {
            this.groupName = options.get("org.apache.activemq.jaas.guest.group");
        }
        this.principals.add(new UserPrincipal(this.userName));
        this.principals.add(new GroupPrincipal(this.groupName));
        if (this.debug) {
            GuestLoginModule.LOG.debug("Initialized debug=" + this.debug + " guestUser=" + this.userName + " guestGroup=" + this.groupName);
        }
    }
    
    @Override
    public boolean login() throws LoginException {
        this.loginSucceeded = true;
        if (this.credentialsInvalidate) {
            final PasswordCallback passwordCallback = new PasswordCallback("Password: ", false);
            try {
                this.callbackHandler.handle(new Callback[] { passwordCallback });
                if (passwordCallback.getPassword() != null) {
                    if (this.debug) {
                        GuestLoginModule.LOG.debug("Guest login failing (credentialsInvalidate=true) on presence of a password");
                    }
                    this.loginSucceeded = false;
                    passwordCallback.clearPassword();
                }
            }
            catch (IOException ioe) {}
            catch (UnsupportedCallbackException ex) {}
        }
        if (this.debug) {
            GuestLoginModule.LOG.debug("Guest login " + this.loginSucceeded);
        }
        return this.loginSucceeded;
    }
    
    @Override
    public boolean commit() throws LoginException {
        if (this.loginSucceeded) {
            this.subject.getPrincipals().addAll(this.principals);
        }
        if (this.debug) {
            GuestLoginModule.LOG.debug("commit");
        }
        return this.loginSucceeded;
    }
    
    @Override
    public boolean abort() throws LoginException {
        if (this.debug) {
            GuestLoginModule.LOG.debug("abort");
        }
        return true;
    }
    
    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().removeAll(this.principals);
        if (this.debug) {
            GuestLoginModule.LOG.debug("logout");
        }
        return true;
    }
    
    static {
        LOG = LoggerFactory.getLogger(GuestLoginModule.class);
    }
}
