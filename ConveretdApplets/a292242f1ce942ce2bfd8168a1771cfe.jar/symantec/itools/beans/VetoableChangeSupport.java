// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.beans;

import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import java.util.Vector;
import java.beans.VetoableChangeListener;
import java.util.Hashtable;
import java.io.Serializable;

public class VetoableChangeSupport extends java.beans.VetoableChangeSupport implements Serializable
{
    protected Hashtable listenerTable;
    private Object source;
    private int vetoableChangeSupportSerializedDataVersion;
    
    public VetoableChangeSupport(final Object sourceBean) {
        super(sourceBean);
        this.vetoableChangeSupportSerializedDataVersion = 1;
        this.source = sourceBean;
    }
    
    public synchronized void addVetoableChangeListener(final String propertyName, final VetoableChangeListener listener) {
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
    
    public synchronized void removeVetoableChangeListener(final String propertyName, final VetoableChangeListener listener) {
        if (this.listenerTable == null || !this.listenerTable.containsKey(propertyName)) {
            return;
        }
        final Vector listenerList = this.listenerTable.get(propertyName);
        listenerList.removeElement(listener);
    }
    
    public void fireVetoableChange(final String propertyName, final Object oldValue, final Object newValue) throws PropertyVetoException {
        if (oldValue != null && oldValue.equals(newValue)) {
            return;
        }
        super.fireVetoableChange(propertyName, oldValue, newValue);
        Hashtable templistenerTable = null;
        synchronized (this) {
            if (this.listenerTable == null || !this.listenerTable.containsKey(propertyName)) {
                // monitorexit(this)
                return;
            }
            templistenerTable = (Hashtable)this.listenerTable.clone();
        }
        final Vector listenerList = templistenerTable.get(propertyName);
        PropertyChangeEvent evt = new PropertyChangeEvent(this.source, propertyName, oldValue, newValue);
        try {
            for (int i = 0; i < listenerList.size(); ++i) {
                final VetoableChangeListener target = listenerList.elementAt(i);
                target.vetoableChange(evt);
            }
        }
        catch (PropertyVetoException ex) {
            evt = new PropertyChangeEvent(this.source, propertyName, newValue, oldValue);
            for (int j = 0; j < listenerList.size(); ++j) {
                try {
                    final VetoableChangeListener target2 = listenerList.elementAt(j);
                    target2.vetoableChange(evt);
                }
                catch (PropertyVetoException ex2) {}
            }
            throw ex;
        }
    }
}
