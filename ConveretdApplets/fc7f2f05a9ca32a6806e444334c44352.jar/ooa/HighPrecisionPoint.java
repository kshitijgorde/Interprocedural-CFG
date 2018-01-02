// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Point;

public class HighPrecisionPoint
{
    public double x;
    public double y;
    protected Point point;
    
    public HighPrecisionPoint(final HighPrecisionPoint pt) {
        this.x = pt.x;
        this.y = pt.y;
        this.point = new Point((int)this.x, (int)this.y);
    }
    
    public HighPrecisionPoint(final Point pt) {
        this.x = pt.x;
        this.y = pt.y;
        this.point = new Point((int)this.x, (int)this.y);
    }
    
    public HighPrecisionPoint(final double newX, final double newY) {
        this.x = newX;
        this.y = newY;
        this.point = new Point((int)this.x, (int)this.y);
    }
    
    public void directionalMove(double direction, final double distance) {
        direction = Util.fixDegrees(direction);
        this.x += distance * Math.sin(Util.toRadians(direction));
        this.y += -distance * Math.cos(Util.toRadians(direction));
        this.point.x = (int)this.x;
        this.point.y = (int)this.y;
    }
    
    public double getAngleBetween(final HighPrecisionPoint pt) {
        final double dx = pt.x - this.x;
        final double dy = pt.y - this.y;
        if (dx == 0 && dy <= 0) {
            return 0.0;
        }
        if (dy == 0 && dx >= 0) {
            return 90.0;
        }
        if (dx == 0 && dy >= 0) {
            return 180.0;
        }
        if (dy == 0 && dx <= 0) {
            return 270.0;
        }
        if (dx >= 0 && dy <= 0) {
            return Util.toDegrees(Math.atan(dx / -dy));
        }
        if (dx >= 0 && dy >= 0) {
            return 90 + Util.toDegrees(Math.atan(dy / dx));
        }
        if (dx <= 0 && dy >= 0) {
            return 180 + Util.toDegrees(Math.atan(-dx / dy));
        }
        if (dx <= 0 && dy <= 0) {
            return 270 + Util.toDegrees(Math.atan(-dy / -dx));
        }
        return 0.0;
    }
    
    public double getDistanceBetween(final HighPrecisionPoint pt) {
        final double dx = pt.x - this.x;
        final double dy = pt.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public void translate(final double dx, final double dy) {
        this.x += dx;
        this.y += dy;
        this.point.x = (int)this.x;
        this.point.y = (int)this.y;
    }
    
    public void rotate(double degreesRotation) {
        final double currentPointRadius = Math.sqrt(this.x * this.x + this.y * this.y);
        final double currentPointAngle = Util.toRadians(new HighPrecisionPoint(0.0, 0.0).getAngleBetween(this));
        degreesRotation = Util.fixDegrees(degreesRotation);
        this.x = currentPointRadius * Math.sin(currentPointAngle + Util.toRadians(degreesRotation));
        this.y = -(currentPointRadius * Math.cos(currentPointAngle + Util.toRadians(degreesRotation)));
        this.point.x = (int)this.x;
        this.point.y = (int)this.y;
    }
    
    public HighPrecisionPoint cloneTranslate(final double dx, final double dy) {
        final HighPrecisionPoint pt = new HighPrecisionPoint(this);
        pt.translate(dx, dy);
        return pt;
    }
    
    public HighPrecisionPoint cloneRotate(final double degreesRotation) {
        final HighPrecisionPoint pt = new HighPrecisionPoint(this);
        pt.rotate(degreesRotation);
        return pt;
    }
}
