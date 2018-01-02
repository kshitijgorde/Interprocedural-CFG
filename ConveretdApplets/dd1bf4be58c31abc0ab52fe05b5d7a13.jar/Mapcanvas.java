import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dialog;
import java.awt.Cursor;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Mapcanvas extends Canvas
{
    final int mapOben = 460;
    final int mapRechts = 490;
    final int xL = 30;
    final double xFaktor = 1.2555555555555555;
    final double yFaktor = 1.2444444444444445;
    final int x0;
    final int y0;
    final int mapUnten = 224;
    final char deg = '°';
    Image myMap;
    Date dat;
    int locOffset;
    int day;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double dec;
    double GHA;
    double longitude;
    double latitude;
    int xMouse;
    int yMouse;
    boolean clicked;
    public Compute comp;
    double moonH;
    double moonAz;
    int hMoon;
    int xMoon;
    boolean isMoon;
    static final double P2 = 6.283185307179586;
    static final double ARC = 206264.8062;
    static final double coseps = 0.917482062;
    static final double sineps = 0.397777156;
    static final double K = 0.017453292519943295;
    int[][] LongLat;
    double STD;
    double sunDecGlobal;
    double sunRAGlobal;
    double sunLong;
    double moonLong;
    String[] monthArray;
    String[] dataStr;
    String hourStr;
    double[] newMoonJD;
    double jdLastNew;
    double jdNextNew;
    boolean drawFirst;
    boolean isSun;
    double longi;
    double lati;
    String moonAgeStr;
    double currentJD;
    boolean alt;
    boolean DayNightOK;
    
    public Mapcanvas(final Image map, final Date myDate, final double myLat, final double myLong, final boolean myClick, final int mylocOffset, final boolean myMoon, final boolean mySun, final boolean altOK, final boolean dnOK) {
        this.x0 = (int)Math.round(226.0);
        this.y0 = (int)Math.round(112.0) + 460;
        this.xMouse = 0;
        this.yMouse = 0;
        this.LongLat = new int[200][100];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.dataStr = new String[20];
        this.newMoonJD = new double[17];
        this.jdLastNew = 0.0;
        this.jdNextNew = 0.0;
        this.drawFirst = false;
        this.myMap = map;
        this.dat = myDate;
        this.longitude = myLong;
        this.latitude = myLat;
        this.clicked = myClick;
        this.locOffset = mylocOffset;
        this.isMoon = myMoon;
        this.isSun = mySun;
        this.alt = altOK;
        this.DayNightOK = dnOK;
        this.comp = new Compute();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        if (this.GHA < 0.0) {
            this.GHA += 360.0;
        }
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.STD);
        this.currentJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.dataNewMoon(false, 1, false);
    }
    
    public void dataNewMoon(final boolean showFrame, final int when, final boolean doAll) {
        final double[] jD = new double[20];
        double curJD;
        if (when == 1) {
            curJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        }
        else {
            curJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, 24 - this.locOffset);
        }
        int minNewMoon = 0;
        int hourNM = 0;
        final double[] delta = new double[122];
        int N = 0;
        String strD = "";
        double jd = this.comp.JD(1, 1, this.year + 1900, 0.0);
        for (int n = -300; n < 750; ++n) {
            if (Math.abs(2449128.59 + 29.53058867 * n - 0.3 - jd) < 30.0) {
                N = n;
                break;
            }
        }
        int nn = 0;
        for (int n2 = N; n2 <= N + 14; ++n2) {
            jd = 2449128.59 + 29.53058867 * n2 - 1.0;
            if (!doAll && jd > curJD) {
                break;
            }
            for (int i = 0; i <= 48; ++i) {
                final SunMoon sunmoon = new SunMoon(jd + i / 24.0, 6);
                double SunMoonExcess = 360.0 - (sunmoon.lambda() - sunmoon.heliocentricLambda());
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[i] = SunMoonExcess;
            }
            hourNM = 0;
            for (int j = 0; j < 48; ++j) {
                if (delta[j] > 359.0 && delta[j + 1] < 1.0) {
                    hourNM = j;
                    break;
                }
            }
            if (Math.abs(delta[hourNM + 1]) < Math.abs(delta[hourNM] - 360.0)) {
                ++hourNM;
            }
            --hourNM;
            for (int k = 0; k <= 120; ++k) {
                final SunMoon sunmoon = new SunMoon(jd + hourNM / 24.0 + k / 1440.0, 6);
                double SunMoonExcess = 360.0 - (sunmoon.lambda() - sunmoon.heliocentricLambda());
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[k] = SunMoonExcess;
            }
            minNewMoon = 0;
            for (int l = 0; l < 120; ++l) {
                if (delta[l] > 359.0 && delta[l + 1] < 1.0) {
                    minNewMoon = l;
                    break;
                }
            }
            final double delt = (360.0 - delta[minNewMoon]) / (360.0 - delta[minNewMoon] + delta[minNewMoon + 1]);
            final double jdNM = jd + hourNM / 24.0 + (minNewMoon + delt) / 1440.0;
            final int D = this.calDat(1, jdNM);
            if (D < 10) {
                strD = "0" + D;
            }
            else {
                strD = String.valueOf(D);
            }
            if (showFrame) {
                this.dataStr[n2 - N] = String.valueOf(this.calDat(3, jdNM)) + " " + this.comp.dayString(jdNM) + " " + this.monthArray[this.calDat(2, jdNM)] + " " + strD + " " + this.hourStr;
            }
            jD[n2 - N] = jdNM;
            if (jD[n2 - N] < curJD) {
                this.jdLastNew = jD[n2 - N];
                nn = n2;
            }
            this.newMoonJD[n2 - N] = jD[n2 - N];
        }
        this.jdNextNew = jD[nn - N + 1];
        if (showFrame) {
            this.dataStr[15] = "Current date: " + this.dat.toString();
            this.dataStr[16] = "After prev. New Moon:  " + this.comp.DaysHoursMinutes(curJD - this.jdLastNew);
            this.dataStr[17] = "Before next New Moon:  " + this.comp.DaysHoursMinutes(this.jdNextNew - curJD);
            for (int n3 = 0; n3 <= 17; ++n3) {
                final String[] dataStr = this.dataStr;
                final int n4 = n3;
                dataStr[n4] = String.valueOf(dataStr[n4]) + "\n";
            }
            final scrollFrame sf = new scrollFrame("New Moon " + (this.year + 1900), 17, this.dataStr);
            sf.resize(300, 280);
            sf.show();
        }
    }
    
    public int calDat(final int what, final double jd) {
        final double JD0 = (int)(jd + 0.5);
        final int B = (int)((JD0 - 1867216.25) / 36524.25);
        final double C = JD0 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        double hour = 24.0 * (jd + 0.5 - JD0);
        int min = (int)Math.round(60.0 * this.frac(hour));
        if (min == 60) {
            min = 0;
            ++hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        this.hourStr = String.valueOf((int)hour) + str + min;
        if ((int)hour < 10) {
            this.hourStr = "0" + this.hourStr;
        }
        this.hourStr = String.valueOf(this.hourStr) + " UT";
        switch (what) {
            case 1: {
                return day;
            }
            case 2: {
                return month - 1;
            }
            case 3: {
                return year;
            }
            case 4: {
                return (int)Math.round(hour);
            }
            default: {
                return 0;
            }
        }
    }
    
    double LMST(final double MJD, final double LAMBDA) {
        final double MJD2 = (int)MJD;
        final double UT = (MJD - (int)MJD) * 24.0;
        final double T = (MJD2 - 51544.5) / 36525.0;
        final double gmst = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
        return 24.0 * this.frac((gmst + LAMBDA / 15.0) / 24.0);
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
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public double moon_Elev(final double JD, final double LAT, final double LONG) {
        final double T = (JD - 2451545.0) / 36525.0;
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
        final double tau = 15.0 * (this.LMST(JD - 2400000.5, LONG) - RA);
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        this.moonLong = L_Moon / 0.017453292519943295;
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public void sunDecRA(final double T) {
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482062 * SL;
        final double Z = 0.397777156 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        this.sunDecGlobal = 57.29577951308232 * Math.atan(Z / R);
        this.sunRAGlobal = 7.639437268410976 * Math.atan(Y / (X + R));
        if (this.sunRAGlobal < 0.0) {
            this.sunRAGlobal += 24.0;
        }
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11)));
    }
    
    public int[][] doLat_Long() {
        return this.LongLat;
    }
    
    public void doLatLong() {
        final double currentJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        if (Math.abs(currentJD - this.jdLastNew) > 2.0) {
            final String str = "Next New Moon on " + this.calDat(3, this.jdNextNew) + " " + this.monthArray[this.calDat(2, this.jdNextNew)] + " " + this.calDat(1, this.jdNextNew);
            final Dialog alert = new alert(str);
            alert.resize(300, 250);
            alert.show();
            this.isSun = true;
            this.repaint();
        }
        else {
            this.setCursor(new Cursor(3));
            final MoonDistance myMoonDistance = new MoonDistance(this.date, this.month + 1, this.year, 12.0);
            final double moonDistance = myMoonDistance.compute();
            this.drawFirst = true;
            final double jd0 = this.jdLastNew + 0.5;
            for (int i = 0; i < 180; ++i) {
                this.longi = -180 + 2 * i;
                for (int j = 10; j <= 80; ++j) {
                    this.lati = 90 - 2 * j;
                    int min = 0;
                    while (min <= 720) {
                        final double smJD = jd0 + min / 1440.0;
                        final double smT = (smJD - 2451545.0) / 36525.0;
                        double moonElev = this.moon_Elev(smJD, this.lati, this.longi);
                        moonElev -= this.comp.moonParal(moonElev, moonDistance);
                        moonElev += this.refract(moonElev) / 60.0;
                        this.sunDecRA(smT);
                        final double sunElev = this.sun_elev(smJD, this.lati, -this.longi, this.sunDecGlobal, this.sunRAGlobal);
                        if (sunElev < -5.0 && moonElev > 2.5) {
                            this.LongLat[i][j] = 1 + (int)(min / 180.0);
                            if (moonElev - sunElev < 8.0) {
                                this.LongLat[i][j] = 5;
                                break;
                            }
                            break;
                        }
                        else {
                            ++min;
                        }
                    }
                }
            }
        }
        this.setCursor(new Cursor(0));
    }
    
    public void paint(final Graphics g) {
        this.setCursor(new Cursor(3));
        g.setColor(Color.black);
        this.moonAgeStr = "Moon Age: " + this.comp.DaysHoursMinutes(this.currentJD - this.jdLastNew);
        if (this.currentJD - this.jdLastNew < 2.0 || this.currentJD - this.jdLastNew > 27.5) {
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.black);
        }
        g.drawString(this.moonAgeStr, 490, 595);
        g.drawImage(this.myMap, 30, 460, this);
        if (this.alt) {
            final double tau = this.GHA;
            final int xm = 256;
            final double D = 0.4;
            for (int i = -180; i <= 180; ++i) {
                int t = (int)Math.round(2.0 * (i - tau));
                if (t > 360) {
                    t -= 720;
                }
                if (t < -360) {
                    t += 720;
                }
                final int T = (int)Math.round(t / 1.593);
                for (int b = -90; b <= 90; ++b) {
                    final double sinH = Math.sin(0.017453292519943295 * b) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * b) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * i);
                    final double H = Math.asin(sinH) / 0.017453292519943295;
                    final int y1 = this.y0 - (int)Math.round(1.244 * b);
                    if (Math.abs(H - 10.0) < D) {
                        g.setColor(Color.darkGray);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("10°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 20.0) < D) {
                        g.setColor(Color.blue);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("20°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 30.0) < D) {
                        g.setColor(Color.cyan);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("30°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 40.0) < D) {
                        g.setColor(Color.orange);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("40°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 50.0) < D) {
                        g.setColor(Color.pink);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("50°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 60.0) < D) {
                        g.setColor(Color.green);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("60°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 70.0) < D) {
                        g.setColor(Color.magenta);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("70°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H - 80.0) < D) {
                        g.setColor(Color.red);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("80°", xm + T - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 10.0) < D) {
                        g.setColor(Color.darkGray);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-10°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 20.0) < D) {
                        g.setColor(Color.blue);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-20°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 30.0) < D) {
                        g.setColor(Color.cyan);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-30°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 40.0) < D) {
                        g.setColor(Color.orange);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-40°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 50.0) < D) {
                        g.setColor(Color.pink);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-50°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 60.0) < D) {
                        g.setColor(Color.green);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-60°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 70.0) < D) {
                        g.setColor(Color.magenta);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-70°", xm + T + 226 - 3, y1);
                        }
                    }
                    else if (Math.abs(H + 80.0) < D) {
                        g.setColor(Color.red);
                        g.drawOval(xm + T, y1, 0, 0);
                        if (b == i) {
                            g.drawString("-80°", xm + T + 226 - 3, y1);
                        }
                    }
                }
            }
        }
        if (this.drawFirst) {
            final int paintCounter = 0;
            final Color dark = new Color(15, 15, 15);
            final String ns = "N";
            final String ew = "E";
            for (int j = 0; j <= 180; ++j) {
                for (int k = 10; k <= 80; ++k) {
                    if (this.LongLat[j][k] > 0) {
                        final int X = 30 + this.x0 + (int)Math.round((-180 + j * 2) * 1.2555555555555555);
                        final int Y = this.y0 - (int)Math.round((90 - 2 * k) * 1.2444444444444445);
                        if (this.LongLat[j][k] == 1) {
                            g.setColor(Color.red);
                        }
                        else if (this.LongLat[j][k] == 2) {
                            g.setColor(Color.magenta);
                        }
                        else if (this.LongLat[j][k] == 3) {
                            g.setColor(Color.blue);
                        }
                        else if (this.LongLat[j][k] == 4) {
                            g.setColor(Color.green);
                        }
                        g.drawOval(X, Y, 1, 1);
                        if (this.LongLat[j][k] == 5) {
                            g.setColor(dark);
                            g.drawOval(X, Y, 1, 1);
                        }
                    }
                }
            }
        }
        g.setColor(Color.black);
        if (this.DayNightOK) {
            g.setColor(Color.darkGray);
            for (int l = 0; l < 451; ++l) {
                if (l % 3 == 0) {
                    int a;
                    if (this.dec > 0.0) {
                        a = this.y0 + 111;
                    }
                    else {
                        a = this.y0 - 111;
                    }
                    final double LL = -180.0 + this.GHA + l / 1.2555555555555555;
                    final int yy = (int)Math.round(1.2444444444444445 * this.comp.computeLat(LL, this.dec));
                    g.drawLine(30 + l, this.y0 - yy, 30 + l, a);
                }
            }
        }
        int x = 30;
        g.setColor(Color.gray);
        g.drawLine(30, this.y0, 30 + 2 * this.x0 - 2, this.y0);
        g.drawLine(30 + this.x0, 460, 30 + this.x0, 684);
        final int yy2 = (int)Math.round(this.y0 - 29.244444444444444);
        final int yy3 = (int)Math.round(this.y0 + 29.244444444444444);
        int y2 = (int)Math.round(654.7555555555556);
        int yy = (int)Math.round(489.24444444444447);
        for (int m = 0; m < 75; ++m) {
            g.drawLine(x, yy2, x + 2, yy2);
            g.drawLine(x, yy3, x + 2, yy3);
            g.drawLine(x, y2, x + 2, y2);
            g.drawLine(x, yy, x + 2, yy);
            x += 6;
        }
        if (this.isMoon) {
            g.setColor(Color.cyan);
            final Moon myMoon = new Moon(this.date, this.month, this.year, this.STD, this.latitude, this.longitude, this.locOffset);
            double moon_GSW = myMoon.moonGSW();
            final double moon_Dec = myMoon.moonDEC();
            if (moon_GSW < 0.0) {
                moon_GSW += 360.0;
            }
            for (int i2 = 0; i2 < 451; ++i2) {
                final double LL = -180.0 + moon_GSW + i2 / 1.2555555555555555;
                yy = (int)Math.round(1.2444444444444445 * this.comp.computeLat(LL, moon_Dec));
                if (moon_Dec < 0.0) {
                    for (int j2 = 0; this.y0 - yy + j2 > this.y0 - 112; --j2) {
                        final int n = this.y0 - yy + j2;
                        if (n % 3 == 0) {
                            g.drawLine(30 + i2, n, 30 + i2, n);
                        }
                    }
                }
                else {
                    for (int j2 = 0; this.y0 - yy + j2 < this.y0 + 112; ++j2) {
                        final int n = this.y0 - yy + j2;
                        if (n % 3 == 0) {
                            g.drawLine(30 + i2, n, 30 + i2, n);
                        }
                    }
                }
            }
        }
        g.setColor(Color.red);
        if (!this.drawFirst) {
            final double hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
            if (hoehe >= 0.0) {
                double azimuth = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, hoehe);
                azimuth -= 180.0;
                final double gnomon = 50.0 / Math.tan(0.017453292519943295 * hoehe);
                this.xMouse = 30 + this.x0 + (int)Math.round(this.longitude * 113.0 / 90.0);
                this.yMouse = this.y0 - (int)Math.round(this.latitude * 56.0 / 45.0);
                final int yGnomon = (int)Math.round(gnomon * Math.cos(0.017453292519943295 * azimuth));
                final int xGnomon = (int)Math.round(gnomon * Math.sin(0.017453292519943295 * azimuth));
                final double steig = yGnomon / xGnomon;
                if (this.yMouse - yGnomon > 460 && xGnomon + this.xMouse < 482) {
                    g.drawLine(this.xMouse, this.yMouse, this.xMouse + xGnomon, this.yMouse - yGnomon);
                }
                if (this.yMouse - yGnomon < 460 && xGnomon + this.xMouse < 482) {
                    final int xPart = (int)((this.yMouse - 460) / steig);
                    g.drawLine(this.xMouse, this.yMouse, this.xMouse + xPart, 460);
                }
                if (this.yMouse - yGnomon > 460 && xGnomon + this.xMouse > 482) {
                    final int yPart = (int)((482 - this.xMouse) * steig);
                    g.drawLine(this.xMouse, this.yMouse, 482, this.yMouse - yPart);
                }
                if (this.yMouse - yGnomon < 460 && xGnomon + this.xMouse > 482) {
                    final double steigEcke = (this.yMouse - 460) / (482 - this.xMouse);
                    if (steig >= steigEcke) {
                        final int xPart = (int)((this.yMouse - 460) / steig);
                        g.drawLine(this.xMouse, this.yMouse, this.xMouse + xPart, 460);
                    }
                    else {
                        final int yPart = (int)((482 - this.xMouse) * steig);
                        g.drawLine(this.xMouse, this.yMouse, 482, this.yMouse - yPart);
                    }
                }
            }
        }
        g.clearRect(30, 684, 452, 30);
        g.clearRect(1, 460, 29, 502);
        g.setColor(Color.red);
        g.drawRect(30, 460, 451, 223);
        String str = "System Clock: UT ";
        final Date browserDate = new Date();
        String hm = " h";
        final int browserOffset = -browserDate.getTimezoneOffset();
        if (Math.abs(browserOffset) > 15) {
            hm = " min";
        }
        if (browserOffset > 0) {
            str = String.valueOf(str) + "+";
        }
        g.drawString(String.valueOf(str) + browserOffset + hm, 30, 697);
        g.setColor(Color.red);
        x = this.x0 + (int)Math.round(1.2555555555555555 * this.longitude);
        y2 = this.y0 - (int)Math.round(1.2444444444444445 * this.latitude);
        g.fillOval(30 + x - 3, y2 - 3, 6, 6);
        g.setColor(Color.yellow);
        g.drawLine(30 + x, y2 - 4, 30 + x, y2 + 4);
        g.drawLine(30 + x - 4, y2, 30 + x + 4, y2);
        this.setCursor(new Cursor(0));
    }
}
