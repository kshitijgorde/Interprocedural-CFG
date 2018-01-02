import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class QuicksortBar extends Drawable
{
    private static final int WIDTH = 10;
    private int height;
    private int first;
    private int last;
    
    public QuicksortBar(final Position position) {
        super.x = position.x();
        super.y = position.y();
    }
    
    public void draw(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(super.x, super.y, 10, this.height);
        g.drawLine(this.first, super.y, this.last + 10 - 1, super.y);
    }
    
    public void setDimensions(final int height, final int first, final int last) {
        this.height = height;
        this.first = first;
        this.last = last;
    }
}
