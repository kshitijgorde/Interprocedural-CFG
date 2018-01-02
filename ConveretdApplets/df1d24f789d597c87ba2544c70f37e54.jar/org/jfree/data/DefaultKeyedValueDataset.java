// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.io.Serializable;

public class DefaultKeyedValueDataset extends AbstractDataset implements KeyedValueDataset, Serializable
{
    private KeyedValue data;
    
    public DefaultKeyedValueDataset() {
        this(null);
    }
    
    public DefaultKeyedValueDataset(final Comparable key, final Number value) {
        this(new DefaultKeyedValue(key, value));
    }
    
    public DefaultKeyedValueDataset(final KeyedValue data) {
        this.data = data;
    }
    
    public Comparable getKey() {
        Comparable result = null;
        if (this.data != null) {
            result = this.data.getKey();
        }
        return result;
    }
    
    public Number getValue() {
        Number result = null;
        if (this.data != null) {
            result = this.data.getValue();
        }
        return result;
    }
    
    public void updateValue(final Number value) {
        if (this.data == null) {
            throw new RuntimeException("updateValue: can't update null.");
        }
        this.setValue(this.data.getKey(), value);
    }
    
    public void setValue(final Comparable key, final Number value) {
        this.data = new DefaultKeyedValue(key, value);
        this.notifyListeners(new DatasetChangeEvent(this, this));
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KeyedValueDataset)) {
            return false;
        }
        final KeyedValueDataset kvd = (KeyedValueDataset)obj;
        if (this.data == null) {
            return kvd.getKey() == null && kvd.getValue() == null;
        }
        return ObjectUtils.equal(this.data.getKey(), kvd.getKey()) && ObjectUtils.equal(this.data.getValue(), kvd.getValue());
    }
    
    public int hashCode() {
        return (this.data != null) ? this.data.hashCode() : 0;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final DefaultKeyedValueDataset clone = (DefaultKeyedValueDataset)super.clone();
        return clone;
    }
}
