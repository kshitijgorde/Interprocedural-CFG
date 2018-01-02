// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.NotificationBroadcaster;
import javax.management.ObjectName;

public class MBeanServerListenerRegistrationFactory implements ListenerRegistrationFactory
{
    private ObjectName name;
    private NotificationBroadcaster broadcaster;
    
    public MBeanServerListenerRegistrationFactory(final ObjectName name, final NotificationBroadcaster broadcaster) {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        this.name = name;
        this.broadcaster = broadcaster;
    }
    
    public ListenerRegistration create(final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        return new MBeanServerListenerRegistration(this.name, this.broadcaster, listener, filter, handback);
    }
}
