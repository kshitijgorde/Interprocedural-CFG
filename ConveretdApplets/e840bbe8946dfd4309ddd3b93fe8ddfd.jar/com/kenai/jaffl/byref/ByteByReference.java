// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public class ByteByReference extends AbstractPrimitiveReference<Byte>
{
    public ByteByReference(final Byte value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        buffer.put(0, (byte)this.value);
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)buffer.get(0);
    }
    
    public final int nativeSize() {
        return 1;
    }
}
