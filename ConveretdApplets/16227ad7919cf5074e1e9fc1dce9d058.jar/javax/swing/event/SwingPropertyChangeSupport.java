// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.util.Vector;
import java.beans.PropertyChangeSupport;

public final class SwingPropertyChangeSupport extends PropertyChangeSupport
{
    private transient Vector listeners;
    private Hashtable children;
    private Object source;
    static final long serialVersionUID = 7162625831330845068L;
    
    public SwingPropertyChangeSupport(final Object source) {
        super(source);
        this.source = source;
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.listeners == null) {
            this.listeners = new Vector();
        }
        this.listeners.addElement(propertyChangeListener);
    }
    
    public synchronized void addPropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (this.children == null) {
            this.children = new Hashtable();
        }
        SwingPropertyChangeSupport swingPropertyChangeSupport = this.children.get(s);
        if (swingPropertyChangeSupport == null) {
            swingPropertyChangeSupport = new SwingPropertyChangeSupport(this.source);
            this.children.put(s, swingPropertyChangeSupport);
        }
        swingPropertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object oldValue = propertyChangeEvent.getOldValue();
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
            return;
        }
        SwingPropertyChangeSupport swingPropertyChangeSupport = null;
        synchronized (this) {
            if (this.children != null && propertyName != null) {
                swingPropertyChangeSupport = this.children.get(propertyName);
            }
        }
        if (this.listeners != null) {
            for (int size = this.listeners.size(), i = 0; i < size; ++i) {
                ((PropertyChangeListener)this.listeners.elementAt(i)).propertyChange(propertyChangeEvent);
            }
        }
        if (swingPropertyChangeSupport != null) {
            swingPropertyChangeSupport.firePropertyChange(propertyChangeEvent);
        }
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        if (o != null && o2 != null && o.equals(o2)) {
            return;
        }
        SwingPropertyChangeSupport swingPropertyChangeSupport = null;
        synchronized (this) {
            if (this.children != null && s != null) {
                swingPropertyChangeSupport = this.children.get(s);
            }
        }
        if (this.listeners != null || swingPropertyChangeSupport != null) {
            final PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this.source, s, o, o2);
            if (this.listeners != null) {
                for (int i = 0; i < this.listeners.size(); ++i) {
                    ((PropertyChangeListener)this.listeners.elementAt(i)).propertyChange(propertyChangeEvent);
                }
            }
            if (swingPropertyChangeSupport != null) {
                swingPropertyChangeSupport.firePropertyChange(propertyChangeEvent);
            }
        }
    }
    
    public synchronized boolean hasListeners(final String s) {
        if (this.listeners != null && !this.listeners.isEmpty()) {
            return true;
        }
        if (this.children != null) {
            final SwingPropertyChangeSupport swingPropertyChangeSupport = this.children.get(s);
            if (swingPropertyChangeSupport != null) {
                return swingPropertyChangeSupport.listeners.isEmpty() ^ true;
            }
        }
        return false;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Object object;
        while ((object = objectInputStream.readObject()) != null) {
            this.addPropertyChangeListener((PropertyChangeListener)object);
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.listeners == null) {
            return;
        }
        this.listeners.removeElement(propertyChangeListener);
    }
    
    public synchronized void removePropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (this.children == null) {
            return;
        }
        final SwingPropertyChangeSupport swingPropertyChangeSupport = this.children.get(s);
        if (swingPropertyChangeSupport == null) {
            return;
        }
        swingPropertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Vector<PropertyChangeListener> vector = null;
        synchronized (this) {
            if (this.listeners != null) {
                vector = (Vector<PropertyChangeListener>)this.listeners.clone();
            }
        }
        if (vector != null) {
            for (int size = vector.size(), i = 0; i < size; ++i) {
                final PropertyChangeListener propertyChangeListener = vector.elementAt(i);
                if (propertyChangeListener instanceof Serializable) {
                    objectOutputStream.writeObject(propertyChangeListener);
                }
            }
        }
        objectOutputStream.writeObject(null);
    }
}
