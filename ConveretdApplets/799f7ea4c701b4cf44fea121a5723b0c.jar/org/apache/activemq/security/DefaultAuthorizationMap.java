// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.Set;
import java.util.List;
import org.apache.activemq.filter.DestinationMap;

public class DefaultAuthorizationMap extends DestinationMap implements AuthorizationMap
{
    private AuthorizationEntry defaultEntry;
    private TempDestinationAuthorizationEntry tempDestinationAuthorizationEntry;
    
    public DefaultAuthorizationMap() {
    }
    
    public DefaultAuthorizationMap(final List authorizationEntries) {
        this.setAuthorizationEntries(authorizationEntries);
    }
    
    public void setTempDestinationAuthorizationEntry(final TempDestinationAuthorizationEntry tempDestinationAuthorizationEntry) {
        this.tempDestinationAuthorizationEntry = tempDestinationAuthorizationEntry;
    }
    
    public TempDestinationAuthorizationEntry getTempDestinationAuthorizationEntry() {
        return this.tempDestinationAuthorizationEntry;
    }
    
    @Override
    public Set<Object> getTempDestinationAdminACLs() {
        if (this.tempDestinationAuthorizationEntry != null) {
            return this.tempDestinationAuthorizationEntry.getAdminACLs();
        }
        return null;
    }
    
    @Override
    public Set<Object> getTempDestinationReadACLs() {
        if (this.tempDestinationAuthorizationEntry != null) {
            return this.tempDestinationAuthorizationEntry.getReadACLs();
        }
        return null;
    }
    
    @Override
    public Set<Object> getTempDestinationWriteACLs() {
        if (this.tempDestinationAuthorizationEntry != null) {
            return this.tempDestinationAuthorizationEntry.getWriteACLs();
        }
        return null;
    }
    
    @Override
    public Set<Object> getAdminACLs(final ActiveMQDestination destination) {
        final Set<AuthorizationEntry> entries = this.getAllEntries(destination);
        final Set<Object> answer = new HashSet<Object>();
        for (final AuthorizationEntry entry : entries) {
            answer.addAll(entry.getAdminACLs());
        }
        return answer;
    }
    
    @Override
    public Set<Object> getReadACLs(final ActiveMQDestination destination) {
        final Set<AuthorizationEntry> entries = this.getAllEntries(destination);
        final Set<Object> answer = new HashSet<Object>();
        for (final AuthorizationEntry entry : entries) {
            answer.addAll(entry.getReadACLs());
        }
        return answer;
    }
    
    @Override
    public Set<Object> getWriteACLs(final ActiveMQDestination destination) {
        final Set<AuthorizationEntry> entries = this.getAllEntries(destination);
        final Set<Object> answer = new HashSet<Object>();
        for (final AuthorizationEntry entry : entries) {
            answer.addAll(entry.getWriteACLs());
        }
        return answer;
    }
    
    public AuthorizationEntry getEntryFor(final ActiveMQDestination destination) {
        AuthorizationEntry answer = (AuthorizationEntry)this.chooseValue(destination);
        if (answer == null) {
            answer = this.getDefaultEntry();
        }
        return answer;
    }
    
    public void setAuthorizationEntries(final List entries) {
        super.setEntries(entries);
    }
    
    public AuthorizationEntry getDefaultEntry() {
        return this.defaultEntry;
    }
    
    public void setDefaultEntry(final AuthorizationEntry defaultEntry) {
        this.defaultEntry = defaultEntry;
    }
    
    @Override
    protected Class<AuthorizationEntry> getEntryClass() {
        return AuthorizationEntry.class;
    }
    
    protected Set<AuthorizationEntry> getAllEntries(final ActiveMQDestination destination) {
        final Set<AuthorizationEntry> entries = (Set<AuthorizationEntry>)this.get(destination);
        if (this.defaultEntry != null) {
            entries.add(this.defaultEntry);
        }
        return entries;
    }
}
