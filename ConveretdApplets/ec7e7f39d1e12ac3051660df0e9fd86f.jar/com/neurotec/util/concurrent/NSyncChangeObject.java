// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util.concurrent;

import java.util.concurrent.Executors;
import java.util.EventObject;
import com.neurotec.event.NChangeListener;
import java.util.concurrent.Executor;
import javax.swing.event.EventListenerList;
import com.neurotec.lang.NAbstractDisposable;

public final class NSyncChangeObject extends NAbstractDisposable
{
    private Object lockObject;
    private boolean changing;
    private EventListenerList listeners;
    private static Executor executor;
    private Runnable task;
    
    public NSyncChangeObject(final Object source, final Object syncObject) {
        this.lockObject = new Object();
        this.listeners = new EventListenerList();
        this.task = new Runnable() {
            public void run() {
                synchronized (NSyncChangeObject.this.lockObject) {
                    synchronized (syncObject) {
                        NSyncChangeObject.this.onChanging();
                        NSyncChangeObject.this.lockObject.notify();
                        while (true) {
                            try {
                                NSyncChangeObject.this.lockObject.wait();
                            }
                            catch (InterruptedException e) {
                                continue;
                            }
                            break;
                        }
                        NSyncChangeObject.this.onChanged();
                    }
                    NSyncChangeObject.this.lockObject.notify();
                }
            }
        };
    }
    
    public void changing() {
        synchronized (this.lockObject) {
            this.check();
            if (this.changing) {
                throw new IllegalStateException("Already changing");
            }
            NSyncChangeObject.executor.execute(this.task);
            this.changing = true;
            try {
                this.lockObject.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void changed() {
        synchronized (this.lockObject) {
            this.check();
            if (this.changing) {
                this.lockObject.notify();
                try {
                    this.lockObject.wait();
                }
                catch (InterruptedException ex) {}
                this.changing = false;
            }
        }
    }
    
    public boolean isChanging() {
        synchronized (this.lockObject) {
            this.check();
            return this.changing;
        }
    }
    
    public void dispose() {
        synchronized (this.lockObject) {
            super.dispose();
        }
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.changed();
        }
        this.task = null;
        this.lockObject = null;
        this.listeners = null;
    }
    
    public synchronized void addListener(final NChangeListener listener) {
        this.listeners.add(NChangeListener.class, listener);
    }
    
    public synchronized void removeListener(final NChangeListener listener) {
        this.listeners.remove(NChangeListener.class, listener);
    }
    
    private synchronized void onChanging() {
        final Object[] listeners = this.listeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NChangeListener.class) {
                ((NChangeListener)listeners[i + 1]).changing(new EventObject(this));
            }
        }
    }
    
    private synchronized void onChanged() {
        final Object[] listeners = this.listeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NChangeListener.class) {
                ((NChangeListener)listeners[i + 1]).changed(new EventObject(this));
            }
        }
    }
    
    static {
        NSyncChangeObject.executor = Executors.newCachedThreadPool();
    }
}
