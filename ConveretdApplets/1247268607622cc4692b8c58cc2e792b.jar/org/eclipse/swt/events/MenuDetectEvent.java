// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;

public final class MenuDetectEvent extends TypedEvent
{
    public int x;
    public int y;
    public boolean doit;
    private static final long serialVersionUID = -3061660596590828941L;
    
    public MenuDetectEvent(final Event event) {
        super(event);
        this.x = event.x;
        this.y = event.y;
        this.doit = event.doit;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " x=" + this.x + " y=" + this.y + " doit=" + this.doit + "}";
    }
}
