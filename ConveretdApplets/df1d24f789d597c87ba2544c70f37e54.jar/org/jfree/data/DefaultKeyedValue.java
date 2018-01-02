// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.io.Serializable;

public class DefaultKeyedValue implements KeyedValue, Cloneable, Serializable
{
    private Comparable key;
    private Number value;
    
    public DefaultKeyedValue(final Comparable key, final Number value) {
        this.key = key;
        this.value = value;
    }
    
    public Comparable getKey() {
        return this.key;
    }
    
    public Number getValue() {
        return this.value;
    }
    
    public synchronized void setValue(final Number value) {
        this.value = value;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultKeyedValue)) {
            return false;
        }
        final DefaultKeyedValue defaultKeyedValue = (DefaultKeyedValue)o;
        Label_0054: {
            if (this.key != null) {
                if (this.key.equals(defaultKeyedValue.key)) {
                    break Label_0054;
                }
            }
            else if (defaultKeyedValue.key == null) {
                break Label_0054;
            }
            return false;
        }
        if (this.value != null) {
            if (this.value.equals(defaultKeyedValue.value)) {
                return true;
            }
        }
        else if (defaultKeyedValue.value == null) {
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        int result = (this.key != null) ? this.key.hashCode() : 0;
        result = 29 * result + ((this.value != null) ? this.value.hashCode() : 0);
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final DefaultKeyedValue clone = (DefaultKeyedValue)super.clone();
        return clone;
    }
}
