// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public interface ListenerRegistration
{
    NotificationListener getListener();
    
    NotificationFilter getFilter();
    
    Object getHandback();
    
    NotificationListener getRegisteredListener();
    
    NotificationFilter getRegisteredFilter();
    
    void removed();
}
