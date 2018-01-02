import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableBar extends Drawable
{
    private static final int WIDTH = 10;
    private static final int CLICK = 5;
    private int height;
    
    public DrawableBar(final Position position) {
        super.x = position.x();
        super.y = position.y();
    }
    
    public void draw(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(super.x, super.y, 10, this.height);
    }
    
    public boolean inside(final Position position) {
        return position.x() > super.x - 5 && position.x() < super.x + 10 + 5 && position.y() > super.y && position.y() < super.y + this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
}
