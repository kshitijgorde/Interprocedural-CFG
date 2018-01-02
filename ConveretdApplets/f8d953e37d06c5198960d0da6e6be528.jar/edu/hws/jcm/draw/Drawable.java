// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Drawable implements Serializable
{
    protected CoordinateRect coords;
    protected DisplayCanvas canvas;
    private boolean visible;
    
    public Drawable() {
        this.visible = true;
    }
    
    public abstract void draw(final Graphics p0, final boolean p1);
    
    public boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        if (visible != this.visible) {
            this.visible = visible;
            this.needsRedraw();
        }
    }
    
    public void needsRedraw() {
        if (this.canvas != null) {
            this.canvas.doRedraw(this.coords);
        }
    }
    
    protected void setOwnerData(final DisplayCanvas canvas, final CoordinateRect coords) {
        this.canvas = canvas;
        this.coords = coords;
    }
}
