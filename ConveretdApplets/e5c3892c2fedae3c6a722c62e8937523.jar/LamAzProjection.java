import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class LamAzProjection implements ProjectionTransformation
{
    private double HALF_PI;
    private double TWO_PI;
    private double EPSLN;
    private double R2D;
    private double D2R;
    private double lon_center;
    private double lat_center;
    private double R;
    private double sin_lat_o;
    private double cos_lat_o;
    
    LamAzProjection(final double r, final double n, final double n2) {
        this.HALF_PI = 1.5707963267948966;
        this.TWO_PI = 6.283185307179586;
        this.EPSLN = 1.0E-10;
        this.R2D = 57.2957795131;
        this.D2R = 0.01745329252;
        this.R = r;
        this.lon_center = n * this.D2R;
        this.lat_center = n2 * this.D2R;
        this.sin_lat_o = Math.sin(this.lat_center);
        this.cos_lat_o = Math.cos(this.lat_center);
    }
    
    @Override
    public LatLong projToLatLong(final int n, final int n2) {
        final double n3 = n;
        final double n4 = n2;
        final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
        final double n5 = sqrt / (2.0 * this.R);
        if (n5 > 1.0) {
            System.out.println("Error in LamAz projToLatLong");
            return null;
        }
        final double n6 = 2.0 * Math.asin(n5);
        final double sin = Math.sin(n6);
        final double cos = Math.cos(n6);
        double n7 = this.lon_center;
        double n8;
        if (Math.abs(sqrt) > this.EPSLN) {
            n8 = Math.asin(this.sin_lat_o * cos + this.cos_lat_o * sin * n4 / sqrt);
            if (Math.abs(Math.abs(this.lat_center) - this.HALF_PI) > this.EPSLN) {
                final double n9 = cos - this.sin_lat_o * Math.sin(n8);
                if (n9 != 0.0) {
                    n7 = this.adjust_lon(this.lon_center + Math.atan2(n3 * sin * this.cos_lat_o, n9 * sqrt));
                }
            }
            else if (this.lat_center < 0.0) {
                n7 = this.adjust_lon(this.lon_center - Math.atan2(-n3, n4));
            }
            else {
                n7 = this.adjust_lon(this.lon_center + Math.atan2(n3, -n4));
            }
        }
        else {
            n8 = this.lat_center;
        }
        return new LatLong(n8 * this.R2D, n7 * this.R2D);
    }
    
    @Override
    public Point latLongToProj(final LatLong latLong) {
        final double n = latLong.latitude * this.D2R;
        final double n2 = latLong.longitude * this.D2R;
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final double adjust_lon = this.adjust_lon(n2 - this.lon_center);
        final double sin2 = Math.sin(adjust_lon);
        final double cos2 = Math.cos(adjust_lon);
        final double n3 = this.sin_lat_o * sin + this.cos_lat_o * cos * cos2;
        if (n3 == -1.0) {
            System.out.println("Error in LamAz latLongToProj");
            return null;
        }
        final double n4 = this.R * Math.sqrt(2.0 / (1.0 + n3));
        return new Point((int)Math.floor(n4 * cos * sin2 + 0.5), (int)Math.floor(n4 * (this.cos_lat_o * sin - this.sin_lat_o * cos * cos2) + 0.5));
    }
    
    private int sign(final double n) {
        if (n < 0.0) {
            return -1;
        }
        return 1;
    }
    
    private double adjust_lon(double n) {
        n = ((Math.abs(n) < 3.141592653589793) ? n : (n - this.sign(n) * this.TWO_PI));
        return n;
    }
}
