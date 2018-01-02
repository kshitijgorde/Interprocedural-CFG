import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class PositionAngle
{
    static final double P2 = 6.283185307179586;
    static final double K = 0.017453292519943295;
    static final double ARC = 206264.8062;
    final double coseps = 0.917482062;
    final double sineps = 0.397777156;
    double sunDec;
    double sunRA;
    double moonDec;
    double moonRA;
    double sunL;
    double moonL;
    
    public PositionAngle(final Date theDate, final double theLat, final double theLong, final double theLocOffset) {
        final Compute comp = new Compute();
        final int date = theDate.getDate();
        final int month = theDate.getMonth() + 1;
        final int year = theDate.getYear() + 1900;
        final int hours = theDate.getHours();
        final int minutes = theDate.getMinutes();
        final int seconds = theDate.getSeconds();
        final double UT = hours - theLocOffset + minutes / 60.0 + seconds / 3600.0;
        final double JulDat = comp.JD(date, month, year, UT);
        final double T = (JulDat - 2451545.0) / 36525.0;
        this.Moon(T);
        this.Sun(JulDat);
    }
    
    double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    void Moon(final double T) {
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
        final double Y = 0.917482062 * V - 0.397777156 * W;
        final double Z = 0.397777156 * V + 0.917482062 * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan(Z / rho);
        final double RA = 7.639437268410976 * Math.atan(Y / (X + rho));
        this.moonDec = DEC;
        this.moonRA = RA * 15.0;
        this.moonL = L_Moon * 360.0 / 6.283185307179586;
    }
    
    void Sun(final double JD) {
        final double PI2 = 6.283185307179586;
        final double cos_eps = 0.917482062;
        final double sin_eps = 0.397777156;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482062 * SL;
        final double Z = 0.397777156 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan(Z / R);
        double RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (RA < 0.0) {
            RA += 24.0;
        }
        this.sunDec = DEC;
        this.sunRA = RA * 15.0;
        this.sunL = (0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0) * 360.0;
        if (this.sunL > 360.0) {
            this.sunL -= 360.0;
        }
    }
    
    double MoonL() {
        return this.moonL;
    }
    
    double p_angle() {
        final double Z = Math.cos(0.017453292519943295 * this.sunDec) * Math.sin(0.017453292519943295 * (this.sunRA - this.moonRA));
        final double N = Math.sin(0.017453292519943295 * this.sunDec) * Math.cos(0.017453292519943295 * this.moonDec) - Math.cos(0.017453292519943295 * this.sunDec) * Math.sin(0.017453292519943295 * this.moonDec) * Math.cos(0.017453292519943295 * (this.sunRA - this.moonRA));
        return Math.atan2(Z, N) / 0.017453292519943295;
    }
}
