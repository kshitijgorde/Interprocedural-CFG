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
    double moonDEC;
    double moonRA;
    double T;
    double LAT;
    double LONG;
    double UT;
    double DEC;
    double RA;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    int seconds;
    int locOffset;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    String riseStr;
    String setStr;
    double h_Rise;
    double h_Set;
    double T1;
    double T2;
    compute comp;
    String[] monthArray;
    String versStr;
    String locString;
    int DATE;
    int MONTH;
    int YEAR;
    double JDRISE;
    double JDSET;
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
    
    public Moon(final String vStr, final Date myDate, final double myLat, final double myLong, final double myLocOffset, final String myLoc) {
        this.riseStr = "?";
        this.setStr = "?";
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.LAT = myLat;
        this.LONG = myLong;
        this.versStr = vStr;
        this.locString = myLoc;
        this.date = myDate.getDate();
        this.month = myDate.getMonth() + 1;
        this.year = myDate.getYear() + 1900;
        this.hours = myDate.getHours();
        this.minutes = myDate.getMinutes();
        this.seconds = myDate.getSeconds();
        this.comp = new compute();
        this.locOffset = (int)myLocOffset;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double jd = this.comp.Jul_Date(this.date, this.month, this.year, this.UT);
        this.T = (jd - 2451545.0) / 36525.0;
        this.T1 = (jd - 2451545.0 - 0.041666666666666664) / 36525.0;
        this.T2 = (jd - 2451545.0 + 0.041666666666666664) / 36525.0;
        this.computeMoon(this.T);
    }
    
    public void riseset(final double JD) {
        final double sh = Math.sin(0.0023265238929084412);
        this.computeMoon(this.T = (JD - 2451545.0) / 36525.0);
        this.Y0 = this.comp.sin_elev(JD, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        final double jdPlus = JD + 0.041666666666666664;
        this.computeMoon(this.T = (jdPlus - 2451545.0) / 36525.0);
        this.yPlus = this.comp.sin_elev(jdPlus, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        final double jdMinus = JD - 0.041666666666666664;
        this.computeMoon(this.T = (jdMinus - 2451545.0) / 36525.0);
        this.yMinus = this.comp.sin_elev(jdMinus, this.LAT, -this.LONG, this.DEC, this.RA) - sh;
        this.ABOVE = (this.yMinus > 0.0);
        this.RISE = false;
        this.SETT = false;
        final double HOUR = this.caldat(3, JD);
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    this.JDRISE = JD + this.zero1 / 24.0;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                this.JDSET = JD + this.zero1 / 24.0;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                    this.JDRISE = JD + this.zero2 / 24.0;
                    this.JDSET = JD + this.zero1 / 24.0;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                    this.JDRISE = JD + this.zero1 / 24.0;
                    this.JDSET = JD + this.zero2 / 24.0;
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
    
    public void computeMoon(final double T) {
        final double L0 = this.comp.frac(0.606433 + 1336.855225 * T);
        final double L2 = 6.283185307179586 * this.comp.frac(0.374897 + 1325.55241 * T);
        final double LS = 6.283185307179586 * this.comp.frac(0.993133 + 99.997361 * T);
        final double D = 6.283185307179586 * this.comp.frac(0.827361 + 1236.853086 * T);
        final double F = 6.283185307179586 * this.comp.frac(0.259086 + 1342.227825 * T);
        final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
        final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / 206264.8062;
        final double H = F - 2.0 * D;
        final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
        final double L_Moon = 6.283185307179586 * this.comp.frac(L0 + DL / 1296000.0);
        final double B_Moon = (18520.0 * Math.sin(S) + N) / 206264.8062;
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double Y = 0.917482062 * V - 0.397777156 * W;
        final double Z = 0.397777156 * V + 0.917482062 * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        this.DEC = 57.29577951308232 * Math.atan2(Z, rho);
        this.RA = 7.639437268410976 * Math.atan2(Y, X + rho);
        this.moonDEC = this.DEC;
        this.moonRA = this.RA;
    }
    
    double alpha() {
        double ra = this.moonRA * 15.0;
        if (ra < 0.0) {
            ra += 360.0;
        }
        return ra;
    }
    
    double delta() {
        return this.moonDEC;
    }
    
    double phase() {
        final double D = 297.8502042 + 445267.1115168 * this.T - 0.00163 * this.T * this.T + this.T * this.T * this.T / 545868.0 - this.T * this.T * this.T * this.T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * this.T - 1.536E-4 * this.T * this.T + this.T * this.T * this.T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * this.T + 0.008997 * this.T * this.T + this.T * this.T * this.T / 69699.0 - this.T * this.T * this.T * this.T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    double phase1() {
        final double D = 297.8502042 + 445267.1115168 * this.T1 - 0.00163 * this.T1 * this.T1 + this.T1 * this.T1 * this.T1 / 545868.0 - this.T1 * this.T1 * this.T1 * this.T1 / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * this.T1 - 1.536E-4 * this.T1 * this.T1 + this.T1 * this.T1 * this.T1 / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * this.T1 + 0.008997 * this.T1 * this.T1 + this.T1 * this.T1 * this.T1 / 69699.0 - this.T1 * this.T1 * this.T1 * this.T1 / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    double phase2() {
        final double D = 297.8502042 + 445267.1115168 * this.T2 - 0.00163 * this.T2 * this.T2 + this.T2 * this.T2 * this.T2 / 545868.0 - this.T2 * this.T2 * this.T2 * this.T2 / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * this.T2 - 1.536E-4 * this.T2 * this.T2 + this.T2 * this.T2 * this.T2 / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * this.T2 + 0.008997 * this.T2 * this.T2 + this.T2 * this.T2 * this.T2 / 69699.0 - this.T2 * this.T2 * this.T2 * this.T2 / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    public String riseString(final double jdRS, final int n, final int start) {
        double hRise = 0.0;
        this.RISE = false;
        for (int i = start; i <= start + n; ++i) {
            this.riseset(jdRS + i / 24.0);
            if (this.RISE) {
                break;
            }
        }
        if (this.RISE) {
            hRise = this.UTRISE;
            if (hRise != 0.0) {
                this.riseStr = this.comp.makeTimeString(hRise);
            }
            else {
                this.riseStr = "--:--";
            }
        }
        else {
            this.riseStr = "--:--";
        }
        this.h_Rise = hRise;
        if (this.RISE) {
            this.riseStr = this.comp.makeTimeString(hRise + this.locOffset);
        }
        return this.riseStr;
    }
    
    public String setString(final double jdRS, final int n, final int start) {
        double hSet = 0.0;
        this.SETT = false;
        for (int i = start; i <= start + n; ++i) {
            this.riseset(jdRS + i / 24.0);
            if (this.SETT) {
                break;
            }
        }
        if (this.SETT) {
            hSet = this.UTSET;
            if (hSet != 0.0) {
                this.setStr = this.comp.makeTimeString(hSet);
            }
            else {
                this.setStr = "--:--";
            }
        }
        else {
            this.setStr = "--:--";
        }
        this.h_Set = hSet;
        if (this.SETT) {
            this.setStr = this.comp.makeTimeString(hSet + this.locOffset);
        }
        return this.setStr;
    }
    
    public double h_MoonSet() {
        return this.h_Set;
    }
    
    public double h_MoonRise() {
        return this.h_Rise;
    }
    
    double LMST(final double JD, final double LONG) {
        final double GMST = this.comp.GM_Sidereal_Time(JD);
        return 24.0 * this.comp.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double culmTime() {
        double t1 = 0.0;
        final double jd0 = this.comp.Jul_Date(this.date, this.month, this.year, 0.0) - this.locOffset / 24.0;
        final double[] h = new double[245];
        for (int i = 0; i <= 24; ++i) {
            final double jd2 = jd0 + i / 24.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) + this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            final double moonAzim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
            h[i] = moonAzim;
        }
        for (int j = 0; j < 24; ++j) {
            if (h[j] < 180.0 && h[j + 1] > 180.0) {
                t1 = j;
                break;
            }
        }
        --t1;
        int iMax = 0;
        for (int k = 0; k <= 240; ++k) {
            final double jd2 = jd0 + t1 / 24.0 + k * 0.5 / 1440.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) + this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            final double moonAzim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
            h[k] = moonAzim;
        }
        for (int l = 1; l < 240; ++l) {
            if (h[l] < 180.0 && h[l + 1] > 180.0) {
                iMax = l;
                break;
            }
        }
        t1 += 0.5 * iMax / 60.0;
        return t1;
    }
    
    double prevCulmTime() {
        double t1 = 0.0;
        double jd0 = this.comp.Jul_Date(this.date, this.month, this.year, 0.0) - this.locOffset / 24.0;
        final double[] h = new double[245];
        --jd0;
        for (int i = 0; i <= 24; ++i) {
            final double jd2 = jd0 + i / 24.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) + this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            final double moonAzim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
            h[i] = moonAzim;
        }
        for (int j = 0; j < 24; ++j) {
            if (h[j] < 180.0 && h[j + 1] > 180.0) {
                t1 = j;
                break;
            }
        }
        --t1;
        int iMax = 0;
        for (int k = 0; k <= 240; ++k) {
            final double jd2 = jd0 + t1 / 24.0 + k * 0.5 / 1440.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) + this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            final double moonAzim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
            h[k] = moonAzim;
        }
        for (int l = 1; l < 240; ++l) {
            if (h[l] < 180.0 && h[l + 1] > 180.0) {
                iMax = l;
                break;
            }
        }
        t1 += 0.5 * iMax / 60.0;
        return t1;
    }
    
    double nextCulmTime() {
        double jd0 = this.comp.Jul_Date(this.date, this.month, this.year, 0.0) - this.locOffset / 24.0;
        final double[] h = new double[245];
        final double t1 = this.culmTime();
        jd0 = jd0 + t1 / 24.0 + 1.0 + 0.02;
        int iMax = 0;
        for (int i = 0; i <= 240; ++i) {
            final double jd2 = jd0 + i * 0.5 / 1440.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) + this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            final double moonAzim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
            h[i] = moonAzim;
        }
        for (int j = 1; j < 240; ++j) {
            if (h[j] < 180.0 && h[j + 1] > 180.0) {
                iMax = j;
                break;
            }
        }
        double t2 = t1 + 0.5 * iMax / 60.0;
        t2 += 0.48;
        return t2;
    }
    
    void moonTable() {
        double t1 = 0.0;
        final double t2 = 0.0;
        final double t3 = 0.0;
        final double[] h = new double[245];
        final String[] dataStr = new String[365];
        String str = "";
        double jd0 = this.comp.Jul_Date(1, 1, this.year, 0.0) - this.locOffset / 24.0;
        for (int i = 0; i <= 24; ++i) {
            final double jd2 = jd0 + i / 24.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) - this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            h[i] = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
        }
        for (int j = 0; j < 24; ++j) {
            if (h[j] < 180.0 && h[j + 1] > 180.0) {
                t1 = j;
                break;
            }
        }
        --t1;
        jd0 += t1 / 24.0;
        int iMax = 0;
        for (int k = 0; k <= 240; ++k) {
            final double jd2 = jd0 + k * 0.5 / 1440.0;
            this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
            double H = this.comp.THETA0(jd2) - this.LONG - 15.0 * this.RA;
            if (H < 0.0) {
                H += 360.0;
            }
            h[k] = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
        }
        for (int l = 0; l < 240; ++l) {
            if (h[l] < 180.0 && h[l + 1] > 180.0) {
                iMax = l;
                break;
            }
        }
        jd0 += iMax * 0.5 / 1440.0;
        double sum = 0.0;
        int max = 0;
        double minimum = 100.0;
        double maximum = 0.0;
        int nMin = 0;
        int nMax = 0;
        final double mittel = 50.471;
        final double GradProStunde = 360.0 / (24.0 + mittel / 60.0);
        final double MinutenProGrad = 60.0 / GradProStunde;
        int clickLocOffset;
        if (this.LONG >= -7.5) {
            clickLocOffset = (int)((this.LONG + 7.5) / 15.0);
        }
        else {
            clickLocOffset = -(int)(-(this.LONG - 7.5) / 15.0);
        }
        final double longitudeCorr = (this.LONG - 15 * clickLocOffset) * MinutenProGrad;
        for (int n = 0; n <= 353; ++n) {
            iMax = 0;
            final double jd3 = jd0;
            str = String.valueOf((int)this.caldat(1, jd0 + this.locOffset / 24.0));
            if (str.length() == 1) {
                str = "0" + str;
            }
            String str2 = String.valueOf(this.monthArray[(int)this.caldat(2, jd0 + this.locOffset / 24.0) - 1]) + " " + str + "  " + this.comp.makeTimeString(this.caldat(3, jd0) + this.locOffset);
            ++jd0;
            for (int m = 0; m <= 240; ++m) {
                final double jd2 = jd0 + m * 0.5 / 1440.0;
                this.computeMoon(this.T = (jd2 - 2451545.0) / 36525.0);
                double H = this.comp.THETA0(jd2) - this.LONG - 15.0 * this.RA;
                if (H < 0.0) {
                    H += 360.0;
                }
                h[m] = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * this.LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * this.LAT)) / 0.017453292519943295;
            }
            for (int i2 = 1; i2 < 240; ++i2) {
                if (h[i2] < 180.0 && h[i2 + 1] > 180.0) {
                    iMax = i2;
                    break;
                }
            }
            jd0 += 0.5 * iMax / 1440.0;
            str = String.valueOf((int)this.caldat(1, jd0 + this.locOffset / 24.0));
            if (str.length() == 1) {
                str = "0" + str;
            }
            final String str3 = String.valueOf(this.monthArray[(int)this.caldat(2, jd0 + this.locOffset / 24.0) - 1]) + " " + str + "  " + this.comp.makeTimeString(this.caldat(3, jd0) + this.locOffset);
            double delta = this.caldat(3, jd0) - this.caldat(3, jd3);
            if (delta < 0.0) {
                delta += 24.0;
            }
            delta *= 60.0;
            if (delta > maximum) {
                maximum = delta;
                nMax = n;
            }
            if (delta < minimum) {
                minimum = delta;
                nMin = n;
            }
            sum += delta;
            str = String.valueOf(Math.round(10.0 * Math.abs(delta - mittel)) / 10.0);
            if (Math.abs(delta - mittel) < 9.96) {
                str = " " + str;
            }
            if (delta - mittel >= 0.0) {
                str = "+" + str;
            }
            else {
                str = "-" + str;
            }
            dataStr[n] = String.valueOf(str2) + "   " + str3 + "    24h " + Math.round(10.0 * delta) / 10.0 + "m" + "    " + str + " m";
            str2 = String.valueOf(Math.round(100.0 * (12.0 + longitudeCorr / 60.0 - this.caldat(3, jd3 + this.locOffset / 24.0))) / 100.0);
            dataStr[n] = String.valueOf(dataStr[n]) + "    " + str2 + "\n";
            if ((int)this.caldat(2, jd0 + this.locOffset / 24.0) - 1 == 0 && n > 50) {
                max = n + 1;
                break;
            }
        }
        str = "SunMoon Clock " + this.versStr + "\n" + "© 2007-2010 J. Giesen  - www.GeoAstro.de" + "\n";
        str = String.valueOf(str) + this.locString + ",  " + Math.abs(this.LAT);
        if (this.LAT >= 0.0) {
            str = String.valueOf(str) + " N";
        }
        else {
            str = String.valueOf(str) + " S";
        }
        str = String.valueOf(str) + "  " + Math.abs(this.LONG);
        if (this.LONG >= 0.0) {
            str = String.valueOf(str) + " W";
        }
        else {
            str = String.valueOf(str) + " E";
        }
        if (this.locOffset >= 0) {
            str = String.valueOf(str) + ",  UT +" + this.locOffset + " h";
        }
        else {
            str = String.valueOf(str) + ",  UT " + this.locOffset + " h";
        }
        str = String.valueOf(str) + "\n\n";
        str = String.valueOf(str) + "        Trans           Trans       \u2206Trans     Deviat.\n";
        dataStr[0] = String.valueOf(str) + dataStr[0];
        sum /= max;
        dataStr[354] = "Mean =  24h " + Math.round(1000.0 * sum) / 1000.0 + "m" + "\n";
        dataStr[355] = "Min  =  24h " + Math.round(1000.0 * minimum) / 1000.0 + "m" + "\n";
        final String[] array = dataStr;
        final int n2 = 355;
        array[n2] = String.valueOf(array[n2]) + dataStr[nMin];
        dataStr[356] = "Max  =  24h " + Math.round(1000.0 * maximum) / 1000.0 + "m" + "\n";
        final String[] array2 = dataStr;
        final int n3 = 356;
        array2[n3] = String.valueOf(array2[n3]) + dataStr[nMax];
        final scrollFrame sf = new scrollFrame("Lunar Days " + this.year, 360, dataStr, false);
        sf.resize(420, 400);
        sf.show();
    }
    
    void rsTable() {
        final String[] dataStr = new String[370];
        String str1 = "";
        String str2 = "";
        int n = 0;
        final double oldLong = this.LONG;
        this.LONG = -this.LONG;
        final int[] D = new int[400];
        final double[] L = new double[400];
        final double[] p = new double[400];
        double jdRS = this.comp.Jul_Date(31, 12, this.year - 1, 0.0);
        for (int i = 0; i <= 24; ++i) {
            this.riseset(jdRS + i / 24.0);
            if (this.RISE) {
                break;
            }
        }
        jdRS = this.comp.Jul_Date(1, 1, this.year, 0.0);
        for (int j = 0; j <= 24; ++j) {
            this.riseset(jdRS + j / 24.0);
            if (this.SETT) {
                break;
            }
        }
        int N = 0;
        int DR = 0;
        int DS = 0;
        int MR = 0;
        int MS = 0;
        int dJD = 0;
        for (int k = 0; k <= 366; ++k) {
            String str3 = this.riseString(this.JDRISE + 1.0, 2, 0);
            if (!this.RISE) {
                ++this.JDRISE;
                str3 = this.riseString(this.JDRISE + 1.0, 12, -1);
            }
            String str4 = this.setString(this.JDSET + 1.0, 2, 0);
            if (!this.SETT) {
                ++this.JDSET;
                str4 = this.setString(this.JDSET + 1.0, 12, -1);
            }
            double moonlight = this.h_Set - this.h_Rise;
            if (moonlight < 0.0) {
                moonlight += 24.0;
            }
            if (str3.equals("--:--") || str4.equals("--:--")) {
                moonlight = 0.0;
            }
            MR = (int)this.caldat(2, this.JDRISE + this.locOffset / 24.0);
            dataStr[n] = String.valueOf(this.monthArray[MR - 1]) + " ";
            DR = (int)this.caldat(1, this.JDRISE + this.locOffset / 24.0);
            if (DR < 10) {
                final String[] array = dataStr;
                final int n2 = n;
                array[n2] = String.valueOf(array[n2]) + "0";
            }
            double jd = jdRS + k;
            dJD = (int)this.caldat(1, jd);
            if (dJD < DR) {
                ++k;
                ++N;
            }
            D[n] = n + N;
            L[n] = moonlight;
            if (!str3.equals("--:--")) {
                final String[] array2 = dataStr;
                final int n3 = n;
                array2[n3] = String.valueOf(array2[n3]) + DR + "  " + this.comp.makeTimeString(this.caldat(3, this.JDRISE + this.locOffset / 24.0)) + "    ";
            }
            else {
                final String[] array3 = dataStr;
                final int n4 = n;
                array3[n4] = String.valueOf(array3[n4]) + DR + "  " + str3 + "    ";
            }
            MS = (int)this.caldat(2, this.JDSET + this.locOffset / 24.0);
            final String[] array4 = dataStr;
            final int n5 = n;
            array4[n5] = String.valueOf(array4[n5]) + this.monthArray[MS - 1] + " ";
            DS = (int)this.caldat(1, this.JDSET + this.locOffset / 24.0);
            if (DS < 10) {
                final String[] array5 = dataStr;
                final int n6 = n;
                array5[n6] = String.valueOf(array5[n6]) + "0";
            }
            str1 = String.valueOf(Math.round(100.0 * moonlight) / 100.0);
            if (moonlight < 10.0) {
                str1 = " " + str1;
            }
            while (str1.length() < 5) {
                str1 = String.valueOf(str1) + " ";
            }
            if (!str4.equals("--:--")) {
                final String[] array6 = dataStr;
                final int n7 = n;
                array6[n7] = String.valueOf(array6[n7]) + DS + "  " + this.comp.makeTimeString(this.caldat(3, this.JDSET + this.locOffset / 24.0)) + "    " + str1;
            }
            else {
                final String[] array7 = dataStr;
                final int n8 = n;
                array7[n8] = String.valueOf(array7[n8]) + DS + "  " + str4 + "    " + str1;
            }
            jd = this.comp.Jul_Date(DR, MR, this.year, this.caldat(3, this.JDRISE) + 0.5 * moonlight);
            this.T = (jd - 2451545.0) / 36525.0;
            str2 = "        " + Math.round(1000.0 * this.phase()) / 10.0;
            p[n] = this.phase();
            final String[] array8 = dataStr;
            final int n9 = n;
            array8[n9] = String.valueOf(array8[n9]) + str2 + "\n";
            ++n;
            if (MR == 12) {
                if (DR == 31) {
                    break;
                }
                if (DS == 31) {
                    break;
                }
            }
        }
        String latStr = String.valueOf(Math.abs(this.LAT));
        if (this.LAT >= 0.0) {
            latStr = String.valueOf(latStr) + " N";
        }
        else {
            latStr = String.valueOf(latStr) + " S";
        }
        String longStr = String.valueOf(Math.abs(this.LONG));
        if (this.LONG >= 0.0) {
            longStr = String.valueOf(longStr) + " E";
        }
        else {
            longStr = String.valueOf(longStr) + " W";
        }
        final graphFrame gf = new graphFrame("Moonlight " + this.year + ",  " + latStr + ", " + longStr, n - 1, D, L, p);
        gf.resize(800, 550);
        gf.show();
        this.LONG = oldLong;
        String str5 = "SunMoon Clock " + this.versStr + "\n" + "© 2007-2010 J. Giesen  - www.GeoAstro.de" + "\n";
        str5 = String.valueOf(str5) + this.locString + ",  " + Math.abs(this.LAT);
        if (this.LAT >= 0.0) {
            str5 = String.valueOf(str5) + " N";
        }
        else {
            str5 = String.valueOf(str5) + " S";
        }
        str5 = String.valueOf(str5) + "  " + Math.abs(this.LONG);
        if (this.LONG >= 0.0) {
            str5 = String.valueOf(str5) + " W";
        }
        else {
            str5 = String.valueOf(str5) + " E";
        }
        if (this.locOffset >= 0) {
            str5 = String.valueOf(str5) + ",  UT +" + this.locOffset + " h";
        }
        else {
            str5 = String.valueOf(str5) + ",  UT " + this.locOffset + " h";
        }
        str5 = String.valueOf(str5) + "\n\n";
        str5 = String.valueOf(str5) + "Rise             Set              Moonlight    Ill.Frac.\n";
        str5 = String.valueOf(str5) + "                                  hours        at Transit\n";
        dataStr[0] = String.valueOf(str5) + dataStr[0];
        final scrollFrame sf = new scrollFrame("Moon Rise and Set " + this.year, n, dataStr, false);
        sf.resize(400, 400);
        sf.show();
    }
    
    public double caldat(final int what, final double JD) {
        final int Z = (int)(JD + 0.5);
        final double F = this.comp.frac(JD + 0.5);
        int A;
        if (Z < 2299161) {
            A = Z;
        }
        else {
            final int a = (int)((Z - 1867216.25) / 36524.25);
            A = Z + 1 + a - (int)(a / 4.0);
        }
        final int B = A + 1524;
        final int C = (int)((B - 122.1) / 365.25);
        final int D = (int)(365.25 * C);
        final int E = (int)((B - D) / 30.6001);
        final double DAY = B - D - (int)(30.6001 * E) + F;
        final int day = (int)DAY;
        int month;
        if (E < 14) {
            month = E - 1;
        }
        else {
            month = E - 13;
        }
        int year;
        if (month > 2) {
            year = C - 4716;
        }
        else {
            year = C - 4715;
        }
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month;
        }
        if (what == 3) {
            return 24.0 * this.comp.frac(DAY);
        }
        return year;
    }
}
