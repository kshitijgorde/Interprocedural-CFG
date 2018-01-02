// 
// Decompiled by Procyon v0.5.30
// 

package jstella.j6507;

public interface IfcSystem
{
    int peek(final int p0);
    
    void poke(final int p0, final int p1);
    
    void processorCycle(final int p0);
    
    char getResetPC();
}
