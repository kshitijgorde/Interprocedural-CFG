import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Sprite2D extends Sprite
{
    protected int locx;
    protected int locy;
    Color color;
    boolean fill;
    
    public boolean getFill() {
        return this.fill;
    }
    
    public void setFill(final boolean fill) {
        this.fill = fill;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
}
