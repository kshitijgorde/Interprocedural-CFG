// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

public class AstroCalculatorAA98 extends AstroCalculator
{
    protected double computeNewMoonTime(final int k) {
        final double T = k / 1236.85;
        final double T2 = T * T;
        final double T3 = T2 * T;
        final double dr = 0.017453292519943295;
        double jd1 = 2415020.75933 + 29.53058868 * k + 1.178E-4 * T2 - 1.55E-7 * T3;
        jd1 += 3.3E-4 * Math.sin((166.56 + 132.87 * T - 0.009173 * T2) * dr);
        final double M = 359.2242 + 29.10535608 * k - 3.33E-5 * T2 - 3.47E-6 * T3;
        final double Mpr = 306.0253 + 385.81691806 * k + 0.0107306 * T2 + 1.236E-5 * T3;
        final double F = 21.2964 + 390.67050646 * k - 0.0016528 * T2 - 2.39E-6 * T3;
        double C1 = (0.1734 - 3.93E-4 * T) * Math.sin(M * dr) + 0.0021 * Math.sin(2.0 * dr * M);
        C1 = C1 - 0.4068 * Math.sin(Mpr * dr) + 0.0161 * Math.sin(dr * 2.0 * Mpr);
        C1 -= 4.0E-4 * Math.sin(dr * 3.0 * Mpr);
        C1 = C1 + 0.0104 * Math.sin(dr * 2.0 * F) - 0.0051 * Math.sin(dr * (M + Mpr));
        C1 = C1 - 0.0074 * Math.sin(dr * (M - Mpr)) + 4.0E-4 * Math.sin(dr * (2.0 * F + M));
        C1 = C1 - 4.0E-4 * Math.sin(dr * (2.0 * F - M)) - 6.0E-4 * Math.sin(dr * (2.0 * F + Mpr));
        C1 = C1 + 0.001 * Math.sin(dr * (2.0 * F - Mpr)) + 5.0E-4 * Math.sin(dr * (2.0 * Mpr + M));
        double deltat;
        if (T < -11.0) {
            deltat = 0.001 + 8.39E-4 * T + 2.261E-4 * T2 - 8.45E-6 * T3 - 8.1E-8 * T * T3;
        }
        else {
            deltat = -2.78E-4 + 2.65E-4 * T + 2.62E-4 * T2;
        }
        final double jdNew = jd1 + C1 - deltat;
        return jdNew;
    }
    
    public double sunLongitude(final double jdn) {
        final double T = (jdn - 2451545.0) / 36525.0;
        final double T2 = T * T;
        final double dr = 0.017453292519943295;
        final double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T2 - 4.8E-7 * T * T2;
        final double L0 = 280.46645 + 36000.76983 * T + 3.032E-4 * T2;
        double DL = (1.9146 - 0.004817 * T - 1.4E-5 * T2) * Math.sin(dr * M);
        DL = DL + (0.019993 - 1.01E-4 * T) * Math.sin(dr * 2.0 * M) + 2.9E-4 * Math.sin(dr * 3.0 * M);
        double L2 = L0 + DL;
        L2 *= dr;
        L2 -= 6.283185307179586 * (int)Math.floor(L2 / 6.283185307179586);
        return L2;
    }
}
