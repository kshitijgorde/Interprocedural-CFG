// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
import com.fluendo.utils.Debug;
import java.util.Vector;

public class Bus
{
    private Vector queue;
    private Vector handlers;
    private boolean flushing;
    private BusSyncHandler syncHandler;
    
    public Bus() {
        this.queue = new Vector();
        this.handlers = new Vector();
        this.flushing = false;
    }
    
    public synchronized void addHandler(final BusHandler busHandler) {
        this.handlers.addElement(busHandler);
    }
    
    public synchronized void removeHandler(final BusHandler busHandler) {
        this.handlers.removeElement(busHandler);
    }
    
    public synchronized void setSyncHandler(final BusSyncHandler syncHandler) {
        this.syncHandler = syncHandler;
    }
    
    private void notifyHandlers(final Vector vector, final Message message) {
        Debug.debug("Bus.notifyHandlers: " + message);
        final Enumeration<BusHandler> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final BusHandler busHandler = elements.nextElement();
            Debug.debug("Notifying " + busHandler);
            busHandler.handleMessage(message);
        }
    }
    
    public void post(final Message message) {
        Debug.debug("Bus.post: " + message);
        final BusSyncHandler syncHandler;
        synchronized (this) {
            if (this.flushing) {
                return;
            }
            syncHandler = this.syncHandler;
        }
        final boolean b = syncHandler == null || syncHandler.handleSyncMessage(message) == 1;
        synchronized (this) {
            if (b && !this.flushing) {
                this.queue.addElement(message);
                this.notifyAll();
            }
        }
    }
    
    public synchronized Message peek() {
        if (this.queue.isEmpty() || this.flushing) {
            return null;
        }
        return this.queue.firstElement();
    }
    
    public synchronized Message pop() {
        if (this.queue.isEmpty() || this.flushing) {
            return null;
        }
        final Message message = this.queue.elementAt(0);
        this.queue.removeElementAt(0);
        return message;
    }
    
    public synchronized Message poll(final long n) {
        if (this.queue.isEmpty() && !this.flushing) {
            try {
                this.wait(n);
            }
            catch (InterruptedException ex) {}
        }
        return this.pop();
    }
    
    public synchronized void setFlushing(final boolean flushing) {
        this.flushing = flushing;
        this.queue.setSize(0);
        this.notifyAll();
    }
    
    public void waitAndDispatch() {
        final Message poll = this.poll(0L);
        if (poll != null) {
            this.notifyHandlers(this.handlers, poll);
        }
    }
}
