// 
// Decompiled by Procyon v0.5.30
// 

class MOON
{
    static final double P2 = 6.283185307179586;
    static final double ARC = 206264.8062;
    static final double coseps = 0.917482062;
    static final double sineps = 0.397777156;
    static final double K = 0.017453292519943295;
    double moonDEC;
    double moon_RA;
    double B_Moon;
    double L_Moon;
    double T;
    double GHA;
    double RA;
    double LAT;
    double LONG;
    double JD;
    
    public MOON(final double jd, final double Lat, final double Long) {
        this.LAT = Lat;
        this.LONG = -Long;
        this.JD = jd;
        this.computeMoon(this.T = (this.JD - 2451545.0) / 36525.0);
        this.GHA = 15.0 * (this.GMST(this.JD) - this.RA);
        if (this.GHA < 0.0) {
            this.GHA += 360.0;
        }
        if (this.GHA > 360.0) {
            this.GHA -= 360.0;
        }
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public void computeMoon(final double T) {
        final double L0 = this.frac(0.606433 + 1336.855225 * T);
        final double L2 = 6.283185307179586 * this.frac(0.374897 + 1325.55241 * T);
        final double LS = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double D = 6.283185307179586 * this.frac(0.827361 + 1236.853086 * T);
        final double F = 6.283185307179586 * this.frac(0.259086 + 1342.227825 * T);
        final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
        final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / 206264.8062;
        final double H = F - 2.0 * D;
        final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
        this.L_Moon = 6.283185307179586 * this.frac(L0 + DL / 1296000.0);
        this.B_Moon = (18520.0 * Math.sin(S) + N) / 206264.8062;
        final double CB = Math.cos(this.B_Moon);
        final double X = CB * Math.cos(this.L_Moon);
        final double V = CB * Math.sin(this.L_Moon);
        final double W = Math.sin(this.B_Moon);
        final double Y = 0.917482062 * V - 0.397777156 * W;
        final double Z = 0.397777156 * V + 0.917482062 * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan2(Z, rho);
        this.RA = 7.639437268410976 * Math.atan2(Y, X + rho);
        this.moonDEC = DEC;
        this.moon_RA = this.RA;
    }
    
    double GMST(final double JD) {
        final double MJD = JD - 2400000.5;
        final double MJD2 = (int)MJD;
        final double UT = (MJD - (int)MJD) * 24.0;
        final double T = (MJD2 - 51544.5) / 36525.0;
        final double gmst = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
        return 24.0 * this.frac(gmst / 24.0);
    }
    
    double altitude() {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(this.JD, this.LONG) - this.moon_RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sin = Math.cos(0.017453292519943295 * this.LAT) * Math.cos(0.017453292519943295 * this.moonDEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * this.LAT) * Math.sin(0.017453292519943295 * this.moonDEC);
        return Math.asin(sin) / 0.017453292519943295;
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double phase() {
        final double D = 297.8502042 + 445267.1115168 * this.T - 0.00163 * this.T * this.T + this.T * this.T * this.T / 545868.0 - this.T * this.T * this.T * this.T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * this.T - 1.536E-4 * this.T * this.T + this.T * this.T * this.T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * this.T + 0.008997 * this.T * this.T + this.T * this.T * this.T / 69699.0 - this.T * this.T * this.T * this.T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    double computeR() {
        final double K = 0.017453292519943295;
        final double D = 0.017453292519943295 * (297.8502042 + 445267.1115168 * this.T - 0.00163 * this.T * this.T + this.T * this.T * this.T / 545868.0 - this.T * this.T * this.T * this.T / 1.13065E8);
        final double M = 0.017453292519943295 * (357.5291092 + 35999.0502909 * this.T - 1.536E-4 * this.T * this.T + this.T * this.T * this.T / 2.449E7);
        final double MS = 0.017453292519943295 * (134.9634114 + 477198.8676313 * this.T + 0.008997 * this.T * this.T + this.T * this.T * this.T / 69699.0 - this.T * this.T * this.T * this.T / 1.4712E7);
        final double F = 0.017453292519943295 * (93.2720993 + 483202.0175273 * this.T - 0.0034029 * this.T * this.T - this.T * this.T * this.T / 3526000.0 + this.T * this.T * this.T * this.T / 8.6331E8);
        final double E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        double r = -2.0905355E7 * Math.cos(MS) - 3699111.0 * Math.cos(2.0 * D - MS) - 2955968.0 * Math.cos(2.0 * D) - 569925.0 * Math.cos(2.0 * MS) + E * 48888.0 * Math.cos(M) - 3149.0 * Math.cos(2.0 * F);
        r = r + 246158.0 * Math.cos(2.0 * D - 2.0 * MS) - E * 152138.0 * Math.cos(2.0 * D - M - MS) - 170733.0 * Math.cos(2.0 * D + MS) - E * 204586.0 * Math.cos(2.0 * D - M) - E * 129620.0 * Math.cos(M - MS);
        r = r + 108743.0 * Math.cos(D) + E * 104755.0 * Math.cos(M + MS) + 10321.0 * Math.cos(2.0 * D - 2.0 * F) + 79661.0 * Math.cos(MS - 2.0 * F) - 34782.0 * Math.cos(4.0 * D - MS) - 23210.0 * Math.cos(3.0 * MS);
        r = r - 21636.0 * Math.cos(4.0 * D - 2.0 * MS) + E * 24208.0 * Math.cos(2.0 * D + M - MS) + E * 30824.0 * Math.cos(2.0 * D + M) - 8379.0 * Math.cos(D - MS) - E * 16675.0 * Math.cos(D + M);
        r = r - E * 12831.0 * Math.cos(2.0 * D - M + MS) - 10445.0 * Math.cos(2.0 * D + 2.0 * MS) - 11650.0 * Math.cos(4.0 * D) + 14403.0 * Math.cos(2.0 * D - 3.0 * MS) - E * 7003.0 * Math.cos(M - 2.0 * MS);
        r = r + E * 10056.0 * Math.cos(2.0 * D - M - 2.0 * MS) + 6322.0 * Math.cos(D + MS) - E * E * 9884.0 * Math.cos(2.0 * D - 2.0 * M) + E * 5751.0 * Math.cos(M + 2.0 * MS) - E * E * 4950.0 * Math.cos(2.0 * D - 2.0 * M - MS);
        r = r + 4130.0 * Math.cos(2.0 * D + MS - 2.0 * F) - E * 3958.0 * Math.cos(4.0 * D - M - MS) + 3258.0 * Math.cos(3.0 * D - MS) + E * 2616.0 * Math.cos(2.0 * D + M + MS) - E * 1897.0 * Math.cos(4.0 * D - M - 2.0 * MS);
        r = r - E * E * 2117.0 * Math.cos(2.0 * M - MS) + E * E * 2354.0 * Math.cos(2.0 * D + 2.0 * M - MS) - 1423.0 * Math.cos(4.0 * D + MS) - 1117.0 * Math.cos(4.0 * MS) - E * 1571.0 * Math.cos(4.0 * D - M);
        r = r - 1739.0 * Math.cos(D - 2.0 * MS) - 4421.0 * Math.cos(2.0 * MS - 2.0 * F) + E * E * 1165.0 * Math.cos(2.0 * M + MS) + 8752.0 * Math.cos(2.0 * D - MS - 2.0 * F);
        return 385000.56 + r / 1000.0;
    }
}
