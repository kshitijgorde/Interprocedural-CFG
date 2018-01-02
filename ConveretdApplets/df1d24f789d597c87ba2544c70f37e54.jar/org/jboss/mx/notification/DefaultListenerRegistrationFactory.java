// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class DefaultListenerRegistrationFactory implements ListenerRegistrationFactory
{
    public ListenerRegistration create(final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        return new DefaultListenerRegistration(listener, filter, handback);
    }
}
