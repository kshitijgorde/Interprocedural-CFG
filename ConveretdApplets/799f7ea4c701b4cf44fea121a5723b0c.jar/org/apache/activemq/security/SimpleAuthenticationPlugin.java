// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.HashMap;
import org.apache.activemq.broker.Broker;
import java.util.List;
import org.apache.activemq.jaas.GroupPrincipal;
import java.util.Set;
import java.util.Map;
import org.apache.activemq.broker.BrokerPlugin;

public class SimpleAuthenticationPlugin implements BrokerPlugin
{
    private Map<String, String> userPasswords;
    private Map<String, Set<GroupPrincipal>> userGroups;
    private static final String DEFAULT_ANONYMOUS_USER = "anonymous";
    private static final String DEFAULT_ANONYMOUS_GROUP = "anonymous";
    private String anonymousUser;
    private String anonymousGroup;
    private boolean anonymousAccessAllowed;
    
    public SimpleAuthenticationPlugin() {
        this.anonymousUser = "anonymous";
        this.anonymousGroup = "anonymous";
        this.anonymousAccessAllowed = false;
    }
    
    public SimpleAuthenticationPlugin(final List users) {
        this.anonymousUser = "anonymous";
        this.anonymousGroup = "anonymous";
        this.anonymousAccessAllowed = false;
        this.setUsers(users);
    }
    
    @Override
    public Broker installPlugin(final Broker parent) {
        final SimpleAuthenticationBroker broker = new SimpleAuthenticationBroker(parent, this.userPasswords, this.userGroups);
        broker.setAnonymousAccessAllowed(this.anonymousAccessAllowed);
        broker.setAnonymousUser(this.anonymousUser);
        broker.setAnonymousGroup(this.anonymousGroup);
        return broker;
    }
    
    public Map<String, Set<GroupPrincipal>> getUserGroups() {
        return this.userGroups;
    }
    
    public void setUsers(final List users) {
        this.userPasswords = new HashMap<String, String>();
        this.userGroups = new HashMap<String, Set<GroupPrincipal>>();
        for (final AuthenticationUser user : users) {
            this.userPasswords.put(user.getUsername(), user.getPassword());
            final Set<GroupPrincipal> groups = new HashSet<GroupPrincipal>();
            final StringTokenizer iter = new StringTokenizer(user.getGroups(), ",");
            while (iter.hasMoreTokens()) {
                final String name = iter.nextToken().trim();
                groups.add(new GroupPrincipal(name));
            }
            this.userGroups.put(user.getUsername(), groups);
        }
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
    
    public void setUserGroups(final Map<String, Set<GroupPrincipal>> userGroups) {
        this.userGroups = userGroups;
    }
    
    public Map<String, String> getUserPasswords() {
        return this.userPasswords;
    }
    
    public void setUserPasswords(final Map<String, String> userPasswords) {
        this.userPasswords = userPasswords;
    }
}
