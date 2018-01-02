// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

public interface PluginBus
{
    Object broadcast(final PluginMessage p0);
    
    void registerPluginListener(final PluginListener p0);
}
