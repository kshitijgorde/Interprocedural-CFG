// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.io.IOException;

public interface FilterPlugin
{
    void setFilterSource(final FilterPlugin p0) throws IllegalArgumentException;
    
    FilterPlugin getFilterSource();
    
    int read(final byte[] p0) throws IOException;
    
    void write(final byte[] p0) throws IOException;
}
