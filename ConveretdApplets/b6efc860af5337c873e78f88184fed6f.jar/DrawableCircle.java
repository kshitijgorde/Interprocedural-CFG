import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableCircle extends Drawable
{
    private static final int THICKNESS = 3;
    private int diameter;
    private int clickingDiameter;
    private boolean filled;
    
    public DrawableCircle(final Position position, final int diameter, final boolean filled, final boolean widerClicking) {
        this.reposition(position);
        this.diameter = diameter;
        if (widerClicking) {
            this.clickingDiameter = diameter * 3;
        }
        else {
            this.clickingDiameter = diameter;
        }
        this.filled = filled;
    }
    
    public void draw(final Graphics g) {
        final int radius = this.diameter / 2;
        g.setColor(super.color);
        if (this.filled) {
            g.fillOval(super.x - radius, super.y - radius, this.diameter, this.diameter);
        }
        else {
            for (int i = 0, j = 0; i < 3; ++i, j += 2) {
                g.drawOval(super.x - radius - i, super.y - radius - i, this.diameter + j, this.diameter + j);
            }
        }
    }
    
    public boolean inside(final Position position) {
        final int radius = this.clickingDiameter / 2;
        return position.x() > super.x - radius && position.x() < super.x + radius && position.y() > super.y - radius && position.y() < super.y + radius;
    }
}
