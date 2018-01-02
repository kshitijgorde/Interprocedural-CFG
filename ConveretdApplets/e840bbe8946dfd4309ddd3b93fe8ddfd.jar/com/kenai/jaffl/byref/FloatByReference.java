// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import java.nio.ByteBuffer;

public class FloatByReference extends AbstractPrimitiveReference<Float>
{
    public FloatByReference(final Float value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        buffer.putFloat(0, (float)this.value);
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)buffer.getFloat(0);
    }
    
    public final int nativeSize() {
        return 4;
    }
}
