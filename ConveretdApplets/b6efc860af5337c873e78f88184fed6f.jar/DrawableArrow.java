import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableArrow extends Drawable
{
    private static int ARROW;
    private double theta;
    private boolean flip;
    
    static {
        DrawableArrow.ARROW = 12;
    }
    
    public void draw(final Graphics g) {
        g.setColor(super.color);
        this.drawArrow(g, this.theta + 0.5235987755982988);
        this.drawArrow(g, this.theta - 0.5235987755982988);
    }
    
    private void drawArrow(final Graphics g, final double phi) {
        final int deltaX = (int)Math.round(Math.sin(phi) * DrawableArrow.ARROW);
        final int deltaY = (int)Math.round(Math.cos(phi) * DrawableArrow.ARROW);
        for (int xI = super.x - 1; xI <= super.x + 1; ++xI) {
            if (this.flip) {
                g.drawLine(xI, super.y, xI + deltaX, super.y - deltaY);
            }
            else {
                g.drawLine(xI, super.y, xI - deltaX, super.y + deltaY);
            }
        }
    }
    
    public void setDirection(final Position to, final boolean forward) {
        double deltaX = to.x() - super.x;
        double deltaY = to.y() - super.y;
        this.flip = (super.x > to.x());
        if (!forward) {
            deltaX = -deltaX;
            deltaY = -deltaY;
            this.flip = (to.x() > super.x);
        }
        if (deltaX != 0.0) {
            this.theta = Math.atan(deltaY / deltaX);
        }
        else if (deltaY > 0.0) {
            this.theta = 1.5707963267948966;
        }
        else {
            this.theta = -1.5707963267948966;
        }
        this.theta += 1.5707963267948966;
    }
}
