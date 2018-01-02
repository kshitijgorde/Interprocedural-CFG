// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee;

import javax.management.ListenerNotFoundException;
import java.rmi.RemoteException;
import javax.management.InstanceNotFoundException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import java.io.Serializable;

public interface ListenerRegistration extends Serializable
{
    void addNotificationListener(final ObjectName p0, final NotificationListener p1, final NotificationFilter p2, final Object p3) throws InstanceNotFoundException, RemoteException;
    
    void removeNotificationListener(final ObjectName p0, final NotificationListener p1) throws InstanceNotFoundException, ListenerNotFoundException, RemoteException;
}
