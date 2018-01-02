// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.byref;

import com.kenai.jaffl.util.BufferUtil;
import java.nio.ByteBuffer;
import com.kenai.jaffl.Address;

public class AddressByReference extends AbstractPrimitiveReference<Address>
{
    public AddressByReference(final Address value) {
        super(value);
    }
    
    public void marshal(final ByteBuffer buffer) {
        BufferUtil.putAddress(buffer, 0, ((Address)this.value).nativeAddress());
    }
    
    public void unmarshal(final ByteBuffer buffer) {
        this.value = (T)new Address(BufferUtil.getAddress(buffer, 0));
    }
    
    public int nativeSize() {
        return Address.SIZE / 8;
    }
}
