// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

final class JFFIPointer extends DirectMemoryIO
{
    JFFIPointer(final long address) {
        super(address);
    }
    
    public int intValue() {
        return (int)this.address;
    }
    
    public long longValue() {
        return this.address;
    }
    
    public float floatValue() {
        return this.address;
    }
    
    public double doubleValue() {
        return this.address;
    }
    
    public static final JFFIPointer valueOf(final long address) {
        return new JFFIPointer(address);
    }
    
    public static final JFFIPointer valueOf(final int address) {
        return new JFFIPointer(address & 0xFFFFFFFFL);
    }
}
