// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

public interface MicrodriveCartridge
{
    byte getByte(final int p0);
    
    int getLength();
    
    boolean isWriteProtected();
    
    void setByte(final int p0, final byte p1);
}
