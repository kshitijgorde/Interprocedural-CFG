// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.sun.jna.Structure;

public class UUIDData extends Structure
{
    public long mostSigBits;
    public long leastSigBits;
    
    public UUIDData() {
    }
    
    public UUIDData(final long mostSigBits, final long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
    }
    
    protected ByReference newByReference() {
        return new ByReference();
    }
    
    protected ByValue newByValue() {
        return new ByValue();
    }
    
    protected UUIDData newInstance() {
        return new UUIDData();
    }
    
    static class ByReference extends UUIDData implements Structure.ByReference
    {
    }
    
    static class ByValue extends UUIDData implements Structure.ByValue
    {
    }
}
