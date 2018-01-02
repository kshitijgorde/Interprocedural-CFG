import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class MapProjection
{
    double MAP_SCALE;
    double FALSE_NORTHING;
    double FALSE_EASTING;
    double PRIME_MERIDIAN;
    double a;
    double e;
    int ITERATIONS;
    
    MapProjection(final mappingData mappingData) {
        this.ITERATIONS = 4;
        this.MAP_SCALE = mappingData.mapScale;
        this.FALSE_NORTHING = mappingData.falseNorthing;
        this.FALSE_EASTING = mappingData.falseEasting;
        this.PRIME_MERIDIAN = mappingData.primeMeridian;
        this.a = mappingData.semiMajorAxis;
        this.e = mappingData.eccentricity;
    }
    
    public abstract Point forward(final geoPos p0);
    
    public abstract geoPos inverse(final Point p0);
}
