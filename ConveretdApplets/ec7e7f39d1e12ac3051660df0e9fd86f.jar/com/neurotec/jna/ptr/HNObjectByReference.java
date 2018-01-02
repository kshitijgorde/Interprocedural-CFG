// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna.ptr;

import com.sun.jna.Native;
import com.sun.jna.ptr.ByReference;

public class HNObjectByReference extends ByReference
{
    public HNObjectByReference() {
        this((HNObject)null);
    }
    
    public HNObjectByReference(final HNObject value) {
        super(Native.POINTER_SIZE);
        this.setValue(value);
    }
    
    public final void setValue(final HNObject value) {
        this.getPointer().setPointer(0L, (value == null) ? null : value.getPointer());
    }
    
    public final HNObject getValue() {
        return new HNObject(this.getPointer().getPointer(0L));
    }
}
