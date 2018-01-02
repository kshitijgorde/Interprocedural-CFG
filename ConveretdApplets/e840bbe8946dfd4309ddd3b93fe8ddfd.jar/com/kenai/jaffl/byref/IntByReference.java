// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public final class IntByReference extends AbstractPrimitiveReference<Integer>
{
    public IntByReference(final Integer value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        buffer.putInt(0, (int)this.value);
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)buffer.getInt(0);
    }
    
    public int nativeSize() {
        return 4;
    }
    
    public Class nativeType() {
        return Integer.class;
    }
}
