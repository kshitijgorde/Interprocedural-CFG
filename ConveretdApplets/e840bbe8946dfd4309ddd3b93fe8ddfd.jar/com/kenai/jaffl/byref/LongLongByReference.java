// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public class LongLongByReference extends AbstractPrimitiveReference<Long>
{
    public LongLongByReference(final Long value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        buffer.putLong(0, (long)this.value);
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)buffer.getLong(0);
    }
    
    public final int nativeSize() {
        return 8;
    }
}
