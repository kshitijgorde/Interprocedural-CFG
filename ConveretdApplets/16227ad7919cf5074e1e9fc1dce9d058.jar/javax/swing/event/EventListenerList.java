// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.EventListener;
import java.io.Serializable;

public class EventListenerList implements Serializable
{
    private static final Object[] NULL_ARRAY;
    protected transient Object[] listenerList;
    
    static {
        NULL_ARRAY = new Object[0];
    }
    
    public EventListenerList() {
        this.listenerList = EventListenerList.NULL_ARRAY;
    }
    
    public synchronized void add(final Class clazz, final EventListener eventListener) {
        if (eventListener == null) {
            return;
        }
        if (!clazz.isInstance(eventListener)) {
            throw new IllegalArgumentException("Listener " + eventListener + " is not of type " + clazz);
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
    
    public Object[] getListenerList() {
        return this.listenerList;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.listenerList = EventListenerList.NULL_ARRAY;
        objectInputStream.defaultReadObject();
        Object object;
        while ((object = objectInputStream.readObject()) != null) {
            this.add(Class.forName((String)object), (EventListener)objectInputStream.readObject());
        }
    }
    
    public synchronized void remove(final Class clazz, final EventListener eventListener) {
        if (eventListener == null) {
            return;
        }
        if (!clazz.isInstance(eventListener)) {
            throw new IllegalArgumentException("Listener " + eventListener + " is not of type " + clazz);
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
    
    public String toString() {
        final Object[] listenerList = this.listenerList;
        String s = String.valueOf("EventListenerList: ") + listenerList.length / 2 + " listeners: ";
        for (int i = 0; i <= listenerList.length - 2; i += 2) {
            s = String.valueOf(new StringBuffer(String.valueOf(s)).append(" type ").append(((Class)listenerList[i]).getName()).toString()) + " listener " + listenerList[i + 1];
        }
        return s;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        final Object[] listenerList = this.listenerList;
        objectOutputStream.defaultWriteObject();
        for (int i = 0; i < listenerList.length; i += 2) {
            final Class clazz = (Class)listenerList[i];
            final EventListener eventListener = (EventListener)listenerList[i + 1];
            if (eventListener != null && eventListener instanceof Serializable) {
                objectOutputStream.writeObject(clazz.getName());
                objectOutputStream.writeObject(eventListener);
            }
        }
        objectOutputStream.writeObject(null);
    }
}
