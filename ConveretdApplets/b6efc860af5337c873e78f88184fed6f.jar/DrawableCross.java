import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableCross extends Drawable
{
    private static final int SIZE = 4;
    
    public DrawableCross(final Position position) {
        this.reposition(position);
    }
    
    public void draw(final Graphics g) {
        g.setColor(super.color);
        for (int i = -1; i <= 1; ++i) {
            g.drawLine(super.x - 4 + i, super.y - 4, super.x + 4 + i, super.y + 4);
            g.drawLine(super.x + 4 + i, super.y - 4, super.x - 4 + i, super.y + 4);
        }
    }
}
