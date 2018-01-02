// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import java.util.Iterator;
import javax.management.ListenerNotFoundException;
import javax.management.JMException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.NotificationBroadcaster;
import javax.management.ObjectName;
import java.util.HashMap;

public class MBeanServerListenerRegistry
{
    private HashMap registries;
    
    public MBeanServerListenerRegistry() {
        this.registries = new HashMap();
    }
    
    public void add(final ObjectName name, final NotificationBroadcaster broadcaster, final NotificationListener listener, final NotificationFilter filter, final Object handback) {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Null listener");
        }
        ListenerRegistry registry = null;
        synchronized (this.registries) {
            registry = this.registries.get(name);
            if (registry == null) {
                registry = new ListenerRegistry(new MBeanServerListenerRegistrationFactory(name, broadcaster));
            }
            this.registries.put(name, registry);
        }
        try {
            registry.add(listener, filter, handback);
        }
        catch (JMException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public void remove(final ObjectName name) {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        ListenerRegistry registry = null;
        synchronized (this.registries) {
            registry = this.registries.remove(name);
            if (registry == null) {
                return;
            }
        }
        final ListenerRegistry.ListenerRegistrationIterator iterator = registry.iterator();
        while (iterator.hasNext()) {
            final ListenerRegistration registration = iterator.nextRegistration();
            registration.removed();
        }
    }
    
    public void remove(final ObjectName name, final NotificationListener listener) throws ListenerNotFoundException {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        synchronized (this.registries) {
            final ListenerRegistry registry = this.registries.get(name);
            if (registry == null) {
                throw new ListenerNotFoundException("Listener not found " + listener + " for object name " + name);
            }
            registry.remove(listener);
            if (registry.isEmpty()) {
                this.registries.remove(name);
            }
        }
    }
    
    public void remove(final ObjectName name, final NotificationListener listener, final NotificationFilter filter, final Object handback) throws ListenerNotFoundException {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        synchronized (this.registries) {
            final ListenerRegistry registry = this.registries.get(name);
            if (registry == null) {
                throw new ListenerNotFoundException("Listener not found listener=" + listener + " filter=" + filter + " handback=" + handback + " for object name " + name);
            }
            registry.remove(listener, filter, handback);
            if (registry.isEmpty()) {
                this.registries.remove(name);
            }
        }
    }
    
    public void removeAll() {
        synchronized (this.registries) {
            final Iterator it = this.registries.keySet().iterator();
            while (it.hasNext()) {
                final ListenerRegistry registry = this.registries.get(it.next());
                registry.removeAll();
            }
        }
    }
}
