// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx;

public interface ZxIoHandler
{
    int readIO(final int p0, final int p1);
    
    void readOpcode1(final int p0);
    
    int readOpcode2(final int p0, final int p1);
    
    void writeIO(final int p0, final int p1);
}
