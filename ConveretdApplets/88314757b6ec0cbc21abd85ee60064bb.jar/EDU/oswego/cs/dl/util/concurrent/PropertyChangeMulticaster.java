// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class PropertyChangeMulticaster implements Serializable
{
    protected transient PropertyChangeListener[] listeners;
    protected final Object source;
    protected HashMap children;
    
    public PropertyChangeMulticaster(final Object source) {
        this.listeners = new PropertyChangeListener[0];
        if (source == null) {
            throw new NullPointerException();
        }
        this.source = source;
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            throw new NullPointerException();
        }
        final int length = this.listeners.length;
        final PropertyChangeListener[] listeners = new PropertyChangeListener[length + 1];
        if (length > 0) {
            System.arraycopy(this.listeners, 0, listeners, 0, length);
        }
        listeners[length] = propertyChangeListener;
        this.listeners = listeners;
    }
    
    public void addPropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            throw new NullPointerException();
        }
        PropertyChangeMulticaster propertyChangeMulticaster = null;
        synchronized (this) {
            if (this.children == null) {
                this.children = new HashMap();
            }
            else {
                propertyChangeMulticaster = this.children.get(s);
            }
            if (propertyChangeMulticaster == null) {
                propertyChangeMulticaster = new PropertyChangeMulticaster(this.source);
                this.children.put(s, propertyChangeMulticaster);
            }
        }
        propertyChangeMulticaster.addPropertyChangeListener(propertyChangeListener);
    }
    
    public synchronized void addPropertyChangeListenerIfAbsent(final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            throw new NullPointerException();
        }
        final int length = this.listeners.length;
        final PropertyChangeListener[] listeners = new PropertyChangeListener[length + 1];
        for (int i = 0; i < length; ++i) {
            listeners[i] = this.listeners[i];
            if (propertyChangeListener.equals(this.listeners[i])) {
                return;
            }
        }
        listeners[length] = propertyChangeListener;
        this.listeners = listeners;
    }
    
    public void addPropertyChangeListenerIfAbsent(final String s, final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            throw new NullPointerException();
        }
        PropertyChangeMulticaster propertyChangeMulticaster = null;
        synchronized (this) {
            if (this.children == null) {
                this.children = new HashMap();
            }
            else {
                propertyChangeMulticaster = this.children.get(s);
            }
            if (propertyChangeMulticaster == null) {
                propertyChangeMulticaster = new PropertyChangeMulticaster(this.source);
                this.children.put(s, propertyChangeMulticaster);
            }
        }
        propertyChangeMulticaster.addPropertyChangeListenerIfAbsent(propertyChangeListener);
    }
    
    public void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object oldValue = propertyChangeEvent.getOldValue();
        final Object newValue = propertyChangeEvent.getNewValue();
        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            this.multicast(propertyChangeEvent);
        }
    }
    
    public void firePropertyChange(final String s, final int n, final int n2) {
        if (n != n2) {
            this.multicast(new PropertyChangeEvent(this.source, s, new Integer(n), new Integer(n2)));
        }
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        if (o == null || o2 == null || !o.equals(o2)) {
            this.multicast(new PropertyChangeEvent(this.source, s, o, o2));
        }
    }
    
    public void firePropertyChange(final String s, final boolean b, final boolean b2) {
        if (b != b2) {
            this.multicast(new PropertyChangeEvent(this.source, s, new Boolean(b), new Boolean(b2)));
        }
    }
    
    protected synchronized PropertyChangeMulticaster getChild(final String s) {
        return (this.children == null) ? null : this.children.get(s);
    }
    
    public boolean hasListeners(final String s) {
        final PropertyChangeMulticaster propertyChangeMulticaster;
        synchronized (this) {
            if (this.listeners.length > 0) {
                // monitorexit(this)
                return true;
            }
            if (s == null || this.children == null) {
                // monitorexit(this)
                return false;
            }
            propertyChangeMulticaster = this.children.get(s);
            if (propertyChangeMulticaster == null) {
                // monitorexit(this)
                return false;
            }
        }
        return propertyChangeMulticaster.hasListeners(null);
    }
    
    protected void multicast(final PropertyChangeEvent propertyChangeEvent) {
        PropertyChangeMulticaster propertyChangeMulticaster = null;
        final PropertyChangeListener[] listeners;
        synchronized (this) {
            listeners = this.listeners;
            if (this.children != null && propertyChangeEvent.getPropertyName() != null) {
                propertyChangeMulticaster = this.children.get(propertyChangeEvent.getPropertyName());
            }
        }
        for (int i = 0; i < listeners.length; ++i) {
            listeners[i].propertyChange(propertyChangeEvent);
        }
        if (propertyChangeMulticaster != null) {
            propertyChangeMulticaster.multicast(propertyChangeEvent);
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.listeners = new PropertyChangeListener[0];
        objectInputStream.defaultReadObject();
        Object object;
        while ((object = objectInputStream.readObject()) != null) {
            this.addPropertyChangeListener((PropertyChangeListener)object);
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        final int n = this.listeners.length - 1;
        if (n < 0 || propertyChangeListener == null) {
            return;
        }
        final PropertyChangeListener[] array = new PropertyChangeListener[n];
        for (int i = 0; i < n; ++i) {
            if (propertyChangeListener.equals(this.listeners[i])) {
                for (int j = i + 1; j <= n; ++j) {
                    array[j - 1] = this.listeners[j];
                }
                this.listeners = array;
                return;
            }
            array[i] = this.listeners[i];
        }
        if (propertyChangeListener.equals(this.listeners[n])) {
            this.listeners = array;
        }
    }
    
    public void removePropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        final PropertyChangeMulticaster child = this.getChild(s);
        if (child != null) {
            child.removePropertyChangeListener(propertyChangeListener);
        }
    }
    
    private synchronized void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        for (int i = 0; i < this.listeners.length; ++i) {
            final PropertyChangeListener propertyChangeListener = this.listeners[i];
            if (this.listeners[i] instanceof Serializable) {
                objectOutputStream.writeObject(this.listeners[i]);
            }
        }
        objectOutputStream.writeObject(null);
    }
}
