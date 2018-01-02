// 
// Decompiled by Procyon v0.5.30
// 

public class Compute
{
    public double JD(final double epoque) {
        final double Y = epoque - 2000.0;
        return 2451545.0 + Y * 365.25;
    }
}
