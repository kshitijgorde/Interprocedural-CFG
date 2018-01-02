// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
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
    
    public synchronized void addHandler(final BusHandler handler) {
        this.handlers.addElement(handler);
    }
    
    public synchronized void removeHandler(final BusHandler handler) {
        this.handlers.removeElement(handler);
    }
    
    public synchronized void setSyncHandler(final BusSyncHandler handler) {
        this.syncHandler = handler;
    }
    
    private void notifyHandlers(final Vector handlers, final Message message) {
        final Enumeration e = handlers.elements();
        while (e.hasMoreElements()) {
            final BusHandler handler = e.nextElement();
            handler.handleMessage(message);
        }
    }
    
    public void post(final Message message) {
        boolean post = true;
        final BusSyncHandler handler;
        synchronized (this) {
            if (this.flushing) {
                return;
            }
            handler = this.syncHandler;
        }
        post = (handler == null || handler.handleSyncMessage(message) == 1);
        synchronized (this) {
            if (post && !this.flushing) {
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
        final Message ret = this.queue.elementAt(0);
        this.queue.removeElementAt(0);
        return ret;
    }
    
    public synchronized Message poll(final long timeout) {
        if (this.queue.isEmpty() && !this.flushing) {
            try {
                this.wait(timeout);
            }
            catch (InterruptedException ex) {}
        }
        return this.pop();
    }
    
    public synchronized void setFlushing(final boolean flush) {
        this.flushing = flush;
        this.queue.setSize(0);
        this.notifyAll();
    }
    
    public void waitAndDispatch() {
        final Message msg = this.poll(0L);
        if (msg != null) {
            this.notifyHandlers(this.handlers, msg);
        }
    }
}
