// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.z80;

public interface Z80IoHandler2
{
    int readIO(final int p0);
    
    int readMem(final int p0);
    
    int readOpcode(final int p0);
    
    void writeIO(final int p0, final int p1);
    
    void writeMem(final int p0, final int p1);
}
