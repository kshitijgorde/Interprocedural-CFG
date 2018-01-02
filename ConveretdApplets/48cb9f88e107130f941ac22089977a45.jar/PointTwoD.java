import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class PointTwoD
{
    static final int D = 700;
    public int x;
    public int y;
    
    public static PointTwoD MapToTwoD(final Rectangle rectangle, final PointThreeD pointThreeD) {
        final PointThreeD pointThreeD3;
        final PointThreeD pointThreeD2 = pointThreeD3 = new PointThreeD(pointThreeD.x, pointThreeD.y, pointThreeD.z);
        pointThreeD3.x += rectangle.width / 2;
        final PointThreeD pointThreeD4 = pointThreeD2;
        pointThreeD4.y += rectangle.height / 2;
        final PointThreeD pointThreeD5 = pointThreeD2;
        pointThreeD5.z += rectangle.width + rectangle.height / 4;
        final double n = 1.0 / (pointThreeD2.z / 700.0 + 1.0);
        final PointTwoD pointTwoD = new PointTwoD();
        pointTwoD.x = (int)(pointThreeD2.x * n);
        pointTwoD.y = (int)(pointThreeD2.y * n);
        return pointTwoD;
    }
    
    public PointTwoD() {
        this.x = 0;
        this.y = 0;
    }
    
    public PointTwoD(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
