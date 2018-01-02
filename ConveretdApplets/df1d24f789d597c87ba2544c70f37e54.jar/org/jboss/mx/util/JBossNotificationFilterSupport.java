// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Iterator;
import javax.management.Notification;
import java.util.Collection;
import java.util.Vector;
import org.jboss.util.CollectionsFactory;
import java.util.List;
import java.io.Serializable;
import javax.management.NotificationFilter;

public class JBossNotificationFilterSupport implements NotificationFilter, Serializable
{
    private static final long serialVersionUID = 6442164418782871672L;
    private List enabledTypes;
    
    public JBossNotificationFilterSupport() {
        this.enabledTypes = CollectionsFactory.createCopyOnWriteList();
    }
    
    public void disableAllTypes() {
        synchronized (this) {
            this.enabledTypes.clear();
        }
    }
    
    public void disableType(final String type) {
        synchronized (this) {
            this.enabledTypes.remove(type);
        }
    }
    
    public void enableType(final String type) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("null notification type");
        }
        synchronized (this) {
            if (!this.enabledTypes.contains(type)) {
                this.enabledTypes.add(type);
            }
        }
    }
    
    public Vector getEnabledTypes() {
        return new Vector(this.enabledTypes);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(100);
        sb.append(this.getClass().getName()).append(':');
        sb.append(" enabledTypes=").append(this.getEnabledTypes());
        return sb.toString();
    }
    
    public boolean isNotificationEnabled(final Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("null notification");
        }
        final String notificationType = notification.getType();
        for (final String type : this.enabledTypes) {
            if (notificationType.startsWith(type)) {
                return true;
            }
        }
        return false;
    }
}
