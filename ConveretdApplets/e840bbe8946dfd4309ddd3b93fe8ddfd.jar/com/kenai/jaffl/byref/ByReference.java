// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public interface ByReference<T>
{
    int nativeSize();
    
    void marshal(final ByteBuffer p0);
    
    void unmarshal(final ByteBuffer p0);
    
    T getValue();
}
