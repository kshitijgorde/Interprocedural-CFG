// 
// Decompiled by Procyon v0.5.30
// 

class Compute
{
    public double computeLat(final double longitude, final double dec) {
        final double K = 0.017453292519943295;
        final double tan = -Math.cos(longitude * K) / Math.tan(dec * K);
        double itan = Math.atan(tan);
        itan /= K;
        return itan;
    }
    
    public double computeGHA(final int T, final int M, final int J, final double STD) {
        final double K = 0.017453292519943295;
        long N = 365 * J + T + 31 * M - 46;
        if (M < 3) {
            N += (J - 1) / 4;
        }
        else {
            N = N - (int)(0.4 * M + 2.3) + (int)(J / 4.0);
        }
        final double P = STD / 24.0;
        double X = (P + N - 722449.0) * 0.98564734 + 279.306;
        X *= K;
        double XX = -104.55 * Math.sin(X) - 429.266 * Math.cos(X) + 595.63 * Math.sin(2.0 * X) - 2.283 * Math.cos(2.0 * X);
        XX = XX + 4.6 * Math.sin(3.0 * X) + 18.7333 * Math.cos(3.0 * X);
        XX = XX - 13.2 * Math.sin(4.0 * X) - Math.cos(5.0 * X) - Math.sin(5.0 * X) / 3.0 + 0.5 * Math.sin(6.0 * X) + 0.231;
        XX = XX / 240.0 + 360.0 * (P + 0.5);
        if (XX > 360.0) {
            XX -= 360.0;
        }
        return XX;
    }
    
    public double computeDeclination(final int T, final int M, final int J, final double STD) {
        final double K = 0.017453292519943295;
        long N = 365 * J + T + 31 * M - 46;
        if (M < 3) {
            N += (J - 1) / 4;
        }
        else {
            N = N - (int)(0.4 * M + 2.3) + (int)(J / 4.0);
        }
        double X = (N - 693960L) / 1461.0;
        X = (X - (int)X) * 1440.02509 + (int)X * 0.0307572;
        X = X + STD / 24.0 * 0.9856645 + 356.6498973;
        X += 1.91233 * Math.sin(0.9999825 * X * K);
        X = (X + Math.sin(1.999965 * X * K) / 50.0 + 282.55462) / 360.0;
        X = (X - (int)X) * 360.0;
        final double J2 = (J - 2000) / 100.0;
        final double Ekliptik = 23.43929111 - (46.815 + (5.9E-4 - 0.001813 * J2) * J2) * J2 / 3600.0;
        X = Math.sin(X * K) * Math.sin(K * Ekliptik);
        return Math.atan(X / Math.sqrt(1.0 - X * X)) / K + 7.5E-4;
    }
    
    public double computeHeight(final double dec, final double latitude, final double longitude, final double GHA) {
        final double K = 0.017453292519943295;
        final double sinHeight = Math.sin(dec * K) * Math.sin(latitude * K) + Math.cos(dec * K) * Math.cos(K * latitude) * Math.cos(K * (GHA + longitude));
        double height = Math.asin(sinHeight);
        height /= K;
        return height;
    }
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        final double K = 0.017453292519943295;
        final double cosAz = (Math.sin(dec * K) - Math.sin(latitude * K) * Math.sin(hoehe * K)) / (Math.cos(hoehe * K) * Math.cos(K * latitude));
        double Az = 1.5707963267948966 - Math.asin(cosAz);
        Az /= K;
        if (Math.sin(K * (GHA + longitude)) < 0.0) {
            Az = Az;
        }
        if (Math.sin(K * (GHA + longitude)) > 0.0) {
            Az = 360.0 - Az;
        }
        return Az;
    }
}
