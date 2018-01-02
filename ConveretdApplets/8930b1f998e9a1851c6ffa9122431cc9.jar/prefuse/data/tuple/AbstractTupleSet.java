// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import java.beans.PropertyChangeListener;
import prefuse.data.expression.Expression;
import prefuse.data.Schema;
import prefuse.data.Tuple;
import prefuse.data.Table;
import prefuse.data.event.TupleSetListener;
import prefuse.data.util.SortedTupleIterator;
import prefuse.data.util.Sort;
import prefuse.data.util.FilterIteratorFactory;
import java.util.Iterator;
import prefuse.data.expression.Predicate;
import javax.swing.event.SwingPropertyChangeSupport;
import java.util.HashMap;
import prefuse.util.collections.CopyOnWriteArrayList;

public abstract class AbstractTupleSet implements TupleSet
{
    private CopyOnWriteArrayList m_tupleListeners;
    private HashMap m_props;
    private SwingPropertyChangeSupport m_propSupport;
    
    public Iterator tuples(final Predicate predicate) {
        if (predicate == null) {
            return this.tuples();
        }
        return FilterIteratorFactory.tuples(this, predicate);
    }
    
    public Iterator tuples(final Predicate predicate, final Sort sort) {
        if (sort == null) {
            return this.tuples(predicate);
        }
        return new SortedTupleIterator(this.tuples(predicate), this.getTupleCount(), sort.getComparator(this));
    }
    
    public void addTupleSetListener(final TupleSetListener tupleSetListener) {
        if (this.m_tupleListeners == null) {
            this.m_tupleListeners = new CopyOnWriteArrayList();
        }
        if (!this.m_tupleListeners.contains(tupleSetListener)) {
            this.m_tupleListeners.add(tupleSetListener);
        }
    }
    
    public void removeTupleSetListener(final TupleSetListener tupleSetListener) {
        if (this.m_tupleListeners != null) {
            this.m_tupleListeners.remove(tupleSetListener);
        }
    }
    
    protected void fireTupleEvent(final Table table, final int n, final int n2, final int n3) {
        if (this.m_tupleListeners != null && this.m_tupleListeners.size() > 0) {
            final Object[] array = this.m_tupleListeners.getArray();
            final Tuple[] array2 = new Tuple[n2 - n + 1];
            for (int n4 = 0, i = n; i <= n2; ++i, ++n4) {
                array2[n4] = table.getTuple(i);
            }
            for (int j = 0; j < array.length; ++j) {
                final TupleSetListener tupleSetListener = (TupleSetListener)array[j];
                if (n3 == 1) {
                    tupleSetListener.tupleSetChanged(this, array2, AbstractTupleSet.EMPTY_ARRAY);
                }
                else {
                    tupleSetListener.tupleSetChanged(this, AbstractTupleSet.EMPTY_ARRAY, array2);
                }
            }
        }
    }
    
    protected void fireTupleEvent(final Tuple tuple, final int n) {
        if (this.m_tupleListeners != null && this.m_tupleListeners.size() > 0) {
            final Object[] array = this.m_tupleListeners.getArray();
            final Tuple[] array2 = { tuple };
            for (int i = 0; i < array.length; ++i) {
                final TupleSetListener tupleSetListener = (TupleSetListener)array[i];
                if (n == 1) {
                    tupleSetListener.tupleSetChanged(this, array2, AbstractTupleSet.EMPTY_ARRAY);
                }
                else {
                    tupleSetListener.tupleSetChanged(this, AbstractTupleSet.EMPTY_ARRAY, array2);
                }
            }
        }
    }
    
    protected void fireTupleEvent(Tuple[] array, Tuple[] array2) {
        if (this.m_tupleListeners != null && this.m_tupleListeners.size() > 0) {
            final Object[] array3 = this.m_tupleListeners.getArray();
            array = ((array == null) ? AbstractTupleSet.EMPTY_ARRAY : array);
            array2 = ((array2 == null) ? AbstractTupleSet.EMPTY_ARRAY : array2);
            for (int i = 0; i < array3.length; ++i) {
                ((TupleSetListener)array3[i]).tupleSetChanged(this, array, array2);
            }
        }
    }
    
    public boolean isAddColumnSupported() {
        return false;
    }
    
    public void addColumns(final Schema schema) {
        if (this.isAddColumnSupported()) {
            for (int i = 0; i < schema.getColumnCount(); ++i) {
                try {
                    this.addColumn(schema.getColumnName(i), schema.getColumnType(i), schema.getDefault(i));
                }
                catch (IllegalArgumentException ex) {}
            }
            return;
        }
        throw new UnsupportedOperationException();
    }
    
    public void addColumn(final String s, final Class clazz, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public void addColumn(final String s, final Class clazz) {
        throw new UnsupportedOperationException();
    }
    
    public void addColumn(final String s, final Expression expression) {
        throw new UnsupportedOperationException();
    }
    
    public void addColumn(final String s, final String s2) {
        throw new UnsupportedOperationException();
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.m_propSupport == null) {
            this.m_propSupport = new SwingPropertyChangeSupport(this);
        }
        this.m_propSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void addPropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.m_propSupport == null) {
            this.m_propSupport = new SwingPropertyChangeSupport(this);
        }
        this.m_propSupport.addPropertyChangeListener(s, propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.m_propSupport == null) {
            return;
        }
        this.m_propSupport.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.m_propSupport == null) {
            return;
        }
        this.m_propSupport.removePropertyChangeListener(s, propertyChangeListener);
    }
    
    public void putClientProperty(final String s, final Object o) {
        if (this.m_props == null && o == null) {
            return;
        }
        Object o2;
        if (o == null) {
            o2 = this.m_props.remove(s);
        }
        else {
            if (this.m_props == null) {
                this.m_props = new HashMap(2);
            }
            o2 = this.m_props.put(s, o);
        }
        if (this.m_propSupport != null) {
            this.m_propSupport.firePropertyChange(s, o2, o);
        }
    }
    
    public Object getClientProperty(final String s) {
        return (this.m_props == null) ? null : this.m_props.get(s);
    }
}
