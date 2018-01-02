// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import java.util.NoSuchElementException;
import java.util.Iterator;
import javax.management.ListenerNotFoundException;
import javax.management.JMException;
import java.util.ArrayList;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import java.util.HashMap;

public class ListenerRegistry
{
    private HashMap listeners;
    private ListenerRegistrationFactory factory;
    
    public ListenerRegistry() {
        this(null);
    }
    
    public ListenerRegistry(final ListenerRegistrationFactory factory) {
        this.listeners = new HashMap();
        if (factory == null) {
            this.factory = new DefaultListenerRegistrationFactory();
        }
        else {
            this.factory = factory;
        }
    }
    
    public void add(final NotificationListener listener, final NotificationFilter filter, final Object handback) throws JMException {
        if (listener == null) {
            throw new IllegalArgumentException("Null listener");
        }
        synchronized (this.listeners) {
            final HashMap newListeners = (HashMap)this.listeners.clone();
            ArrayList registrations = newListeners.get(listener);
            if (registrations == null) {
                registrations = new ArrayList();
                newListeners.put(listener, registrations);
            }
            else {
                registrations = (ArrayList)registrations.clone();
                newListeners.put(listener, registrations);
            }
            registrations.add(this.factory.create(listener, filter, handback));
            this.listeners = newListeners;
        }
    }
    
    public void remove(final NotificationListener listener) throws ListenerNotFoundException {
        ArrayList registrations = null;
        synchronized (this.listeners) {
            if (!this.listeners.containsKey(listener)) {
                throw new ListenerNotFoundException("Listener not found " + listener);
            }
            final HashMap newListeners = (HashMap)this.listeners.clone();
            registrations = newListeners.remove(listener);
            this.listeners = newListeners;
        }
        for (final ListenerRegistration registration : registrations) {
            registration.removed();
        }
    }
    
    public void remove(final NotificationListener listener, final NotificationFilter filter, final Object handback) throws ListenerNotFoundException {
        ListenerRegistration registration = null;
        synchronized (this.listeners) {
            ArrayList registrations = this.listeners.get(listener);
            if (registrations == null) {
                throw new ListenerNotFoundException("No registristrations for listener not listener=" + listener + " filter=" + filter + " handback=" + handback);
            }
            registration = new DefaultListenerRegistration(listener, filter, handback);
            final int index = registrations.indexOf(registration);
            if (index == -1) {
                throw new ListenerNotFoundException("Listener not found listener=" + listener + " filter=" + filter + " handback=" + handback);
            }
            final HashMap newListeners = (HashMap)this.listeners.clone();
            registrations = (ArrayList)registrations.clone();
            registration = registrations.remove(index);
            if (registrations.isEmpty()) {
                newListeners.remove(listener);
            }
            else {
                newListeners.put(listener, registrations);
            }
            this.listeners = newListeners;
        }
        registration.removed();
    }
    
    public void removeAll() {
        synchronized (this.listeners) {
            this.listeners.clear();
        }
    }
    
    public ListenerRegistrationIterator iterator() {
        return new ListenerRegistrationIterator();
    }
    
    public boolean isEmpty() {
        return this.listeners.isEmpty();
    }
    
    public class ListenerRegistrationIterator implements Iterator
    {
        private Iterator listenerIterator;
        private Iterator registrationIterator;
        
        public ListenerRegistrationIterator() {
            this.listenerIterator = ListenerRegistry.this.listeners.values().iterator();
        }
        
        public boolean hasNext() {
            if (this.registrationIterator == null || !this.registrationIterator.hasNext()) {
                while (this.listenerIterator.hasNext()) {
                    this.registrationIterator = this.listenerIterator.next().iterator();
                    if (this.registrationIterator.hasNext()) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Use hasNext before next");
            }
            return this.registrationIterator.next();
        }
        
        public ListenerRegistration nextRegistration() {
            return (ListenerRegistration)this.next();
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }
    }
}
