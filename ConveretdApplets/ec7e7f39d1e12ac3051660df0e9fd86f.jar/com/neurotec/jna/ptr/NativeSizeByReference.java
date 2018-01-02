// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna.ptr;

import com.sun.jna.ptr.ByReference;

public final class NativeSizeByReference extends ByReference
{
    public NativeSizeByReference() {
        this(new NativeSize(0L));
    }
    
    public NativeSizeByReference(final NativeSize value) {
        super(NativeSize.SIZE);
        this.setValue(value);
    }
    
    public void setValue(final NativeSize value) {
        if (NativeSize.SIZE == 4) {
            this.getPointer().setInt(0L, value.intValue());
        }
        else {
            if (NativeSize.SIZE != 8) {
                throw new RuntimeException("GCCLong has to be either 4 or 8 bytes.");
            }
            this.getPointer().setLong(0L, value.longValue());
        }
    }
    
    public NativeSize getValue() {
        if (NativeSize.SIZE == 4) {
            return new NativeSize((long)this.getPointer().getInt(0L));
        }
        if (NativeSize.SIZE == 8) {
            return new NativeSize(this.getPointer().getLong(0L));
        }
        throw new RuntimeException("GCCLong has to be either 4 or 8 bytes.");
    }
}
