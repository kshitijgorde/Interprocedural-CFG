// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Rectangle;

public final class TouchSource
{
    int handle;
    boolean direct;
    Rectangle bounds;
    
    TouchSource(final int handle, final boolean direct, final Rectangle bounds) {
        this.handle = handle;
        this.direct = direct;
        this.bounds = bounds;
    }
    
    public boolean isDirect() {
        return this.direct;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
    }
    
    public String toString() {
        return "TouchSource {handle=" + this.handle + " direct=" + this.direct + " bounds=" + this.bounds + "}";
    }
}
