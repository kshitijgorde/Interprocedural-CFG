// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

public class AstroCalculatorCC2K extends AstroCalculator
{
    protected double computeNewMoonTime(final int k) {
        return RiseSet.nthNewMoon(k + 23487) + 1721424.5;
    }
    
    public double sunLongitude(final double jdn) {
        return RiseSet.solarLongitude(jdn - 1721424.5) * 3.141592653589793 / 180.0;
    }
}
