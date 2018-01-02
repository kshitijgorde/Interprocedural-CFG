// 
// Decompiled by Procyon v0.5.30
// 

public class mappingData
{
    public double mapScale;
    public double falseNorthing;
    public double falseEasting;
    public double semiMajorAxis;
    public double eccentricity;
    public double primeMeridian;
    
    mappingData(final double mapScale, final double falseNorthing, final double falseEasting, final double semiMajorAxis, final double eccentricity, final double primeMeridian) {
        this.mapScale = mapScale;
        this.falseNorthing = falseNorthing;
        this.falseEasting = falseEasting;
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.primeMeridian = primeMeridian;
    }
}
