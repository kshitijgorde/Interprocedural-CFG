// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class DefaultPieDataset extends AbstractDataset implements PieDataset, Cloneable, Serializable
{
    private DefaultKeyedValues data;
    
    public DefaultPieDataset() {
        this.data = new DefaultKeyedValues();
    }
    
    public DefaultPieDataset(final KeyedValues data) {
        this.data = new DefaultKeyedValues();
        for (int i = 0; i < data.getItemCount(); ++i) {
            this.data.addValue(data.getKey(i), data.getValue(i));
        }
    }
    
    public int getItemCount() {
        return this.data.getItemCount();
    }
    
    public List getKeys() {
        return Collections.unmodifiableList((List<?>)this.data.getKeys());
    }
    
    public Comparable getKey(final int item) {
        Comparable result = null;
        if (this.getItemCount() > item) {
            result = this.data.getKey(item);
        }
        return result;
    }
    
    public int getIndex(final Comparable key) {
        return this.data.getIndex(key);
    }
    
    public Number getValue(final int item) {
        Number result = null;
        if (this.getItemCount() > item) {
            result = this.data.getValue(item);
        }
        return result;
    }
    
    public Number getValue(final Comparable key) {
        if (key == null) {
            throw new IllegalArgumentException("PieDataset: null key not allowed.");
        }
        return this.data.getValue(key);
    }
    
    public void setValue(final Comparable key, final Number value) {
        this.data.setValue(key, value);
        this.fireDatasetChanged();
    }
    
    public void setValue(final Comparable key, final double value) {
        this.setValue(key, new Double(value));
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof PieDataset)) {
            return false;
        }
        final PieDataset pd = (PieDataset)o;
        final int count = this.getItemCount();
        if (pd.getItemCount() != count) {
            return false;
        }
        for (int i = 0; i < count; ++i) {
            final Comparable k1 = this.getKey(i);
            final Comparable k2 = pd.getKey(i);
            if (!k1.equals(k2)) {
                return false;
            }
            final Number v1 = this.getValue(i);
            final Number v2 = pd.getValue(i);
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
        return this.data.hashCode();
    }
    
    public Object clone() throws CloneNotSupportedException {
        final DefaultPieDataset clone = (DefaultPieDataset)super.clone();
        clone.data = (DefaultKeyedValues)this.data.clone();
        return clone;
    }
}
