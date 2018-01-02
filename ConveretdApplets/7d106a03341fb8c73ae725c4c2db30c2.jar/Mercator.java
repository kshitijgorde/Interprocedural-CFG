import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mercator extends MapProjection
{
    Mercator(final mappingData mappingData) {
        super(mappingData);
    }
    
    double term2(final double n) {
        final double sin = Math.sin(n);
        return Math.pow(1.0 - super.e * sin + super.e * sin, super.e / 2.0);
    }
    
    double isometricLatitude(final double n) {
        return Math.log(Math.tan(0.7853981633974483 + n / 2.0) * this.term2(n));
    }
    
    public geoPos inverse(final Point point) {
        final double n = super.MAP_SCALE * (super.FALSE_NORTHING - point.y) / super.a;
        double n2 = 2.0 * (Math.atan(Math.exp(n)) - 0.7853981633974483);
        for (int i = 0; i < super.ITERATIONS; ++i) {
            n2 = 2.0 * (Math.atan(Math.exp(n) / this.term2(n2)) - 0.7853981633974483);
        }
        return new geoPos(n2 * 180.0 / 3.141592653589793, super.MAP_SCALE * (super.FALSE_EASTING - point.x) / super.a * 180.0 / 3.141592653589793 + super.PRIME_MERIDIAN);
    }
    
    public Point forward(final geoPos geoPos) {
        return new Point((int)(super.FALSE_EASTING - super.a * ((geoPos.longitude - super.PRIME_MERIDIAN) * 3.141592653589793 / 180.0) / super.MAP_SCALE), (int)(super.FALSE_NORTHING - super.a * this.isometricLatitude(geoPos.latitude * 3.141592653589793 / 180.0) / super.MAP_SCALE));
    }
}
