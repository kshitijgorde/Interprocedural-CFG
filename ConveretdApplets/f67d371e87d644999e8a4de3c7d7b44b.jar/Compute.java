// 
// Decompiled by Procyon v0.5.30
// 

class Compute
{
    final double K = 0.017453292519943295;
    
    public double computeLat(final int longitude, final double dec) {
        final double K = 0.017453292519943295;
        final double tan = -Math.cos(longitude * K) / Math.tan(dec * K);
        double itan = Math.atan(tan);
        itan /= K;
        return itan;
    }
    
    double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        return (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B - 1524.5 + UT / 24.0;
    }
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11))) / 60.0;
    }
    
    public double EPS(final double T) {
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        return eps0;
    }
    
    double sun_GHA(final double JD, final double RA) {
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA);
        tau %= 360.0;
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double sunL(final double T) {
        final double tau = T / 10.0;
        double L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double DeclinationRightAscension(final int what, final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double L = this.sunL(T);
        double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        M %= 360.0;
        if (M < 0.0) {
            M += 360.0;
        }
        double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M);
        C += (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M);
        C += 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double theta = L + C;
        final double eps = this.EPS(T);
        final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
        final double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda)) / 0.017453292519943295;
        if (what == 1) {
            return delta;
        }
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda)) / 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        return RA / 15.0;
    }
    
    public double computeGHA(final int T, final int M, final int J, final double STD) {
        final double jd = this.JD(T, M, J, STD);
        final double RA = this.DeclinationRightAscension(2, jd);
        return this.sun_GHA(jd, RA);
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
