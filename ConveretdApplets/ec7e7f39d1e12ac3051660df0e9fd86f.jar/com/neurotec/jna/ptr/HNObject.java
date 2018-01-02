// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna.ptr;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

public class HNObject extends PointerType
{
    public static final HNObject NULL;
    
    public HNObject(final Pointer pointer) {
        super(pointer);
    }
    
    public HNObject() {
    }
    
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return this.getPointer() == null;
        }
        if (!(o instanceof HNObject)) {
            return false;
        }
        final Pointer p = ((HNObject)o).getPointer();
        if (this.getPointer() == null) {
            return p == null;
        }
        return this.getPointer().equals(p);
    }
    
    static {
        NULL = new HNObject();
    }
}
