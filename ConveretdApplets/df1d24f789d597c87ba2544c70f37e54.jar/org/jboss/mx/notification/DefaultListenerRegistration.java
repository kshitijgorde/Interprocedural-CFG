// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class DefaultListenerRegistration implements ListenerRegistration
{
    private NotificationListener listener;
    private NotificationFilter filter;
    private Object handback;
    
    public DefaultListenerRegistration(final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        this.listener = listener;
        this.filter = filter;
        this.handback = handback;
    }
    
    public NotificationListener getListener() {
        return this.listener;
    }
    
    public NotificationFilter getFilter() {
        return this.filter;
    }
    
    public Object getHandback() {
        return this.handback;
    }
    
    public NotificationListener getRegisteredListener() {
        return this.listener;
    }
    
    public NotificationFilter getRegisteredFilter() {
        return this.filter;
    }
    
    public void removed() {
    }
    
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof ListenerRegistration)) {
            return false;
        }
        final ListenerRegistration other = (ListenerRegistration)obj;
        if (!this.getRegisteredListener().equals(other.getRegisteredListener())) {
            return false;
        }
        final NotificationFilter myFilter = this.getRegisteredFilter();
        final NotificationFilter otherFilter = other.getRegisteredFilter();
        if (myFilter != null && !myFilter.equals(otherFilter)) {
            return false;
        }
        if (myFilter == null && otherFilter != null) {
            return false;
        }
        final Object myHandback = this.getHandback();
        final Object otherHandback = other.getHandback();
        return (myHandback == null || myHandback.equals(otherHandback)) && (myHandback != null || otherHandback == null);
    }
    
    public int hashCode() {
        int result = this.listener.hashCode();
        if (this.filter != null) {
            result += this.filter.hashCode();
        }
        if (this.handback != null) {
            result += this.handback.hashCode();
        }
        return result;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(50);
        buffer.append(this.getClass()).append(":");
        buffer.append(" listener=").append(this.getRegisteredListener());
        buffer.append(" filter=").append(this.getRegisteredFilter());
        buffer.append(" handback=").append(this.getHandback());
        return buffer.toString();
    }
}
