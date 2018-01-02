import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableString extends Drawable
{
    private static final int HEIGHT = 15;
    private static final int CHARACTER_WIDTH = 7;
    private static final int DELTA = 10;
    private String string;
    
    public DrawableString(final Position position) {
        this.reposition(position);
        super.color = Color.black;
    }
    
    public void draw(final Graphics g) {
        g.setColor(super.color);
        g.setFont(Screen.screen.getFont());
        if (this.string != null) {
            g.drawString(this.string, super.x, super.y);
        }
    }
    
    public boolean inside(final Position position) {
        return position.x() > super.x - 10 && position.x() < super.x + this.string.length() * 7 + 10 && position.y() > super.y - 15 - 10 && position.y() < super.y + 10;
    }
    
    public void setString(final String string) {
        this.string = string;
    }
}
