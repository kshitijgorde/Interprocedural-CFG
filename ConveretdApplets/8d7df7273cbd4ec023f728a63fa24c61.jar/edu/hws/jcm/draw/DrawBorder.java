// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import java.awt.Color;

public class DrawBorder extends Drawable
{
    protected Color color;
    protected int width;
    
    public DrawBorder() {
        this(Color.black, 1);
    }
    
    public DrawBorder(final Color color, final int n) {
        this.color = ((color == null) ? Color.black : color);
        this.width = ((n >= 0) ? n : 1);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        if (color != null && !color.equals(this.color)) {
            this.color = color;
            this.needsRedraw();
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        if (width >= 0 && this.width != this.width) {
            this.width = width;
        }
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (super.coords == null || this.width == 0) {
            return;
        }
        graphics.setColor(this.color);
        for (int i = 0; i < this.width; ++i) {
            graphics.drawRect(super.coords.getLeft() + i, super.coords.getTop() + i, super.coords.getWidth() - 2 * i - 1, super.coords.getHeight() - 2 * i - 1);
        }
    }
}
