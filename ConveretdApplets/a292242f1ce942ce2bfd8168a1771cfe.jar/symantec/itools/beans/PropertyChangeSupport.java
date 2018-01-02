// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.beans;

import java.beans.PropertyChangeEvent;
import java.util.Vector;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.io.Serializable;

public class PropertyChangeSupport extends java.beans.PropertyChangeSupport implements Serializable
{
    protected Hashtable listenerTable;
    private Object source;
    private int propertyChangeSupportSerializedDataVersion;
    
    public PropertyChangeSupport(final Object sourceBean) {
        super(sourceBean);
        this.propertyChangeSupportSerializedDataVersion = 1;
        this.source = sourceBean;
    }
    
    public synchronized void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        if (this.listenerTable == null) {
            this.listenerTable = new Hashtable();
        }
        Vector listenerList;
        if (this.listenerTable.containsKey(propertyName)) {
            listenerList = this.listenerTable.get(propertyName);
        }
        else {
            listenerList = new Vector();
        }
        listenerList.addElement(listener);
        this.listenerTable.put(propertyName, listenerList);
    }
    
    public synchronized void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        if (this.listenerTable == null || !this.listenerTable.containsKey(propertyName)) {
            return;
        }
        final Vector listenerList = this.listenerTable.get(propertyName);
        listenerList.removeElement(listener);
    }
    
    public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
        if (oldValue != null && oldValue.equals(newValue)) {
            return;
        }
        super.firePropertyChange(propertyName, oldValue, newValue);
        Hashtable templistenerTable = null;
        synchronized (this) {
            if (this.listenerTable == null || !this.listenerTable.containsKey(propertyName)) {
                // monitorexit(this)
                return;
            }
            templistenerTable = (Hashtable)this.listenerTable.clone();
        }
        final Vector listenerList = templistenerTable.get(propertyName);
        final PropertyChangeEvent evt = new PropertyChangeEvent(this.source, propertyName, oldValue, newValue);
        for (int i = 0; i < listenerList.size(); ++i) {
            final PropertyChangeListener target = listenerList.elementAt(i);
            target.propertyChange(evt);
        }
    }
}
