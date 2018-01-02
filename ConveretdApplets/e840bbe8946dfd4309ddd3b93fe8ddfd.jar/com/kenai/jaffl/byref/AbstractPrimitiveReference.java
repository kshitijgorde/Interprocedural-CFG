// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

public abstract class AbstractPrimitiveReference<T> implements ByReference<T>
{
    protected T value;
    
    public AbstractPrimitiveReference(final T value) {
        if (value == null) {
            throw new NullPointerException("Reference value cannot be null");
        }
        this.value = value;
    }
    
    public Class nativeType() {
        return this.value.getClass();
    }
    
    public void setValue(final T value) {
        if (value == null) {
            throw new NullPointerException("Reference value cannot be null");
        }
        this.value = value;
    }
    
    public T getValue() {
        return this.value;
    }
}
