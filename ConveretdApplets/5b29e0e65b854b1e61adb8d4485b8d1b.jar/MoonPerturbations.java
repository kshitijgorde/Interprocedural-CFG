// 
// Decompiled by Procyon v0.5.30
// 

class MoonPerturbations
{
    public static double moonLongitudeCorrectionDegrees(final double n) {
        final double n2 = 0.0;
        final double n3 = 0.0;
        final double n4 = 0.0;
        final double n5 = 0.0;
        final double n6 = 0.0;
        final double n7 = 0.0;
        sunNwM(n2, n3, n4, n);
        moonNwM(n5, n6, n7, n);
        final double n8 = n7 + n5 + n6 - (n4 + n2 + n3);
        return -1.274 * sind(n7 - 2.0 * n8) + 0.658 * sind(2.0 * n8) - 0.186 * sind(n4);
    }
    
    public static double moonLatitudeCorrectionDegrees(final double n) {
        final double n2 = 0.0;
        final double n3 = 0.0;
        final double n4 = 0.0;
        final double n5 = 0.0;
        final double n6 = 0.0;
        final double n7 = 0.0;
        sunNwM(n2, n3, n4, n);
        moonNwM(n5, n6, n7, n);
        final double n8 = n4 + n2 + n3;
        final double n9 = n7 + n5 + n6;
        final double n10 = n9 - n8;
        final double n11 = n9 - n5;
        return -0.173 * sind(n11 - 2.0 * n10) - 0.055 * sind(n7 - n11 - 2.0 * n10) - 0.046 * sind(n7 + n11 - 2.0 * n10);
    }
    
    private static void sunNwM(double n, double n2, double n3, final double n4) {
        n = 0.0;
        n2 = 282.9404 + 4.70935E-5 * n4;
        n3 = 356.047 + 0.9856002585 * n4;
    }
    
    private static void moonNwM(double n, double n2, double n3, final double n4) {
        n = 125.1228 - 0.0529538083 * n4;
        n2 = 318.0634 + 0.1643573223 * n4;
        n3 = 115.3654 + 13.0649929509 * n4;
    }
    
    private static double sind(final double n) {
        return Math.sin(n * 3.141592653589793 / 180.0);
    }
}
