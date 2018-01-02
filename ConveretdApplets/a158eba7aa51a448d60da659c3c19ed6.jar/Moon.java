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
    double offset;
    int iRise;
    int iSet;
    double locOffset;
    double utRise;
    double utSet;
    double utCulm;
    double moon_GHA;
    Date dat;
    boolean moonRiseOK;
    boolean moonSetOK;
    compute comp;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    double h_Rise;
    double h_Set;
    String riseStr;
    String setStr;
    double xExtremum;
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
        this.iRise = 100;
        this.iSet = 100;
        this.moonRiseOK = true;
        this.moonSetOK = true;
        this.riseStr = "?";
        this.setStr = "?";
        this.LAT = myLat;
        this.LONG = myLong;
        this.dat = myDate;
        this.offset = this.dat.getTimezoneOffset() / 60.0;
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth() + 1;
        this.year = this.dat.getYear() + 1900;
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.comp = new compute(this.dat, this.LAT, this.LONG, (int)myLocOffset);
        this.locOffset = myLocOffset;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.computeMoon(this.T = (this.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0);
        final double mjd = this.JD(this.date, this.month, this.year, this.UT) - 2400000.5;
        this.moon_GHA = 15.0 * (this.LMST(mjd, 0.0) - this.RA);
        if (this.moon_GHA < 0.0) {
            this.moon_GHA += 360.0;
        }
        if (this.moon_GHA > 360.0) {
            this.moon_GHA -= 360.0;
        }
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR) {
        final double sh = Math.sin(0.0023265238929084412);
        final double JD = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        this.computeMoon(this.T = (JD - 2451545.0) / 36525.0);
        this.Y0 = this.comp.sin_elev(JD, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        this.computeMoon(this.T = (jdPlus - 2451545.0) / 36525.0);
        this.yPlus = this.comp.sin_elev(jdPlus, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        this.computeMoon(this.T = (jdMinus - 2451545.0) / 36525.0);
        this.yMinus = this.comp.sin_elev(jdMinus, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
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
            this.xExtremum = XE;
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
    
    public double JD(final int date, int month, int year, final double STD) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
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
        final double GMST = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
        return 24.0 * this.frac(GMST / 24.0);
    }
    
    double moonDEC() {
        return this.DEC;
    }
    
    double moonGSW() {
        return this.moon_GHA;
    }
    
    public String riseString() {
        String riseStr = "";
        this.RISE = false;
        double hRise = 0.0;
        int hh = 0;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month, this.year, i);
            if (this.RISE) {
                break;
            }
        }
        if (this.RISE) {
            hRise = this.UTRISE + this.locOffset;
            if (hRise > 0.0 && hRise < 24.0) {
                hh = (int)hRise;
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
                riseStr = "Rise " + str;
            }
            else {
                riseStr = "Rise --:--";
                this.moonRiseOK = false;
            }
        }
        else {
            riseStr = "Rise --:--";
            this.moonRiseOK = false;
        }
        this.h_Rise = hRise;
        return riseStr;
    }
    
    public String setString() {
        double hSet = 0.0;
        this.SETT = false;
        int hh = 0;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month, this.year, i);
            if (this.SETT) {
                break;
            }
        }
        if (this.SETT) {
            hSet = this.UTSET + this.locOffset;
            if (hSet > 0.0 && hSet < 24.0) {
                hh = (int)hSet;
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
                this.setStr = "Set " + str;
            }
            else {
                this.setStr = "Set  --:--";
                this.moonSetOK = false;
            }
        }
        else {
            this.setStr = "Set  --:--";
            this.moonSetOK = false;
        }
        this.h_Set = hSet;
        return this.setStr;
    }
    
    public double hMoonRise() {
        return this.h_Rise;
    }
    
    public double hMoonSet() {
        return this.h_Set;
    }
    
    double culmTime() {
        double hoehe0 = 0.0;
        double t1 = 0.0;
        double hoehe2 = 0.0;
        for (int i = -(int)this.locOffset; i < -this.locOffset + 25.0; ++i) {
            this.UT = i;
            this.T = (this.comp.JD(this.date, this.month, this.year, i) - 2451545.0) / 36525.0;
            hoehe2 = this.moon_H();
            if (hoehe2 > 0.0 && hoehe2 > hoehe0) {
                hoehe0 = hoehe2;
                t1 = i;
            }
        }
        --t1;
        hoehe0 = 0.0;
        int iMax = 0;
        for (int j = 0; j < 121; ++j) {
            this.UT = t1 + j / 60.0;
            this.T = (this.comp.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0;
            hoehe2 = this.moon_H();
            if (hoehe2 > hoehe0) {
                hoehe0 = hoehe2;
                iMax = j;
            }
        }
        t1 += iMax / 60.0;
        return this.utCulm = t1;
    }
    
    double culmHeight() {
        this.T = (this.comp.JD(this.date, this.month, this.year, this.utCulm) - 2451545.0) / 36525.0;
        this.UT = this.utCulm;
        return this.moon_H();
    }
}
