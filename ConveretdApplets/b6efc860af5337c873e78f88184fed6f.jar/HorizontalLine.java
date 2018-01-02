import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class HorizontalLine extends Drawable
{
    private int left;
    private int right;
    private int y;
    
    public HorizontalLine(final int left, final int right, final int y) {
        this.left = left;
        this.right = right;
        this.y = y;
    }
    
    public void draw(final Graphics g) {
        g.setColor(Color.black);
        g.drawLine(this.left, this.y, this.right, this.y);
    }
}
