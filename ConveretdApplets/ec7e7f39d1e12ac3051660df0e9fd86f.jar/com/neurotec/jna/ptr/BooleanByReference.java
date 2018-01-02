// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna.ptr;

import com.sun.jna.ptr.ByReference;

public final class BooleanByReference extends ByReference
{
    public BooleanByReference() {
        this(false);
    }
    
    public BooleanByReference(final boolean value) {
        super(4);
        this.setValue(value);
    }
    
    public void setValue(final boolean value) {
        this.getPointer().setInt(0L, value ? 1 : 0);
    }
    
    public boolean getValue() {
        return this.getPointer().getInt(0L) != 0;
    }
}
