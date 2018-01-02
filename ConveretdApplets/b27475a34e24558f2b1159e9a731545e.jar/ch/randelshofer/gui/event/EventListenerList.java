// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui.event;

import java.util.EventListener;

public class EventListenerList
{
    private static final Object[] NULL_ARRAY;
    protected Object[] listenerList;
    
    public EventListenerList() {
        this.listenerList = EventListenerList.NULL_ARRAY;
    }
    
    public Object[] getListenerList() {
        return this.listenerList;
    }
    
    public int getListenerCount() {
        return this.listenerList.length / 2;
    }
    
    public int getListenerCount(final Class clazz) {
        int n = 0;
        final Object[] listenerList = this.listenerList;
        for (int i = 0; i < listenerList.length; i += 2) {
            if (clazz == listenerList[i]) {
                ++n;
            }
        }
        return n;
    }
    
    public synchronized void add(final Class clazz, final EventListener eventListener) {
        if (!clazz.isInstance(eventListener)) {
            throw new IllegalArgumentException("Listener " + eventListener + " is not of type " + clazz);
        }
        if (eventListener == null) {
            throw new IllegalArgumentException("Listener " + eventListener + " is null");
        }
        if (this.listenerList == EventListenerList.NULL_ARRAY) {
            this.listenerList = new Object[] { clazz, eventListener };
        }
        else {
            final int length = this.listenerList.length;
            final Object[] listenerList = new Object[length + 2];
            System.arraycopy(this.listenerList, 0, listenerList, 0, length);
            listenerList[length] = clazz;
            listenerList[length + 1] = eventListener;
            this.listenerList = listenerList;
        }
    }
    
    public synchronized void remove(final Class clazz, final EventListener eventListener) {
        if (!clazz.isInstance(eventListener)) {
            throw new IllegalArgumentException("Listener " + eventListener + " is not of type " + clazz);
        }
        if (eventListener == null) {
            throw new IllegalArgumentException("Listener " + eventListener + " is null");
        }
        int n = -1;
        for (int i = this.listenerList.length - 2; i >= 0; i -= 2) {
            if (this.listenerList[i] == clazz && this.listenerList[i + 1].equals(eventListener)) {
                n = i;
                break;
            }
        }
        if (n != -1) {
            final Object[] array = new Object[this.listenerList.length - 2];
            System.arraycopy(this.listenerList, 0, array, 0, n);
            if (n < array.length) {
                System.arraycopy(this.listenerList, n + 2, array, n, array.length - n);
            }
            this.listenerList = ((array.length == 0) ? EventListenerList.NULL_ARRAY : array);
        }
    }
    
    static {
        NULL_ARRAY = new Object[0];
    }
}
