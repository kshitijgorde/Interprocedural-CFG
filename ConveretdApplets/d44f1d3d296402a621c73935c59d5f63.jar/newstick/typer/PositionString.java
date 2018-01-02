// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

class PositionString
{
    private Rectangle area;
    private boolean inArea;
    public int countTyped;
    private Point startPoint;
    private Dimension dimension;
    
    public boolean inArea() {
        return this.inArea;
    }
    
    public void setStartPoint(final Point startPoint) {
        this.startPoint = startPoint;
        this.checkInArea();
    }
    
    public Point getStartPoint() {
        return this.startPoint;
    }
    
    public PositionString(final Rectangle area) {
        this.area = area;
        this.inArea = true;
        this.countTyped = 0;
    }
    
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
        this.checkInArea();
    }
    
    private void checkInArea() {
        this.inArea = false;
        if (this.startPoint != null && this.dimension != null) {
            this.inArea = this.area.intersects(new Rectangle(this.startPoint, this.dimension));
        }
        else if (this.startPoint != null) {
            this.inArea = this.area.contains(this.startPoint);
        }
        if (this.startPoint != null && this.startPoint.y < this.area.y && !this.inArea) {
            this.countTyped = 0;
        }
    }
    
    public Dimension getDimension() {
        return this.dimension;
    }
    
    public void changeSize(final int n, final int n2) {
        if (this.dimension == null) {
            throw new NullPointerException("Field dimension not initialized.");
        }
        final Dimension dimension = this.dimension;
        dimension.width += n;
        final Dimension dimension2 = this.dimension;
        dimension2.height += n2;
        this.checkInArea();
    }
    
    public void changeLocation(final int n, final int n2) {
        if (this.startPoint == null) {
            throw new NullPointerException("Field startPoint not initialized.");
        }
        final Point startPoint = this.startPoint;
        startPoint.x += n;
        final Point startPoint2 = this.startPoint;
        startPoint2.y += n2;
        this.checkInArea();
    }
}
