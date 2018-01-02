// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

public interface IAstroCalculator
{
    public static final double MEAN_TROPICAL_YEAR = 365.242199;
    public static final double MEAN_SYNODIC_MONTH = 29.530588853;
    public static final double NEWMOON_PER_YEAR = 12.3685;
    public static final double NEWMOON_19000101 = 2415021.07771163;
    public static final double PI = 3.141592653589793;
    public static final double SPRING_EQUINOX_1900 = 2415099.568645406;
    
    double newMoonTime(final int p0);
    
    double sunLongitude(final double p0);
    
    double getTimeSolarTerm(final int p0);
    
    int getDaySolarTerm(final int p0, final int p1);
}
