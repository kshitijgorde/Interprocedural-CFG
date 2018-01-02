// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import java.util.Iterator;
import org.apache.activemq.jaas.GroupPrincipal;
import java.util.HashSet;
import java.util.Set;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Map;
import org.apache.activemq.broker.BrokerFilter;

public class SimpleAuthenticationBroker extends BrokerFilter
{
    private boolean anonymousAccessAllowed;
    private String anonymousUser;
    private String anonymousGroup;
    private final Map userPasswords;
    private final Map userGroups;
    private final CopyOnWriteArrayList<SecurityContext> securityContexts;
    
    public SimpleAuthenticationBroker(final Broker next, final Map userPasswords, final Map userGroups) {
        super(next);
        this.anonymousAccessAllowed = false;
        this.securityContexts = new CopyOnWriteArrayList<SecurityContext>();
        this.userPasswords = userPasswords;
        this.userGroups = userGroups;
    }
    
    public void setAnonymousAccessAllowed(final boolean anonymousAccessAllowed) {
        this.anonymousAccessAllowed = anonymousAccessAllowed;
    }
    
    public void setAnonymousUser(final String anonymousUser) {
        this.anonymousUser = anonymousUser;
    }
    
    public void setAnonymousGroup(final String anonymousGroup) {
        this.anonymousGroup = anonymousGroup;
    }
    
    @Override
    public void addConnection(final ConnectionContext context, final ConnectionInfo info) throws Exception {
        SecurityContext s = context.getSecurityContext();
        if (s == null) {
            if (this.anonymousAccessAllowed && info.getUserName() == null && info.getPassword() == null) {
                info.setUserName(this.anonymousUser);
                s = new SecurityContext(info.getUserName()) {
                    @Override
                    public Set getPrincipals() {
                        final Set groups = new HashSet();
                        groups.add(new GroupPrincipal(SimpleAuthenticationBroker.this.anonymousGroup));
                        return groups;
                    }
                };
            }
            else {
                final String pw = this.userPasswords.get(info.getUserName());
                if (pw == null || !pw.equals(info.getPassword())) {
                    throw new SecurityException("User name or password is invalid.");
                }
                final Set groups = this.userGroups.get(info.getUserName());
                s = new SecurityContext(info.getUserName()) {
                    @Override
                    public Set<?> getPrincipals() {
                        return (Set<?>)groups;
                    }
                };
            }
            context.setSecurityContext(s);
            this.securityContexts.add(s);
        }
        try {
            super.addConnection(context, info);
        }
        catch (Exception e) {
            this.securityContexts.remove(s);
            context.setSecurityContext(null);
            throw e;
        }
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
}
