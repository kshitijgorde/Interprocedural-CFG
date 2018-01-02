// 
// Decompiled by Procyon v0.5.30
// 

class Compute
{
    public double computeLat(final int n, final double n2) {
        final double n3 = 0.017453292519943295;
        return Math.atan(-Math.cos(n * n3) / Math.tan(n2 * n3)) / n3;
    }
    
    public double computeGHA(final int n, final int n2, final int n3, final double n4) {
        final double n5 = 0.017453292519943295;
        final long n6 = 365 * n3 + n + 31 * n2 - 46;
        long n7;
        if (n2 < 3) {
            n7 = n6 + (n3 - 1) / 4;
        }
        else {
            n7 = n6 - (int)(0.4 * n2 + 2.3) + (int)(n3 / 4.0);
        }
        final double n8 = n4 / 24.0;
        final double n9 = ((n8 + n7 - 722449.0) * 0.98564734 + 279.306) * n5;
        double n10 = (-104.55 * Math.sin(n9) - 429.266 * Math.cos(n9) + 595.63 * Math.sin(2.0 * n9) - 2.283 * Math.cos(2.0 * n9) + 4.6 * Math.sin(3.0 * n9) + 18.7333 * Math.cos(3.0 * n9) - 13.2 * Math.sin(4.0 * n9) - Math.cos(5.0 * n9) - Math.sin(5.0 * n9) / 3.0 + 0.5 * Math.sin(6.0 * n9) + 0.231) / 240.0 + 360.0 * (n8 + 0.5);
        if (n10 > 360.0) {
            n10 -= 360.0;
        }
        return n10;
    }
    
    public double computeDeclination(final int n, final int n2, final int n3, final double n4) {
        final double n5 = 0.017453292519943295;
        final long n6 = 365 * n3 + n + 31 * n2 - 46;
        long n7;
        if (n2 < 3) {
            n7 = n6 + (n3 - 1) / 4;
        }
        else {
            n7 = n6 - (int)(0.4 * n2 + 2.3) + (int)(n3 / 4.0);
        }
        final double n8 = (n7 - 693960L) / 1461.0;
        final double n9 = (n8 - (int)n8) * 1440.02509 + (int)n8 * 0.0307572 + n4 / 24.0 * 0.9856645 + 356.6498973;
        final double n10 = n9 + 1.91233 * Math.sin(0.9999825 * n9 * n5);
        final double n11 = (n10 + Math.sin(1.999965 * n10 * n5) / 50.0 + 282.55462) / 360.0;
        final double n12 = (n11 - (int)n11) * 360.0;
        final double n13 = (n3 - 2000) / 100.0;
        final double n14 = Math.sin(n12 * n5) * Math.sin(n5 * (23.43929111 - (46.815 + (5.9E-4 - 0.001813 * n13) * n13) * n13 / 3600.0));
        return Math.atan(n14 / Math.sqrt(1.0 - n14 * n14)) / n5 + 7.5E-4;
    }
    
    public double computeHeight(final double n, final double n2, final double n3, final double n4) {
        final double n5 = 0.017453292519943295;
        return Math.asin(Math.sin(n * n5) * Math.sin(n2 * n5) + Math.cos(n * n5) * Math.cos(n5 * n2) * Math.cos(n5 * (n4 + n3))) / n5;
    }
    
    public double computeAzimut(final double n, final double n2, final double n3, final double n4, final double n5) {
        final double n6 = 0.017453292519943295;
        double n7 = (1.5707963267948966 - Math.asin((Math.sin(n * n6) - Math.sin(n2 * n6) * Math.sin(n5 * n6)) / (Math.cos(n5 * n6) * Math.cos(n6 * n2)))) / n6;
        if (Math.sin(n6 * (n4 + n3)) < 0.0) {
            n7 = n7;
        }
        if (Math.sin(n6 * (n4 + n3)) > 0.0) {
            n7 = 360.0 - n7;
        }
        return n7;
    }
}
