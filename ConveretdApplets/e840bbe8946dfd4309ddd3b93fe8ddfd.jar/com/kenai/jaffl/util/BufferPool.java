// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.util;

import java.util.List;
import java.nio.ByteBuffer;

public interface BufferPool
{
    ByteBuffer get(final int p0);
    
    void put(final ByteBuffer p0);
    
    void putAll(final List<ByteBuffer> p0);
}
