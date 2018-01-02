// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.graphics.GC;

public final class PaintEvent extends TypedEvent
{
    public GC gc;
    public int x;
    public int y;
    public int width;
    public int height;
    public int count;
    static final long serialVersionUID = 3256446919205992497L;
    
    public PaintEvent(final Event event) {
        super(event);
        this.gc = event.gc;
        this.x = event.x;
        this.y = event.y;
        this.width = event.width;
        this.height = event.height;
        this.count = event.count;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " gc=" + this.gc + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height + " count=" + this.count + "}";
    }
}
