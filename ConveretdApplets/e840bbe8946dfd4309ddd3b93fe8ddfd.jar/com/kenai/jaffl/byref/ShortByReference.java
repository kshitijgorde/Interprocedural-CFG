// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public class ShortByReference extends AbstractPrimitiveReference<Short>
{
    public ShortByReference(final Short value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        buffer.putShort(0, (short)this.value);
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)buffer.getShort(0);
    }
    
    public final int nativeSize() {
        return 2;
    }
}
