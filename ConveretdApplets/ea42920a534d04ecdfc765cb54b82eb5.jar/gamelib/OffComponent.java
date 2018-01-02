// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.Graphics;
import java.awt.Rectangle;

public class OffComponent
{
    protected ActionBuffer buffer;
    protected Rectangle bounds;
    private boolean visible;
    private boolean active;
    
    protected OffComponent(final ActionBuffer buffer) {
        this.bounds = new Rectangle(-10000, -10000, 0, 0);
        this.buffer = buffer;
    }
    
    protected OffComponent(final ActionBuffer buffer, final Rectangle bounds) {
        this.bounds = bounds;
        this.buffer = buffer;
    }
    
    public final Rectangle getBounds() {
        return this.bounds;
    }
    
    protected void go() {
    }
    
    protected void paint(final Graphics graphics) {
    }
    
    public void removeSelf() {
        this.setActive(false);
        this.setVisible(false);
    }
    
    public final void repaint() {
        synchronized (this.buffer) {
            this.buffer.rects.push(this.bounds);
        }
        // monitorexit(this.buffer)
    }
    
    protected final void setActive(final boolean active) {
        if (active && !this.active) {
            this.buffer.actors.addElement(this);
        }
        else if (!active && this.active) {
            this.buffer.actors.removeElement(this);
        }
        this.active = active;
    }
    
    protected final void setPosition(final int x, final int y) {
        synchronized (this.buffer) {
            this.buffer.rects.push(this.bounds);
            this.bounds.x = x;
            this.bounds.y = y;
            this.buffer.rects.push(this.bounds);
        }
        // monitorexit(this.buffer)
    }
    
    protected final void setSize(final int width, final int height) {
        synchronized (this.buffer) {
            this.buffer.rects.push(this.bounds);
            this.bounds.width = width;
            this.bounds.height = height;
            this.buffer.rects.push(this.bounds);
        }
        // monitorexit(this.buffer)
    }
    
    protected final void setVisible(final boolean visible) {
        synchronized (this.buffer) {
            if (visible && !this.visible) {
                this.buffer.childs.addElement(this);
            }
            else if (!visible && this.visible) {
                this.buffer.childs.removeElement(this);
            }
            this.visible = visible;
            this.buffer.rects.push(this.bounds);
        }
        // monitorexit(this.buffer)
    }
}
