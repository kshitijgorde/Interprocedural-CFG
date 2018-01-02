// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.Comparator;
import java.util.Collections;
import org.jfree.util.SortOrder;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class DefaultKeyedValues implements KeyedValues, Cloneable, Serializable
{
    private List data;
    
    public DefaultKeyedValues() {
        this.data = new ArrayList();
    }
    
    public int getItemCount() {
        return this.data.size();
    }
    
    public Number getValue(final int item) {
        Number result = null;
        final KeyedValue kval = this.data.get(item);
        if (kval != null) {
            result = kval.getValue();
        }
        return result;
    }
    
    public Comparable getKey(final int index) {
        Comparable result = null;
        final KeyedValue item = this.data.get(index);
        if (item != null) {
            result = item.getKey();
        }
        return result;
    }
    
    public int getIndex(final Comparable key) {
        int i = 0;
        for (final KeyedValue kv : this.data) {
            if (kv.getKey().equals(key)) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public List getKeys() {
        final List result = new ArrayList();
        for (final KeyedValue kv : this.data) {
            result.add(kv.getKey());
        }
        return result;
    }
    
    public Number getValue(final Comparable key) {
        Number result = null;
        final int index = this.getIndex(key);
        if (index >= 0) {
            result = this.getValue(index);
        }
        return result;
    }
    
    public void addValue(final Comparable key, final Number value) {
        this.setValue(key, value);
    }
    
    public void setValue(final Comparable key, final Number value) {
        final int keyIndex = this.getIndex(key);
        if (keyIndex >= 0) {
            final DefaultKeyedValue kv = this.data.get(keyIndex);
            kv.setValue(value);
        }
        else {
            final KeyedValue kv2 = new DefaultKeyedValue(key, value);
            this.data.add(kv2);
        }
    }
    
    public void removeValue(final int index) {
        this.data.remove(index);
    }
    
    public void removeValue(final Comparable key) {
        final int index = this.getIndex(key);
        if (index >= 0) {
            this.removeValue(index);
        }
    }
    
    public void sortByKeys(final SortOrder order) {
        final Comparator comparator = new KeyedValueComparator(KeyedValueComparatorType.BY_KEY, order);
        Collections.sort((List<Object>)this.data, comparator);
    }
    
    public void sortByValues(final SortOrder order) {
        final Comparator comparator = new KeyedValueComparator(KeyedValueComparatorType.BY_VALUE, order);
        Collections.sort((List<Object>)this.data, comparator);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof KeyedValues)) {
            return false;
        }
        final KeyedValues kvs = (KeyedValues)o;
        final int count = this.getItemCount();
        if (count != kvs.getItemCount()) {
            return false;
        }
        for (int i = 0; i < count; ++i) {
            final Comparable k1 = this.getKey(i);
            final Comparable k2 = kvs.getKey(i);
            if (!k1.equals(k2)) {
                return false;
            }
            final Number v1 = this.getValue(i);
            final Number v2 = kvs.getValue(i);
            if (v1 == null) {
                if (v2 != null) {
                    return false;
                }
            }
            else if (!v1.equals(v2)) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        return (this.data != null) ? this.data.hashCode() : 0;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final DefaultKeyedValues clone = (DefaultKeyedValues)super.clone();
        clone.data = new ArrayList();
        for (final DefaultKeyedValue kv : this.data) {
            clone.data.add(kv.clone());
        }
        return clone;
    }
}
