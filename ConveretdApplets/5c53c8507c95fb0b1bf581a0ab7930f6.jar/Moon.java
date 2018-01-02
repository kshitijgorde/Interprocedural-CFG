import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class Moon
{
    static final double P2 = 6.283185307179586;
    static final double ARC = 206264.8062;
    static final double coseps = 0.917482062;
    static final double sineps = 0.397777156;
    static final double K = 0.017453292519943295;
    double RA;
    double DEC;
    double LAT;
    double LONG;
    double T;
    double declin;
    double GMST;
    double MJD;
    double UT;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    int seconds;
    double locOffset;
    double moon_GHA;
    Date dat;
    String riseStr;
    String setStr;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    double h_Rise;
    double h_Set;
    double moonDEC;
    double moon_RA;
    double YE;
    double zero1;
    double zero2;
    double UTRISE;
    double UTSET;
    int NZ;
    double Y0;
    double yPlus;
    double yMinus;
    double DX;
    
    public Moon(final Date myDate, final double myLat, final double myLong, final double myLocOffset) {
        this.riseStr = "?";
        this.setStr = "?";
        this.LAT = myLat;
        this.LONG = myLong;
        this.dat = myDate;
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth() + 1;
        this.year = this.dat.getYear() + 1900;
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.locOffset = myLocOffset;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.computeMoon(this.T = (this.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0);
        this.moon_GHA = 15.0 * (this.LMST(this.JD(this.date, this.month, this.year, this.UT) - 2400000.5, 0.0) - this.RA);
        if (this.moon_GHA < 0.0) {
            this.moon_GHA += 360.0;
        }
        if (this.moon_GHA > 360.0) {
            this.moon_GHA -= 360.0;
        }
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR) {
        final double sh = Math.sin(0.0023265238929084412);
        final double JD = this.JD(DATE, MONTH, YEAR, HOUR);
        this.computeMoon(this.T = (JD - 2451545.0) / 36525.0);
        this.Y0 = this.sin_elev(JD, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        final double jdPlus = this.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        this.computeMoon(this.T = (jdPlus - 2451545.0) / 36525.0);
        this.yPlus = this.sin_elev(jdPlus, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        final double jdMinus = this.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        this.computeMoon(this.T = (jdMinus - 2451545.0) / 36525.0);
        this.yMinus = this.sin_elev(jdMinus, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        this.ABOVE = (this.yMinus > 0.0);
        this.RISE = false;
        this.SETT = false;
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD() {
        this.NZ = 0;
        final double A = 0.5 * (this.yMinus + this.yPlus) - this.Y0;
        final double B = 0.5 * (this.yPlus - this.yMinus);
        final double C = this.Y0;
        final double XE = -B / (2.0 * A);
        this.YE = (A * XE + B) * XE + C;
        final double DIS = B * B - 4.0 * A * C;
        if (DIS >= 0.0) {
            this.DX = 0.5 * Math.sqrt(DIS) / Math.abs(A);
            this.zero1 = XE - this.DX;
            this.zero2 = XE + this.DX;
            if (Math.abs(this.zero1) <= 1.0) {
                ++this.NZ;
            }
            if (Math.abs(this.zero2) <= 1.0) {
                ++this.NZ;
            }
            if (this.zero1 < -1.0) {
                this.zero1 = this.zero2;
            }
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
        final double L_Moon = 6.283185307179586 * this.frac(L0 + DL / 1296000.0);
        final double B_Moon = (18520.0 * Math.sin(S) + N) / 206264.8062;
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double Y = 0.917482062 * V - 0.397777156 * W;
        final double Z = 0.397777156 * V + 0.917482062 * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        this.DEC = 57.29577951308232 * Math.atan(Z / rho);
        this.RA = 7.639437268410976 * Math.atan(Y / (X + rho));
        this.moonDEC = this.DEC;
        this.moon_RA = this.RA;
    }
    
    double phase() {
        final double D = 297.8502042 + 445267.1115168 * this.T - 0.00163 * this.T * this.T + this.T * this.T * this.T / 545868.0 - this.T * this.T * this.T * this.T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * this.T - 1.536E-4 * this.T * this.T + this.T * this.T * this.T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * this.T + 0.008997 * this.T * this.T + this.T * this.T * this.T / 69699.0 - this.T * this.T * this.T * this.T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    double phaseMinus(final double dt) {
        final double t = this.T - dt / 36525.0;
        final double D = 297.8502042 + 445267.1115168 * t - 0.00163 * t * t + t * t * t / 545868.0 - t * t * t * t / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * t - 1.536E-4 * t * t + t * t * t / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * t + 0.008997 * t * t + t * t * t / 69699.0 - t * t * t * t / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    double phasePlus(final double dt) {
        final double t = this.T + dt / 36525.0;
        final double D = 297.8502042 + 445267.1115168 * t - 0.00163 * t * t + t * t * t / 545868.0 - t * t * t * t / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * t - 1.536E-4 * t * t + t * t * t / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * t + 0.008997 * t * t + t * t * t / 69699.0 - t * t * t * t / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    public double moon_H() {
        this.computeMoon(this.T);
        double tau = 15.0 * (this.LMST(this.JD(this.date, this.month, this.year, this.UT) - 2400000.5, this.LONG) - this.RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * this.LAT) * Math.cos(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * this.LAT) * Math.sin(0.017453292519943295 * this.DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public double moon_Az() {
        this.computeMoon(this.T);
        final double H = this.moon_H();
        final double cosAz = (Math.sin(this.DEC * 0.017453292519943295) - Math.sin(this.LAT * 0.017453292519943295) * Math.sin(H * 0.017453292519943295)) / (Math.cos(H * 0.017453292519943295) * Math.cos(0.017453292519943295 * this.LAT));
        double Az = 1.5707963267948966 - Math.asin(cosAz);
        Az /= 0.017453292519943295;
        if (Math.sin(0.017453292519943295 * (this.moonGSW() + this.LONG)) <= 0.0) {
            Az = Az;
        }
        else {
            Az = 360.0 - Az;
        }
        return Az;
    }
    
    double LMST(final double MJD, double LAMBDA) {
        LAMBDA = -LAMBDA;
        final double MJD2 = (int)MJD;
        final double UT = (MJD - (int)MJD) * 24.0;
        final double T = (MJD2 - 51544.5) / 36525.0;
        this.GMST = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
        return 24.0 * this.frac((this.GMST - LAMBDA / 15.0) / 24.0);
    }
    
    double GMST(final double MJD) {
        final double MJD2 = (int)MJD;
        final double UT = (MJD - (int)MJD) * 24.0;
        final double T = (MJD2 - 51544.5) / 36525.0;
        this.GMST = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
        return 24.0 * this.frac(this.GMST / 24.0);
    }
    
    double moonDEC() {
        return this.moonDEC;
    }
    
    double moonGSW() {
        return this.moon_GHA;
    }
    
    double moonRA() {
        return this.moon_RA;
    }
    
    public String riseString() {
        double hRise = 0.0;
        this.RISE = false;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month, this.year, i);
            if (this.RISE) {
                break;
            }
        }
        if (this.RISE) {
            hRise = this.UTRISE + this.locOffset;
            if (hRise > 0.0 && hRise < 24.0) {
                int hh = (int)hRise;
                final double diff = hRise - hh;
                int min = (int)Math.round(diff * 60.0);
                if (min == 60) {
                    min = 0;
                    ++hh;
                }
                String str;
                if (min > 9) {
                    str = ":";
                }
                else {
                    str = ":0";
                }
                str = hh + str + min;
                if (hh < 10) {
                    str = "0" + str;
                }
                this.riseStr = str;
            }
            else {
                this.riseStr = " --:--";
            }
        }
        else {
            this.riseStr = "Rise     --:--";
            this.riseStr = " --:--";
        }
        this.h_Rise = hRise;
        return this.riseStr;
    }
    
    public String setString() {
        double hSet = 0.0;
        final String next = "";
        this.SETT = false;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month, this.year, i);
            if (this.SETT) {
                break;
            }
        }
        if (this.SETT) {
            hSet = this.UTSET + this.locOffset;
            if (hSet > 0.0 && hSet < 24.0) {
                int hh = (int)hSet;
                final double diff = hSet - hh;
                int min = (int)Math.round(diff * 60.0);
                if (min == 60) {
                    min = 0;
                    ++hh;
                }
                String str;
                if (min > 9) {
                    str = ":";
                }
                else {
                    str = ":0";
                }
                str = hh + str + min;
                if (hh < 10) {
                    str = "0" + str;
                }
                this.setStr = String.valueOf(str) + next;
            }
            else {
                this.setStr = " --:--";
            }
        }
        else {
            this.setStr = " --:--";
        }
        this.h_Set = hSet;
        return this.setStr;
    }
    
    public double h_rise() {
        return this.h_Rise;
    }
    
    public double h_set() {
        return this.h_Set;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
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
}
