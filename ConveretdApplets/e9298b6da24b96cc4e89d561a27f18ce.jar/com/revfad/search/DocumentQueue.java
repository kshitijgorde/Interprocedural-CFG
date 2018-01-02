// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

import java.util.Vector;

public class DocumentQueue
{
    private Vector queue;
    private boolean killed;
    
    public DocumentQueue() {
        this.queue = new Vector();
        this.killed = false;
    }
    
    public DocumentQueue(final Vector vector) {
        this.queue = (Vector)vector.clone();
        this.killed = false;
    }
    
    public Document next() {
        synchronized (this.queue) {
            while (this.queue.isEmpty() && !this.killed) {
                try {
                    this.queue.wait();
                    continue;
                }
                catch (InterruptedException ex) {
                    return null;
                }
                break;
            }
            if (this.killed) {
                return null;
            }
            final Document document = this.queue.firstElement();
            this.queue.removeElementAt(0);
            return document;
        }
    }
    
    public void addLast(final Document document) {
        synchronized (this.queue) {
            this.queue.addElement(document);
            this.queue.notify();
        }
    }
    
    public boolean isEmpty() {
        synchronized (this.queue) {
            return this.queue.isEmpty();
        }
    }
    
    public void kill() {
        synchronized (this.queue) {
            this.killed = true;
            this.queue.notifyAll();
        }
    }
}
