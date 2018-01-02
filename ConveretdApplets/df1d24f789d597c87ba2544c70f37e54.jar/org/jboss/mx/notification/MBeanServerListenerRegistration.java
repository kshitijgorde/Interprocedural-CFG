// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.ObjectName;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class MBeanServerListenerRegistration extends DefaultListenerRegistration
{
    private NotificationListener proxy;
    private NotificationFilter filterProxy;
    private NotificationBroadcaster broadcaster;
    
    public MBeanServerListenerRegistration(final ObjectName name, final NotificationBroadcaster broadcaster, final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        super(listener, filter, handback);
        this.proxy = (NotificationListener)NotificationListenerProxy.newInstance(name, listener);
        this.broadcaster = broadcaster;
        this.filterProxy = ((filter == null) ? null : new NotificationFilterProxy(name, filter));
        broadcaster.addNotificationListener(this.proxy, this.filterProxy, handback);
    }
    
    public NotificationListener getListener() {
        return this.proxy;
    }
    
    public NotificationFilter getFilter() {
        return this.filterProxy;
    }
    
    public void removed() {
        try {
            if (this.broadcaster instanceof NotificationEmitter) {
                ((NotificationEmitter)this.broadcaster).removeNotificationListener(this.getListener(), this.getFilter(), this.getHandback());
            }
            else {
                this.broadcaster.removeNotificationListener(this.getListener());
            }
        }
        catch (ListenerNotFoundException ex) {}
    }
}
