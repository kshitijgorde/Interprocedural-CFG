// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.EventListener;

public interface NotificationListener extends EventListener
{
    void handleNotification(final Notification p0, final Object p1);
}
