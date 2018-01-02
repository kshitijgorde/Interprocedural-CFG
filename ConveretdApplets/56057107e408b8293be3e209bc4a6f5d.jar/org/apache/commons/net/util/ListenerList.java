// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.util;

import java.util.Enumeration;
import java.util.EventListener;
import java.util.Vector;
import java.io.Serializable;

public class ListenerList implements Serializable
{
    private Vector __listeners;
    
    public ListenerList() {
        this.__listeners = new Vector();
    }
    
    public synchronized void addListener(final EventListener listener) {
        this.__listeners.addElement(listener);
    }
    
    public synchronized void removeListener(final EventListener listener) {
        this.__listeners.removeElement(listener);
    }
    
    public synchronized Enumeration getListeners() {
        return ((Vector)this.__listeners.clone()).elements();
    }
    
    public int getListenerCount() {
        return this.__listeners.size();
    }
}
