// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;
import java.nio.ByteBuffer;
import com.kenai.jaffl.provider.AbstractBufferMemoryIO;

public class ByteBufferMemoryIO extends AbstractBufferMemoryIO
{
    public ByteBufferMemoryIO(final ByteBuffer buffer) {
        super(buffer);
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
