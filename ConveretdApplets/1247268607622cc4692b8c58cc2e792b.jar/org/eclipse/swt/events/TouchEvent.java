// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Touch;

public class TouchEvent extends TypedEvent
{
    public Touch[] touches;
    public int stateMask;
    public int x;
    public int y;
    static final long serialVersionUID = -8348741538373572182L;
    
    public TouchEvent(final Event event) {
        super(event);
        this.touches = event.touches;
        this.stateMask = event.stateMask;
        this.x = event.x;
        this.y = event.y;
    }
    
    public String toString() {
        final String string = super.toString();
        String s = String.valueOf(string.substring(0, string.length() - 1)) + " stateMask=" + this.stateMask + " x=" + this.x + " y=" + this.y;
        if (this.touches != null) {
            for (int i = 0; i < this.touches.length; ++i) {
                s = String.valueOf(s) + "\n     " + this.touches[i].toString();
            }
            s = String.valueOf(s) + "\n";
        }
        return String.valueOf(s) + "}";
    }
}
