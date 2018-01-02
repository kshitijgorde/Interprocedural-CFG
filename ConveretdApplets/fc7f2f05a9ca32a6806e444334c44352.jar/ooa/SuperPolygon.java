// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Polygon;

public class SuperPolygon extends Polygon
{
    public SuperPolygon cloneRotate(final double degreesRotation) {
        final SuperPolygon returnValue = new SuperPolygon();
        for (int i = 0; i < super.npoints; ++i) {
            final HighPrecisionPoint rotatedPoint = new HighPrecisionPoint(super.xpoints[i], super.ypoints[i]);
            rotatedPoint.rotate(degreesRotation);
            returnValue.addPoint((int)rotatedPoint.x, (int)rotatedPoint.y);
        }
        return returnValue;
    }
    
    public SuperPolygon cloneTranslate(final int transX, final int transY) {
        final SuperPolygon returnValue = new SuperPolygon();
        for (int i = 0; i < super.npoints; ++i) {
            returnValue.addPoint(super.xpoints[i] + transX, super.ypoints[i] + transY);
        }
        return returnValue;
    }
}
