// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna;

import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.sun.jna.Memory;

public final class NMemory extends Memory
{
    boolean ownsPointer;
    
    public NMemory(final Pointer pointer, final long size) {
        this(pointer, size, true);
    }
    
    public NMemory(final Pointer pointer, final long size, final boolean ownsPointer) {
        this.size = size;
        if (size < 0L) {
            throw new IllegalArgumentException("Allocation size must be greater than zero");
        }
        this.peer = ((pointer == null) ? 0L : Pointer.nativeValue(pointer));
        this.ownsPointer = ownsPointer;
    }
    
    public NMemory(final long size) {
        this.size = size;
        if (size < 0L) {
            throw new IllegalArgumentException("Allocation size must be greater than zero");
        }
        this.peer = Pointer.nativeValue(NCore.alloc(size));
        this.ownsPointer = false;
    }
    
    protected void finalize() {
        if (this.ownsPointer) {
            NCore.free(Pointer.createConstant(this.peer));
        }
        this.peer = 0L;
    }
}
