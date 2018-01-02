// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import org.jboss.mx.notification.ListenerRegistration;
import javax.management.Notification;
import javax.management.ListenerNotFoundException;
import javax.management.JMException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;
import org.jboss.mx.notification.ListenerRegistry;
import javax.management.MBeanNotificationInfo;
import org.jboss.logging.Logger;
import javax.management.NotificationEmitter;

public class JBossNotificationBroadcasterSupport implements NotificationEmitter
{
    private static final Logger log;
    private static final MBeanNotificationInfo[] NO_NOTIFICATIONS;
    private ListenerRegistry registry;
    private SynchronizedLong sequenceNumber;
    
    public JBossNotificationBroadcasterSupport() {
        this.registry = new ListenerRegistry();
        this.sequenceNumber = new SynchronizedLong(0L);
    }
    
    public void addNotificationListener(final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        try {
            this.registry.add(listener, filter, handback);
        }
        catch (JMException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public void removeNotificationListener(final NotificationListener listener) throws ListenerNotFoundException {
        this.registry.remove(listener);
    }
    
    public void removeNotificationListener(final NotificationListener listener, final NotificationFilter filter, final Object handback) throws ListenerNotFoundException {
        this.registry.remove(listener, filter, handback);
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        return JBossNotificationBroadcasterSupport.NO_NOTIFICATIONS;
    }
    
    public void sendNotification(final Notification notification) {
        final ListenerRegistry.ListenerRegistrationIterator iterator = this.registry.iterator();
        while (iterator.hasNext()) {
            final ListenerRegistration registration = iterator.nextRegistration();
            final NotificationFilter filter = registration.getFilter();
            if (filter == null) {
                this.handleNotification(registration.getListener(), notification, registration.getHandback());
            }
            else {
                if (!filter.isNotificationEnabled(notification)) {
                    continue;
                }
                this.handleNotification(registration.getListener(), notification, registration.getHandback());
            }
        }
    }
    
    public void handleNotification(final NotificationListener listener, final Notification notification, final Object handback) {
        try {
            listener.handleNotification(notification, handback);
        }
        catch (Throwable ignored) {
            JBossNotificationBroadcasterSupport.log.debug("Ignored unhandled throwable from listener", ignored);
        }
    }
    
    public long nextNotificationSequenceNumber() {
        return this.sequenceNumber.increment();
    }
    
    static {
        log = Logger.getLogger(JBossNotificationBroadcasterSupport.class);
        NO_NOTIFICATIONS = new MBeanNotificationInfo[0];
    }
}
