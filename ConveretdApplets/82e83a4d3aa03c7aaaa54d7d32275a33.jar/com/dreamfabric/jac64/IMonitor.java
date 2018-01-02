// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public interface IMonitor
{
    void init(final MOS6510Core p0);
    
    void setEnabled(final boolean p0);
    
    boolean isEnabled();
    
    void info(final Object p0);
    
    void warning(final Object p0);
    
    void error(final Object p0);
    
    int getLevel();
    
    void setLevel(final int p0);
    
    void disAssemble(final int[] p0, final int p1, final int p2, final int p3, final int p4, final byte p5, final int p6, final int p7);
}
