// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.JMException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public interface ListenerRegistrationFactory
{
    ListenerRegistration create(final NotificationListener p0, final NotificationFilter p1, final Object p2) throws JMException;
}
