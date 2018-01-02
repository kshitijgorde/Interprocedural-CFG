// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;
import com.kenai.jaffl.provider.AbstractArrayMemoryIO;

public final class ArrayMemoryIO extends AbstractArrayMemoryIO
{
    public ArrayMemoryIO(final int size) {
        super(size);
    }
    
    public MemoryIO getMemoryIO(final long offset) {
        return MemoryUtil.newMemoryIO(this.getAddress(offset));
    }
    
    public MemoryIO getMemoryIO(final long offset, final long size) {
        return MemoryUtil.newMemoryIO(this.getAddress(offset), size);
    }
    
    public Pointer getPointer(final long offset) {
        return MemoryUtil.newPointer(this.getAddress(offset));
    }
    
    public void putPointer(final long offset, final Pointer value) {
        this.putAddress(offset, value.address());
    }
}
