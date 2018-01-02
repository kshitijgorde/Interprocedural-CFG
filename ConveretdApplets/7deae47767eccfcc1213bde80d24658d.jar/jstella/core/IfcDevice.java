// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

public interface IfcDevice
{
    String name();
    
    void reset();
    
    void install(final JSSystem p0);
    
    int peek(final int p0);
    
    void poke(final int p0, final int p1);
    
    void systemCyclesReset();
}
