// 
// Decompiled by Procyon v0.5.30
// 

class theMoon
{
    static final double P2 = 6.283185307179586;
    static final double ARC = 206264.8062;
    static final double K = 0.017453292519943295;
    double HH;
    double AZ;
    double DEC;
    double RA;
    double JD;
    double latitude;
    double longitude;
    
    theMoon(final double jd, final double LAT, final double LONG) {
        this.JD = jd;
        this.latitude = LAT;
        this.longitude = LONG;
        final double T = (jd - 2451545.0) / 36525.0;
        final double eps = 23.433333333333334 + (21.448 - 46.815 * T - 5.9E-4 * T * T + 0.001813 * T * T * T) / 3600.0;
        final double coseps = Math.cos(0.017453292519943295 * eps);
        final double sineps = Math.sin(0.017453292519943295 * eps);
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
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double Y = coseps * V - sineps * W;
        final double Z = sineps * V + coseps * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        this.DEC = 57.29577951308232 * Math.atan(Z / rho);
        this.RA = 7.639437268410976 * Math.atan2(Y, X + rho);
        final double tau = 15.0 * (this.LMST(jd, LONG) - this.RA);
        this.HH = Math.asin(Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * this.DEC)) / 0.017453292519943295;
        this.AZ = this.azim();
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    double LMST(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double elev() {
        return this.HH;
    }
    
    double THETA0(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double GHA(final double jd) {
        final double GMST = this.GM_Sidereal_Time(jd);
        double tau = 15.0 * (GMST - this.RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double azim() {
        final double h = this.THETA0(this.JD) - this.longitude - 15.0 * this.RA;
        double az = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        final double gha = this.GHA(this.JD);
        if (Math.sin(0.017453292519943295 * (gha - this.longitude)) <= 0.0) {
            az = az;
        }
        else {
            az += 180.0;
        }
        if (az < 0.0) {
            az += 180.0;
        }
        return az;
    }
    
    double declin() {
        return this.DEC;
    }
}
