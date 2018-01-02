// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Enumeration;
import java.util.Hashtable;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.event.SwingPropertyChangeSupport;
import java.io.Serializable;

public abstract class AbstractAction implements Action, Cloneable, Serializable
{
    protected boolean enabled;
    private ArrayTable arrayTable;
    protected SwingPropertyChangeSupport changeSupport;
    
    public AbstractAction() {
        this.enabled = true;
        this.arrayTable = new ArrayTable();
    }
    
    public AbstractAction(final String s) {
        this.enabled = true;
        this.arrayTable = new ArrayTable();
        this.putValue("Name", s);
    }
    
    public AbstractAction(final String s, final Icon icon) {
        this(s);
        this.putValue("SmallIcon", icon);
    }
    
    public abstract void actionPerformed(final ActionEvent p0);
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport == null) {
            this.changeSupport = new SwingPropertyChangeSupport(this);
        }
        this.changeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final AbstractAction abstractAction = (AbstractAction)super.clone();
        abstractAction.arrayTable = (ArrayTable)this.arrayTable.clone();
        return abstractAction;
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        if (this.changeSupport == null) {
            return;
        }
        this.changeSupport.firePropertyChange(s, o, o2);
    }
    
    public Object getValue(final String s) {
        return this.arrayTable.get(s);
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public synchronized void putValue(final String s, final Object o) {
        Object value = null;
        if (this.arrayTable.containsKey(s)) {
            value = this.arrayTable.get(s);
        }
        if (o == null) {
            this.arrayTable.remove(s);
        }
        else {
            this.arrayTable.put(s, o);
        }
        this.firePropertyChange(s, value, o);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport == null) {
            return;
        }
        this.changeSupport.removePropertyChangeListener(propertyChangeListener);
    }
    
    public synchronized void setEnabled(final boolean enabled) {
        final boolean enabled2 = this.enabled;
        this.enabled = enabled;
        this.firePropertyChange("enabled", new Boolean(enabled2), new Boolean(enabled));
    }
    
    private class ArrayTable implements Cloneable, Serializable
    {
        private Object table;
        private static final int ARRAY_BOUNDARY = 8;
        
        ArrayTable() {
            this.table = null;
        }
        
        public synchronized Object clone() {
            final ArrayTable arrayTable = new ArrayTable();
            if (this.isArray()) {
                final Object[] array = (Object[])this.table;
                for (int i = 0; i < array.length - 1; i += 2) {
                    arrayTable.put(array[i], array[i + 1]);
                }
            }
            else {
                final Hashtable hashtable = (Hashtable)this.table;
                final Enumeration<Object> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    arrayTable.put(nextElement, hashtable.get(nextElement));
                }
            }
            return arrayTable;
        }
        
        public boolean containsKey(final Object o) {
            boolean containsKey = false;
            if (this.table != null) {
                if (this.isArray()) {
                    final Object[] array = (Object[])this.table;
                    for (int i = 0; i < array.length - 1; i += 2) {
                        if (array[i].equals(o)) {
                            containsKey = true;
                            break;
                        }
                    }
                }
                else {
                    containsKey = ((Hashtable)this.table).containsKey(o);
                }
            }
            return containsKey;
        }
        
        public Object get(final Object o) {
            Object value = null;
            if (this.table != null) {
                if (this.isArray()) {
                    final Object[] array = (Object[])this.table;
                    for (int i = 0; i < array.length - 1; i += 2) {
                        if (array[i].equals(o)) {
                            value = array[i + 1];
                            break;
                        }
                    }
                }
                else {
                    value = ((Hashtable)this.table).get(o);
                }
            }
            return value;
        }
        
        private void grow() {
            final Object[] array = (Object[])this.table;
            final Hashtable table = new Hashtable<Object, Object>(array.length / 2);
            for (int i = 0; i < array.length; i += 2) {
                table.put(array[i], array[i + 1]);
            }
            this.table = table;
        }
        
        private boolean isArray() {
            return this.table instanceof Object[];
        }
        
        public synchronized void put(final Object o, final Object o2) {
            if (this.table == null) {
                this.table = new Object[] { o, o2 };
            }
            else {
                final int size = this.size();
                if (size < 8) {
                    if (this.containsKey(o)) {
                        final Object[] array = (Object[])this.table;
                        for (int i = 0; i < array.length - 1; i += 2) {
                            if (array[i].equals(o)) {
                                array[i + 1] = o2;
                                break;
                            }
                        }
                    }
                    else {
                        final Object[] array2 = (Object[])this.table;
                        final int length = array2.length;
                        final Object[] table = new Object[length + 2];
                        System.arraycopy(array2, 0, table, 0, length);
                        table[length] = o;
                        table[length + 1] = o2;
                        this.table = table;
                    }
                }
                else {
                    if (size == 8 && this.isArray()) {
                        this.grow();
                    }
                    ((Hashtable)this.table).put(o, o2);
                }
            }
        }
        
        public synchronized Object remove(final Object o) {
            Object remove = null;
            if (o == null) {
                return null;
            }
            if (this.table != null) {
                if (this.isArray()) {
                    int n = -1;
                    final Object[] array = (Object[])this.table;
                    for (int i = array.length - 2; i >= 0; i -= 2) {
                        if (array[i].equals(o)) {
                            n = i;
                            remove = array[i + 1];
                            break;
                        }
                    }
                    if (n != -1) {
                        final Object[] array2 = new Object[array.length - 2];
                        System.arraycopy(array, 0, array2, 0, n);
                        if (n < array2.length) {
                            System.arraycopy(array, n + 2, array2, n, array2.length - n);
                        }
                        this.table = ((array2.length == 0) ? null : array2);
                    }
                }
                else {
                    remove = ((Hashtable)this.table).remove(o);
                }
                if (this.size() == 7 && !this.isArray()) {
                    this.shrink();
                }
            }
            return remove;
        }
        
        private void shrink() {
            final Hashtable hashtable = (Hashtable)this.table;
            final Object[] table = new Object[hashtable.size() * 2];
            final Enumeration<Object> keys = hashtable.keys();
            int n = 0;
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                table[n] = nextElement;
                table[n + 1] = hashtable.get(nextElement);
                n += 2;
            }
            this.table = table;
        }
        
        public int size() {
            if (this.table == null) {
                return 0;
            }
            int size;
            if (this.isArray()) {
                size = ((Object[])this.table).length / 2;
            }
            else {
                size = ((Hashtable)this.table).size();
            }
            return size;
        }
    }
}
