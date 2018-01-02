// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;

public interface OnlineStatusListener extends PluginListener
{
    void online();
    
    void offline();
}
