// 
// Decompiled by Procyon v0.5.30
// 

package en.gui.components;

import java.util.EventObject;

public class MemoryDotEvent extends EventObject
{
    int slot;
    
    public MemoryDotEvent(final Object o, final int slot) {
        super(o);
        this.slot = slot;
    }
    
    public final int getSlot() {
        return this.slot;
    }
}
