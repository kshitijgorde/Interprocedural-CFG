// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.io.Serializable;

public class DefaultValueDataset extends AbstractDataset implements ValueDataset, Cloneable, Serializable
{
    private Number value;
    
    public DefaultValueDataset() {
        this(null);
    }
    
    public DefaultValueDataset(final double value) {
        this(new Double(value));
    }
    
    public DefaultValueDataset(final Number value) {
        this.value = value;
    }
    
    public Number getValue() {
        return this.value;
    }
    
    public void setValue(final Number value) {
        this.value = value;
        this.notifyListeners(new DatasetChangeEvent(this, this));
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof ValueDataset) {
            final ValueDataset vd = (ValueDataset)obj;
            return ObjectUtils.equal(this.value, vd.getValue());
        }
        return false;
    }
    
    public int hashCode() {
        return (this.value != null) ? this.value.hashCode() : 0;
    }
}
