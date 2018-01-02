// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import java.security.Principal;
import java.util.Set;
import java.util.Iterator;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import org.apache.activemq.jaas.JassCredentialCallbackHandler;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.broker.BrokerFilter;

public class JaasAuthenticationBroker extends BrokerFilter
{
    private final String jassConfiguration;
    private final CopyOnWriteArrayList<SecurityContext> securityContexts;
    
    public JaasAuthenticationBroker(final Broker next, final String jassConfiguration) {
        super(next);
        this.securityContexts = new CopyOnWriteArrayList<SecurityContext>();
        this.jassConfiguration = jassConfiguration;
    }
    
    @Override
    public void addConnection(final ConnectionContext context, final ConnectionInfo info) throws Exception {
        if (context.getSecurityContext() == null) {
            final ClassLoader original = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(JaasAuthenticationBroker.class.getClassLoader());
            try {
                final JassCredentialCallbackHandler callback = new JassCredentialCallbackHandler(info.getUserName(), info.getPassword());
                final LoginContext lc = new LoginContext(this.jassConfiguration, callback);
                lc.login();
                final Subject subject = lc.getSubject();
                final SecurityContext s = new JaasSecurityContext(info.getUserName(), subject);
                context.setSecurityContext(s);
                this.securityContexts.add(s);
            }
            catch (Exception e) {
                throw (SecurityException)new SecurityException("User name or password is invalid.").initCause(e);
            }
            finally {
                Thread.currentThread().setContextClassLoader(original);
            }
        }
        super.addConnection(context, info);
    }
    
    @Override
    public void removeConnection(final ConnectionContext context, final ConnectionInfo info, final Throwable error) throws Exception {
        super.removeConnection(context, info, error);
        if (this.securityContexts.remove(context.getSecurityContext())) {
            context.setSecurityContext(null);
        }
    }
    
    public void refresh() {
        for (final SecurityContext sc : this.securityContexts) {
            sc.getAuthorizedReadDests().clear();
            sc.getAuthorizedWriteDests().clear();
        }
    }
    
    static class JaasSecurityContext extends SecurityContext
    {
        private final Subject subject;
        
        public JaasSecurityContext(final String userName, final Subject subject) {
            super(userName);
            this.subject = subject;
        }
        
        @Override
        public Set<Principal> getPrincipals() {
            return this.subject.getPrincipals();
        }
    }
}
