import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolarStereographicProjection implements ProjectionTransformation
{
    private double HALF_PI;
    private double TWO_PI;
    private double EPSLN;
    private double R2D;
    private double D2R;
    private double r_major;
    private double r_minor;
    private double es;
    private double e;
    private double e4;
    private double center_lon;
    private double center_lat;
    private double fac;
    private double ind;
    private double mcs;
    private double tcs;
    private double false_northing;
    private double false_easting;
    
    PolarStereographicProjection(final double r_major, final double r_minor, final double n, final double n2, final double false_easting, final double false_northing) {
        this.HALF_PI = 1.5707963267948966;
        this.TWO_PI = 6.283185307179586;
        this.EPSLN = 1.0E-10;
        this.R2D = 57.2957795131;
        this.D2R = 0.01745329252;
        this.r_major = r_major;
        this.r_minor = r_minor;
        this.false_northing = false_northing;
        this.false_easting = false_easting;
        this.center_lon = n * this.D2R;
        this.center_lat = n2 * this.D2R;
        final double n3 = this.r_minor / this.r_major;
        this.es = 1.0 - n3 * n3;
        this.e = Math.sqrt(this.es);
        this.e4 = this.e4fn(this.e);
        if (this.center_lat < 0.0) {
            this.fac = -1.0;
        }
        else {
            this.fac = 1.0;
        }
        this.ind = 0.0;
        if (Math.abs(Math.abs(this.center_lat) - this.HALF_PI) > this.EPSLN) {
            this.ind = 1.0;
            final double n4 = this.fac * this.center_lat;
            final double sin = Math.sin(n4);
            this.mcs = this.msfnz(this.e, sin, Math.cos(n4));
            this.tcs = this.tsfnz(this.e, n4, sin);
        }
    }
    
    private double e4fn(final double n) {
        final double n2 = 1.0 + n;
        final double n3 = 1.0 - n;
        return Math.sqrt(Math.pow(n2, n2) * Math.pow(n3, n3));
    }
    
    private double msfnz(final double n, final double n2, final double n3) {
        final double n4 = n * n2;
        return n3 / Math.sqrt(1.0 - n4 * n4);
    }
    
    private double tsfnz(final double n, final double n2, final double n3) {
        final double n4 = n * n3;
        return Math.tan(0.5 * (this.HALF_PI - n2)) / Math.pow((1.0 - n4) / (1.0 + n4), 0.5 * n);
    }
    
    @Override
    public LatLong projToLatLong(final int n, final int n2) {
        final double n3 = (n - this.false_easting) * this.fac;
        final double n4 = (n2 - this.false_northing) * this.fac;
        final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
        double n5;
        if (this.ind != 0.0) {
            n5 = sqrt * this.tcs / (this.r_major * this.mcs);
        }
        else {
            n5 = sqrt * this.e4 / (this.r_major * 2.0);
        }
        final double phi2z = this.phi2z(this.e, n5);
        if (phi2z > 100.0) {
            return null;
        }
        final double n6 = this.fac * phi2z;
        double adjust_lon;
        if (sqrt == 0.0) {
            adjust_lon = this.fac * this.center_lon;
        }
        else {
            adjust_lon = this.adjust_lon(this.fac * Math.atan2(n3, -n4) + this.center_lon);
        }
        return new LatLong(n6 * this.R2D, adjust_lon * this.R2D);
    }
    
    double phi2z(final double n, final double n2) {
        final double n3 = 0.5 * n;
        double n4 = this.HALF_PI - 2.0 * Math.atan(n2);
        for (long n5 = 0L; n5 <= 15L; ++n5) {
            final double n6 = n * Math.sin(n4);
            final double n7 = this.HALF_PI - 2.0 * Math.atan(n2 * Math.pow((1.0 - n6) / (1.0 + n6), n3)) - n4;
            n4 += n7;
            if (Math.abs(n7) <= 1.0E-10) {
                return n4;
            }
        }
        System.out.println("Polar Stereographic convergence error");
        return 200.0;
    }
    
    @Override
    public Point latLongToProj(final LatLong latLong) {
        final double n = latLong.latitude * this.D2R;
        final double n2 = this.fac * this.adjust_lon(latLong.longitude * this.D2R - this.center_lon);
        final double n3 = this.fac * n;
        final double tsfnz = this.tsfnz(this.e, n3, Math.sin(n3));
        double n4;
        if (this.ind != 0.0) {
            n4 = this.r_major * this.mcs * tsfnz / this.tcs;
        }
        else {
            n4 = 2.0 * this.r_major * tsfnz / this.e4;
        }
        return new Point((int)Math.round(this.fac * n4 * Math.sin(n2) + this.false_easting), (int)Math.round(-this.fac * n4 * Math.cos(n2) + this.false_northing));
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
