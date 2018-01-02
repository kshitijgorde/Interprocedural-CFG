// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.JBossNotificationBroadcasterSupport;

public class NotificationBroadcasterSupport implements NotificationEmitter
{
    private JBossNotificationBroadcasterSupport delegate;
    
    public NotificationBroadcasterSupport() {
        this.delegate = new JBossNotificationBroadcasterSupport();
    }
    
    public void addNotificationListener(final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        this.delegate.addNotificationListener(listener, filter, handback);
    }
    
    public void removeNotificationListener(final NotificationListener listener) throws ListenerNotFoundException {
        this.delegate.removeNotificationListener(listener);
    }
    
    public void removeNotificationListener(final NotificationListener listener, final NotificationFilter filter, final Object handback) throws ListenerNotFoundException {
        this.delegate.removeNotificationListener(listener, filter, handback);
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        return this.delegate.getNotificationInfo();
    }
    
    public void sendNotification(final Notification notification) {
        this.delegate.sendNotification(notification);
    }
    
    protected void handleNotification(final NotificationListener listener, final Notification notification, final Object handback) {
        this.delegate.handleNotification(listener, notification, handback);
    }
}
