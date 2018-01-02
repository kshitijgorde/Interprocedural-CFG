import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableLine extends Drawable
{
    private static final int DELTA = 10;
    private Position other;
    private int thickness;
    private float slope;
    private boolean steepSlope;
    private int leftX;
    private int rightX;
    private int upperY;
    private int lowerY;
    
    public DrawableLine(final Position first, final Position second, final int thickness) {
        this.reposition(first);
        this.other = second;
        this.thickness = thickness;
        if (first.y() < second.y()) {
            this.lowerY = second.y();
            this.upperY = first.y();
        }
        else {
            this.lowerY = first.y();
            this.upperY = second.y();
        }
        if (first.x() < second.x()) {
            this.leftX = first.x();
            this.rightX = second.x();
        }
        else {
            this.leftX = second.x();
            this.rightX = first.x();
        }
        final int deltaX = second.x() - first.x();
        final int deltaY = second.y() - first.y();
        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            this.steepSlope = true;
            this.slope = deltaX / deltaY;
        }
        else {
            this.steepSlope = false;
            this.slope = deltaY / deltaX;
        }
    }
    
    public void draw(final Graphics g) {
        g.setColor(super.color);
        for (int i = 0; i < this.thickness; ++i) {
            g.drawLine(super.x + i, super.y, this.other.x() + i, this.other.y());
        }
    }
    
    public boolean inside(final Position position) {
        if (this.steepSlope) {
            if (position.y() > this.upperY && position.y() < this.lowerY) {
                final int xIntercept = (int)(super.x - (super.y - position.y()) * this.slope);
                if (position.x() > xIntercept - 10 && position.x() < xIntercept + 10) {
                    return true;
                }
            }
        }
        else if (position.x() > this.leftX && position.x() < this.rightX) {
            final int yIntercept = (int)(super.y + (position.x() - super.x) * this.slope);
            if (position.y() < yIntercept + 10 && position.y() > yIntercept - 10) {
                return true;
            }
        }
        return false;
    }
}
