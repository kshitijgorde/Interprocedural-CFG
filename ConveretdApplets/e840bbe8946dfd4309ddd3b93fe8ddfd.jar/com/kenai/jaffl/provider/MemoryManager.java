// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;

public interface MemoryManager
{
    MemoryIO allocate(final int p0);
    
    MemoryIO allocateDirect(final int p0);
    
    MemoryIO allocateDirect(final int p0, final boolean p1);
    
    MemoryIO wrap(final Pointer p0);
    
    MemoryIO wrap(final Pointer p0, final int p1);
    
    MemoryIO wrap(final ByteBuffer p0);
    
    Pointer getBufferPointer(final Buffer p0);
}
