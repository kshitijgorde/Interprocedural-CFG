// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.Notification;
import javax.management.ObjectName;
import javax.management.NotificationFilter;

public class NotificationFilterProxy implements NotificationFilter
{
    private static final long serialVersionUID = 1L;
    private ObjectName source;
    private NotificationFilter delegate;
    
    public NotificationFilterProxy(final ObjectName source, final NotificationFilter delegate) {
        this.source = source;
        this.delegate = delegate;
    }
    
    public boolean isNotificationEnabled(final Notification notification) {
        notification.setSource(this.source);
        return this.delegate.isNotificationEnabled(notification);
    }
    
    public ObjectName getSource() {
        return this.source;
    }
    
    public NotificationFilter getFilter() {
        return this.delegate;
    }
}
