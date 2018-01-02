// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.beans.VetoableChangeListener;
import java.io.Serializable;

public class VetoableChangeMulticaster implements Serializable
{
    protected transient VetoableChangeListener[] listeners;
    protected final Object source;
    protected HashMap children;
    
    public VetoableChangeMulticaster(final Object source) {
        this.listeners = new VetoableChangeListener[0];
        if (source == null) {
            throw new NullPointerException();
        }
        this.source = source;
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener vetoableChangeListener) {
        if (vetoableChangeListener == null) {
            throw new NullPointerException();
        }
        final int length = this.listeners.length;
        final VetoableChangeListener[] listeners = new VetoableChangeListener[length + 1];
        if (length > 0) {
            System.arraycopy(this.listeners, 0, listeners, 0, length);
        }
        listeners[length] = vetoableChangeListener;
        this.listeners = listeners;
    }
    
    public void addVetoableChangeListener(final String s, final VetoableChangeListener vetoableChangeListener) {
        if (vetoableChangeListener == null) {
            throw new NullPointerException();
        }
        VetoableChangeMulticaster vetoableChangeMulticaster = null;
        synchronized (this) {
            if (this.children == null) {
                this.children = new HashMap();
            }
            else {
                vetoableChangeMulticaster = this.children.get(s);
            }
            if (vetoableChangeMulticaster == null) {
                vetoableChangeMulticaster = new VetoableChangeMulticaster(this.source);
                this.children.put(s, vetoableChangeMulticaster);
            }
        }
        vetoableChangeMulticaster.addVetoableChangeListener(vetoableChangeListener);
    }
    
    public synchronized void addVetoableChangeListenerIfAbsent(final VetoableChangeListener vetoableChangeListener) {
        if (vetoableChangeListener == null) {
            throw new NullPointerException();
        }
        final int length = this.listeners.length;
        final VetoableChangeListener[] listeners = new VetoableChangeListener[length + 1];
        for (int i = 0; i < length; ++i) {
            listeners[i] = this.listeners[i];
            if (vetoableChangeListener.equals(this.listeners[i])) {
                return;
            }
        }
        listeners[length] = vetoableChangeListener;
        this.listeners = listeners;
    }
    
    public void addVetoableChangeListenerIfAbsent(final String s, final VetoableChangeListener vetoableChangeListener) {
        if (vetoableChangeListener == null) {
            throw new NullPointerException();
        }
        VetoableChangeMulticaster vetoableChangeMulticaster = null;
        synchronized (this) {
            if (this.children == null) {
                this.children = new HashMap();
            }
            else {
                vetoableChangeMulticaster = this.children.get(s);
            }
            if (vetoableChangeMulticaster == null) {
                vetoableChangeMulticaster = new VetoableChangeMulticaster(this.source);
                this.children.put(s, vetoableChangeMulticaster);
            }
        }
        vetoableChangeMulticaster.addVetoableChangeListenerIfAbsent(vetoableChangeListener);
    }
    
    public void fireVetoableChange(final PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        final Object oldValue = propertyChangeEvent.getOldValue();
        final Object newValue = propertyChangeEvent.getNewValue();
        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            this.multicast(propertyChangeEvent);
        }
    }
    
    public void fireVetoableChange(final String s, final int n, final int n2) throws PropertyVetoException {
        if (n != n2) {
            this.multicast(new PropertyChangeEvent(this.source, s, new Integer(n), new Integer(n2)));
        }
    }
    
    public void fireVetoableChange(final String s, final Object o, final Object o2) throws PropertyVetoException {
        if (o == null || o2 == null || !o.equals(o2)) {
            this.multicast(new PropertyChangeEvent(this.source, s, o, o2));
        }
    }
    
    public void fireVetoableChange(final String s, final boolean b, final boolean b2) throws PropertyVetoException {
        if (b != b2) {
            this.multicast(new PropertyChangeEvent(this.source, s, new Boolean(b), new Boolean(b2)));
        }
    }
    
    protected synchronized VetoableChangeMulticaster getChild(final String s) {
        return (this.children == null) ? null : this.children.get(s);
    }
    
    public boolean hasListeners(final String s) {
        final VetoableChangeMulticaster vetoableChangeMulticaster;
        synchronized (this) {
            if (this.listeners.length > 0) {
                // monitorexit(this)
                return true;
            }
            if (s == null || this.children == null) {
                // monitorexit(this)
                return false;
            }
            vetoableChangeMulticaster = this.children.get(s);
            if (vetoableChangeMulticaster == null) {
                // monitorexit(this)
                return false;
            }
        }
        return vetoableChangeMulticaster.hasListeners(null);
    }
    
    protected void multicast(final PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        VetoableChangeMulticaster vetoableChangeMulticaster = null;
        final VetoableChangeListener[] listeners;
        synchronized (this) {
            listeners = this.listeners;
            if (this.children != null && propertyChangeEvent.getPropertyName() != null) {
                vetoableChangeMulticaster = this.children.get(propertyChangeEvent.getPropertyName());
            }
        }
        int i = 0;
        try {
            for (i = 0; i < listeners.length; ++i) {
                listeners[i].vetoableChange(propertyChangeEvent);
            }
            if (vetoableChangeMulticaster != null) {
                vetoableChangeMulticaster.multicast(propertyChangeEvent);
            }
        }
        catch (PropertyVetoException ex) {
            final PropertyChangeEvent propertyChangeEvent2 = new PropertyChangeEvent(propertyChangeEvent.getSource(), propertyChangeEvent.getPropertyName(), propertyChangeEvent.getNewValue(), propertyChangeEvent.getOldValue());
            for (int n = (i < listeners.length) ? i : (listeners.length - 1), j = 0; j <= n; ++j) {
                try {
                    listeners[j].vetoableChange(propertyChangeEvent2);
                }
                catch (PropertyVetoException ex2) {}
            }
            throw ex;
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.listeners = new VetoableChangeListener[0];
        objectInputStream.defaultReadObject();
        Object object;
        while ((object = objectInputStream.readObject()) != null) {
            this.addVetoableChangeListener((VetoableChangeListener)object);
        }
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener vetoableChangeListener) {
        final int n = this.listeners.length - 1;
        if (n < 0 || vetoableChangeListener == null) {
            return;
        }
        final VetoableChangeListener[] array = new VetoableChangeListener[n];
        for (int i = 0; i < n; ++i) {
            if (vetoableChangeListener.equals(this.listeners[i])) {
                for (int j = i + 1; j <= n; ++j) {
                    array[j - 1] = this.listeners[j];
                }
                this.listeners = array;
                return;
            }
            array[i] = this.listeners[i];
        }
        if (vetoableChangeListener.equals(this.listeners[n])) {
            this.listeners = array;
        }
    }
    
    public void removeVetoableChangeListener(final String s, final VetoableChangeListener vetoableChangeListener) {
        final VetoableChangeMulticaster child = this.getChild(s);
        if (child != null) {
            child.removeVetoableChangeListener(vetoableChangeListener);
        }
    }
    
    private synchronized void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        for (int i = 0; i < this.listeners.length; ++i) {
            final VetoableChangeListener vetoableChangeListener = this.listeners[i];
            if (this.listeners[i] instanceof Serializable) {
                objectOutputStream.writeObject(this.listeners[i]);
            }
        }
        objectOutputStream.writeObject(null);
    }
}
