// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.io.InputStream;

public interface ImIstream
{
    void close();
    
    int I();
    
    boolean I(final InputStream p0);
    
    int read();
    
    int read(final byte[] p0, final int p1, final int p2);
    
    void I(final int p0);
    
    void Z(final int p0);
}
