// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Enumeration;
import java.util.Vector;

public class ListenerGroup
{
    private Vector _listeners;
    
    public ListenerGroup() {
        this._listeners = new Vector();
    }
    
    public synchronized void addListener(final Object o) {
        this._listeners.insertElementAt(o, this._listeners.size());
    }
    
    public synchronized void removeListener(final Object o) {
        for (int i = 0; i < this._listeners.size(); ++i) {
            if (this._listeners.elementAt(i) == o) {
                this._listeners.removeElementAt(i);
                return;
            }
        }
    }
    
    public synchronized void sendEventAsync(final String s, final Object[] array) {
        final Enumeration<Object> elements = this._listeners.elements();
        while (elements.hasMoreElements()) {
            EventDispatcher.dispatchEventAsync(elements.nextElement(), s, array);
        }
    }
    
    public synchronized void sendEventAsync(final String s) {
        this.sendEventAsync(s, new Object[0]);
    }
    
    public synchronized void sendEventAsync(final String s, final Object o) {
        this.sendEventAsync(s, new Object[] { o });
    }
    
    public synchronized void sendEventAsync(final String s, final Object o, final Object o2) {
        this.sendEventAsync(s, new Object[] { o, o2 });
    }
    
    public synchronized void sendEventAsync(final String s, final Object o, final Object o2, final Object o3) {
        this.sendEventAsync(s, new Object[] { o, o2, o3 });
    }
    
    public synchronized Object[] sendEvent(final String s, final Object[] array) {
        try {
            return this.sendEventEx(s, array);
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    public synchronized Object[] sendEventEx(final String s, final Object[] array) throws Throwable {
        final Object[] array2 = new Object[this._listeners.size()];
        int n = 0;
        final Enumeration<Object> elements = this._listeners.elements();
        while (elements.hasMoreElements()) {
            try {
                array2[n++] = EventDispatcher.dispatchEventSyncEx(elements.nextElement(), s, array);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return array2;
    }
    
    public synchronized Object[] sendEvent(final String s) {
        return this.sendEvent(s, new Object[0]);
    }
    
    public synchronized Object[] sendEvent(final String s, final Object o) {
        return this.sendEvent(s, new Object[] { o });
    }
    
    public synchronized Object[] sendEvent(final String s, final Object o, final Object o2) {
        return this.sendEvent(s, new Object[] { o, o2 });
    }
    
    public synchronized Object[] sendEvent(final String s, final Object o, final Object o2, final Object o3) {
        return this.sendEvent(s, new Object[] { o, o2, o3 });
    }
    
    public synchronized Object[] sendEventEx(final String s) throws Throwable {
        return this.sendEventEx(s, new Object[0]);
    }
    
    public synchronized Object[] sendEventEx(final String s, final Object o) throws Throwable {
        return this.sendEventEx(s, new Object[] { o });
    }
    
    public synchronized Object[] sendEventEx(final String s, final Object o, final Object o2) throws Throwable {
        return this.sendEventEx(s, new Object[] { o, o2 });
    }
    
    public synchronized Object[] sendEventEx(final String s, final Object o, final Object o2, final Object o3) throws Throwable {
        return this.sendEventEx(s, new Object[] { o, o2, o3 });
    }
}
