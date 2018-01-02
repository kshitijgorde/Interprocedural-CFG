// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class EndOfRecordRequest implements PluginMessage
{
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof EndOfRecordListener) {
            ((EndOfRecordListener)pl).EndOfRecord();
        }
        return null;
    }
}
