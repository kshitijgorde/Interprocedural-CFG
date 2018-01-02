import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sinusoidal extends MapProjection
{
    static final double C00 = 1.0;
    static final double C02 = 0.25;
    static final double C04 = 0.046875;
    static final double C06 = 0.01953125;
    static final double C08 = 0.01068115234375;
    static final double C22 = 0.75;
    static final double C44 = 0.46875;
    static final double C46 = 0.013020833333333334;
    static final double C48 = 0.007120768229166667;
    static final double C66 = 0.3645833333333333;
    static final double C68 = 0.005696614583333333;
    static final double C88 = 0.3076171875;
    static final double EPS = 1.0E-11;
    static final double EPS10 = 1.0E-10;
    static final int MAX_ITER = 10;
    static final double HALFPI = 1.5707963267948966;
    static final int EN_SIZE = 5;
    double[] en;
    double es;
    
    Sinusoidal(final mappingData mappingData) {
        super(mappingData);
        this.en = new double[5];
        this.es = mappingData.eccentricity * mappingData.eccentricity;
        this.en[0] = 1.0 - this.es * (0.25 + this.es * (0.046875 + this.es * (0.01953125 + this.es * 0.01068115234375)));
        this.en[1] = this.es * (0.75 - this.es * (0.046875 + this.es * (0.01953125 + this.es * 0.01068115234375)));
        final double n;
        this.en[2] = (n = this.es * this.es) * (0.46875 - this.es * (0.013020833333333334 + this.es * 0.007120768229166667));
        final double n2;
        this.en[3] = (n2 = n * this.es) * (0.3645833333333333 - this.es * 0.005696614583333333);
        this.en[4] = n2 * this.es * 0.3076171875;
    }
    
    double pj_mlfn(final double n, double n2, double n3) {
        n3 *= n2;
        n2 *= n2;
        return this.en[0] * n - n3 * (this.en[1] + n2 * (this.en[2] + n2 * (this.en[3] + n2 * this.en[4])));
    }
    
    double pj_inv_mlfn(final double n) {
        final double n2 = 1.0 / (1.0 - this.es);
        double n3 = n;
        for (int i = 10; i > 0; --i) {
            final double sin = Math.sin(n3);
            final double n4 = 1.0 - this.es * sin * sin;
            final double n5 = (this.pj_mlfn(n3, sin, Math.cos(n3)) - n) * (n4 * Math.sqrt(n4)) * n2;
            n3 -= n5;
            if (Math.abs(n5) < 1.0E-11) {
                return n3;
            }
        }
        return n3;
    }
    
    public Point forward(final geoPos geoPos) {
        final double n = geoPos.latitude * 3.141592653589793 / 180.0;
        final double n2 = (geoPos.longitude - super.PRIME_MERIDIAN) * 3.141592653589793 / 180.0;
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        return new Point((int)(super.FALSE_EASTING - super.a * (n2 * cos / Math.sqrt(1.0 - this.es * sin * sin)) / super.MAP_SCALE), (int)(super.FALSE_NORTHING - super.a * this.pj_mlfn(n, sin, cos) / super.MAP_SCALE));
    }
    
    public geoPos inverse(final Point point) {
        point.x = (int)super.FALSE_EASTING - point.x;
        point.y = (int)super.FALSE_NORTHING - point.y;
        final double n = point.x * super.MAP_SCALE / super.a;
        final double pj_inv_mlfn = this.pj_inv_mlfn(point.y * super.MAP_SCALE / super.a);
        double n2;
        if (Math.abs(pj_inv_mlfn) < 1.5707963267948966) {
            final double sin = Math.sin(pj_inv_mlfn);
            n2 = n * Math.sqrt(1.0 - this.es * sin * sin) / Math.cos(pj_inv_mlfn);
        }
        else {
            n2 = 0.0;
        }
        return new geoPos(pj_inv_mlfn * 180.0 / 3.141592653589793, n2 * 180.0 / 3.141592653589793 + super.PRIME_MERIDIAN);
    }
}
