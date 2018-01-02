import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class SinusoidalProjection implements ProjectionTransformation
{
    private double HALF_PI;
    private double TWO_PI;
    private double EPSLN;
    private double R2D;
    private double D2R;
    private double lon_center;
    private double radius;
    
    SinusoidalProjection(final double radius, final double n) {
        this.HALF_PI = 1.5707963267948966;
        this.TWO_PI = 6.283185307179586;
        this.EPSLN = 1.0E-10;
        this.R2D = 57.2957795131;
        this.D2R = 0.01745329252;
        this.radius = radius;
        this.lon_center = n * this.D2R;
    }
    
    @Override
    public LatLong projToLatLong(final int n, final int n2) {
        final double n3 = n;
        final double n4 = n2 / this.radius;
        final double abs = Math.abs(n4);
        if (abs > this.HALF_PI) {
            return null;
        }
        double lon_center;
        if (Math.abs(abs - this.HALF_PI) > this.EPSLN) {
            lon_center = this.lon_center + n3 / (this.radius * Math.cos(n4));
        }
        else {
            lon_center = this.lon_center;
        }
        return new LatLong(n4 * this.R2D, lon_center * this.R2D);
    }
    
    @Override
    public Point latLongToProj(final LatLong latLong) {
        final double n = latLong.latitude * this.D2R;
        return new Point((int)Math.floor(this.radius * (latLong.longitude * this.D2R - this.lon_center) * Math.cos(n) + 0.5), (int)Math.floor(this.radius * n + 0.5));
    }
}
