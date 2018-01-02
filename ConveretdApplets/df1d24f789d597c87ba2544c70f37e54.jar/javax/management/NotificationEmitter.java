// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public interface NotificationEmitter extends NotificationBroadcaster
{
    void removeNotificationListener(final NotificationListener p0, final NotificationFilter p1, final Object p2) throws ListenerNotFoundException;
}
