// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public class DoubleByReference extends AbstractPrimitiveReference<Double>
{
    public DoubleByReference(final Double value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        buffer.putDouble(0, (double)this.value);
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)buffer.getDouble(0);
    }
    
    public final int nativeSize() {
        return 8;
    }
}
