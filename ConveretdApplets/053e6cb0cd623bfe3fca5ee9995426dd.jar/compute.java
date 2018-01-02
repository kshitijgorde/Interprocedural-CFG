// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    final double P2 = 6.283185307179586;
    static final double ARC = 206264.8062;
    
    public String HMS(double x) {
        String str = "";
        x /= 15.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + ":";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + ":";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC);
    }
    
    public String hms(double x) {
        String str = "";
        x /= 15.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + "h ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC) + "''";
    }
    
    public double moonParal(final double elev, final double distance) {
        final double horParal = 8.794 / (distance / 1.4959787E8);
        final double paral = Math.cos(0.017453292519943295 * elev) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        return Math.asin(paral) / 0.017453292519943295;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    public double computeMoon(final double jd, final int what) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double L0 = this.frac(0.606433 + 1336.855225 * T);
        final double L2 = 6.283185307179586 * this.frac(0.374897 + 1325.55241 * T);
        final double LS = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double D = 6.283185307179586 * this.frac(0.827361 + 1236.853086 * T);
        final double F = 6.283185307179586 * this.frac(0.259086 + 1342.227825 * T);
        final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
        final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / 206264.8062;
        final double H = F - 2.0 * D;
        final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
        final double L_Moon = 6.283185307179586 * this.frac(L0 + DL / 1296000.0);
        final double B_Moon = (18520.0 * Math.sin(S) + N) / 206264.8062;
        if (what == 3) {
            return L_Moon;
        }
        if (what == 4) {
            return B_Moon;
        }
        return 0.0;
    }
    
    public double MoonMeeus(final double jd, final int what) {
        final double T = (jd - 2415020.0) / 36525.0;
        double Ls = 270.434164 + 481267.8831 * T - 0.001133 * T * T + 1.9E-6 * T * T * T;
        double M = 358.475833 + 35999.0498 * T - 1.5E-4 * T * T - 3.3E-6 * T * T * T;
        double Ms = 296.104608 + 477198.8491 * T + 0.009192 * T * T + 1.44E-5 * T * T * T;
        double D = 350.737486 + 445267.1142 * T - 0.001436 * T * T + 1.9E-6 * T * T * T;
        double F = 11.250889 + 483202.0251 * T - 0.003211 * T * T - 3.0E-7 * T * T * T;
        final double om = 259.183275 - 1934.142 * T + 0.002078 * T * T + 2.2E-6 * T * T * T;
        final double a1 = 2.33E-4 * Math.sin(0.017453292519943295 * (51.2 + 20.2 * T));
        final double a2 = -0.001778 * Math.sin(0.017453292519943295 * (51.2 + 20.2 * T));
        final double a3 = 8.17E-4 * Math.sin(0.017453292519943295 * (51.2 + 20.2 * T));
        final double a4 = 0.002011 * Math.sin(0.017453292519943295 * (51.2 + 20.0 * T));
        final double a5 = 0.003964 * Math.sin(0.017453292519943295 * (346.56 + 132.87 * T - 0.0091731 * T * T));
        final double a6 = 0.001964 * Math.sin(0.017453292519943295 * om);
        final double a7 = 0.002541 * Math.sin(0.017453292519943295 * om);
        final double a8 = 0.001964 * Math.sin(0.017453292519943295 * om);
        final double a9 = -0.024691 * Math.sin(0.017453292519943295 * om);
        final double a10 = -0.004328 * Math.sin(0.017453292519943295 * (om + 275.05 - 2.3 * T));
        Ls = Ls + a1 + a5 + a6;
        M += a2;
        Ms = Ms + a3 + a5 + a7;
        D = D + a4 + a5 + a8;
        F = F + a5 + a9 + a10;
        final double e = 1.0 - 0.002495 * T - 7.52E-6 * T * T;
        double lambda = Ls + 6.28875 * Math.sin(0.017453292519943295 * Ms) + 1.274018 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) + 0.658309 * Math.sin(0.03490658503988659 * D);
        lambda += 0.213616 * Math.sin(0.03490658503988659 * Ms) - e * 0.185596 * Math.sin(0.017453292519943295 * M) - 0.114336 * Math.sin(0.03490658503988659 * F);
        lambda += 0.058793 * Math.sin(0.03490658503988659 * (D - Ms)) + e * 0.057212 * Math.sin(0.017453292519943295 * (2.0 * D - M - Ms)) + 0.05332 * Math.sin(0.017453292519943295 * (2.0 * D + Ms));
        lambda += e * 0.045874 * Math.sin(0.017453292519943295 * (2.0 * D - M)) + e * 0.041024 * Math.sin(0.017453292519943295 * (Ms - M)) - 0.034718 * Math.sin(0.017453292519943295 * D);
        lambda += -e * 0.030465 * Math.sin(0.017453292519943295 * (M + Ms)) + 0.015326 * Math.sin(0.03490658503988659 * (D - F)) - 0.012528 * Math.sin(0.017453292519943295 * (2.0 * F + Ms));
        lambda += -0.01098 * Math.sin(0.017453292519943295 * (2.0 * F - Ms)) + 0.010674 * Math.sin(0.017453292519943295 * (4.0 * D - Ms)) + 0.010034 * Math.sin(0.05235987755982989 * Ms);
        lambda += 0.008548 * Math.sin(0.017453292519943295 * (4.0 * D - 2.0 * Ms)) - e * 0.00791 * Math.sin(0.017453292519943295 * (M - Ms + 2.0 * D)) - e * 0.006783 * Math.sin(0.017453292519943295 * (2.0 * D + M));
        lambda += 0.005162 * Math.sin(0.017453292519943295 * (Ms - D)) + e * 0.005 * Math.sin(0.017453292519943295 * (M + D)) + e * 0.004049 * Math.sin(0.017453292519943295 * (Ms - M + 2.0 * D));
        lambda += 0.003996 * Math.sin(0.017453292519943295 * (2.0 * Ms + 2.0 * D)) + 0.003862 * Math.sin(0.06981317007977318 * D) + 0.003665 * Math.sin(0.017453292519943295 * (2.0 * D - 3.0 * Ms));
        lambda += e * 0.002695 * Math.sin(0.017453292519943295 * (2.0 * Ms - M)) + 0.002602 * Math.sin(0.017453292519943295 * (Ms - 2.0 * F - 2.0 * D)) + e * 0.002396 * Math.sin(0.017453292519943295 * (2.0 * D - M - 2.0 * Ms));
        lambda += -0.002349 * Math.sin(0.017453292519943295 * (Ms + D)) + e * e * 0.002249 * Math.sin(0.03490658503988659 * (D - M)) - e * 0.002125 * Math.sin(0.017453292519943295 * (2.0 * Ms + M));
        lambda += -e * e * 0.002079 * Math.sin(0.03490658503988659 * M) + e * e * 0.002059 * Math.sin(0.017453292519943295 * (2.0 * D - Ms - 2.0 * M)) - 0.001773 * Math.sin(0.017453292519943295 * (Ms + 2.0 * D - 2.0 * F));
        lambda += -0.001595 * Math.sin(0.03490658503988659 * (F + D)) + e * 0.00122 * Math.sin(0.017453292519943295 * (4.0 * D - M - Ms)) - 0.00111 * Math.sin(0.03490658503988659 * (Ms + F));
        lambda += 8.92E-4 * Math.sin(0.017453292519943295 * (Ms - 3.0 * D)) - e * 8.11E-4 * Math.sin(0.017453292519943295 * (M + Ms + 2.0 * D)) + e * 7.61E-4 * Math.sin(0.017453292519943295 * (4.0 * D - M - 2.0 * Ms));
        lambda += e * e * 7.17E-4 * Math.sin(0.017453292519943295 * (Ms - 2.0 * M)) + e * e * 7.04E-4 * Math.sin(0.017453292519943295 * (Ms - 2.0 * M - 2.0 * D)) + e * 6.93E-4 * Math.sin(0.017453292519943295 * (M - 2.0 * Ms + 2.0 * D));
        lambda += e * 5.98E-4 * Math.sin(0.017453292519943295 * (2.0 * D - M - 2.0 * F)) + 5.5E-4 * Math.sin(0.017453292519943295 * (Ms + 4.0 * D)) + 5.38E-4 * Math.sin(0.06981317007977318 * Ms);
        lambda += e * 5.21E-4 * Math.sin(0.017453292519943295 * (4.0 * D - M)) + 4.86E-4 * Math.sin(0.017453292519943295 * (2.0 * Ms - D));
        lambda %= 360.0;
        if (lambda < 0.0) {
            lambda += 360.0;
        }
        final double L_Moon = 0.017453292519943295 * lambda;
        final double B_Moon = this.computeMoon(jd, 4);
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double t = (jd - 2451545.0) / 36525.0;
        final double eps = 23.43929111111111 - (46.815 * t + 5.9E-4 * t * t - 0.001813 * t * t * t) / 3600.0;
        final double Y = Math.cos(0.017453292519943295 * eps) * V - Math.sin(0.017453292519943295 * eps) * W;
        final double Z = Math.sin(0.017453292519943295 * eps) * V + Math.cos(0.017453292519943295 * eps) * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        if (what == 1) {
            return 57.29577951308232 * Math.atan2(Z, rho);
        }
        if (what == 2) {
            return 7.639437268410976 * Math.atan2(Y, X + rho);
        }
        if (what == 3) {
            return L_Moon;
        }
        if (what == 4) {
            return B_Moon;
        }
        return 0.0;
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double THETA0(final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (jd - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    public double azimuth(final double jd, final double ra, final double dec, final double latitude, final double longitude) {
        final double H = this.THETA0(jd) + longitude - 15.0 * ra;
        return 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * latitude)) / 0.017453292519943295;
    }
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11))) / 60.0;
    }
    
    public double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        final int C = (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B;
        return C - 1524.5 + UT / 24.0;
    }
}
