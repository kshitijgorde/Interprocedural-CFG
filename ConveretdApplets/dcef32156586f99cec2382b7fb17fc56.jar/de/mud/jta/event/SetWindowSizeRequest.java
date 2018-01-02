// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class SetWindowSizeRequest implements PluginMessage
{
    private int columns;
    private int rows;
    
    public SetWindowSizeRequest(final int c, final int r) {
        this.rows = r;
        this.columns = c;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof SetWindowSizeListener) {
            ((SetWindowSizeListener)pl).setWindowSize(this.columns, this.rows);
        }
        return null;
    }
}
