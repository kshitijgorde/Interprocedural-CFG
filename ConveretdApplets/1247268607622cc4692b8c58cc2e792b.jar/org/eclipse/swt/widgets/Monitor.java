// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Rectangle;

public final class Monitor
{
    int handle;
    int x;
    int y;
    int width;
    int height;
    int clientX;
    int clientY;
    int clientWidth;
    int clientHeight;
    
    public boolean equals(final Object o) {
        return o == this || (o instanceof Monitor && this.handle == ((Monitor)o).handle);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public Rectangle getClientArea() {
        return new Rectangle(this.clientX, this.clientY, this.clientWidth, this.clientHeight);
    }
    
    public int hashCode() {
        return this.handle;
    }
}
