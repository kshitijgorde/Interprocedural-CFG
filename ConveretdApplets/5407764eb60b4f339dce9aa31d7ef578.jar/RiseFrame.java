import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class RiseFrame extends Frame
{
    final double K = 0.017453292519943295;
    char deg;
    double AU;
    double latitude;
    double longitude;
    double sunRiseJD;
    SunRiseSet SR;
    theMoon moon;
    compute comp;
    int SM;
    String locStr;
    String datStr;
    double declin;
    double angle;
    boolean DemoOnline;
    int RS;
    String rsStr;
    int locOffset;
    
    public RiseFrame(final int offset, final int rs, final int sm, final String loc, final String datString, final double jd, final double LAT, final double LONG, final double dec, final double Angle, final boolean DO) {
        this.deg = '°';
        this.AU = 1.4959787E8;
        this.latitude = LAT;
        this.longitude = LONG;
        this.sunRiseJD = jd;
        this.locStr = loc;
        this.datStr = datString;
        this.declin = dec;
        this.angle = Angle;
        this.SM = sm;
        this.RS = rs;
        this.locOffset = offset;
        this.DemoOnline = DO;
        String str;
        if (this.SM == 1) {
            str = "Sun ";
        }
        else {
            str = "Moon ";
        }
        if (this.RS == 1) {
            this.rsStr = "Rise ";
        }
        else {
            this.rsStr = "Set ";
        }
        this.setTitle(String.valueOf(str) + this.rsStr);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public int caldat(final int what, final double jd) {
        final int Z = (int)(jd + 0.5);
        final double F = this.frac(jd + 0.5);
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
        final double hour = 24.0 * this.frac(DAY);
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month - 1;
        }
        if (what == 4) {
            return year;
        }
        if (what == 3) {
            return (int)hour;
        }
        if (what == 5) {
            return (int)Math.round(60.0 * this.frac(hour));
        }
        return 0;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    public String makeHMString(final double time) {
        int h = (int)time;
        int m = (int)Math.round(60.0 * (time - h));
        if (m == 60) {
            m = 0;
            ++h;
        }
        String hStr;
        if (h < 10) {
            hStr = "0" + h;
        }
        else {
            hStr = String.valueOf(h);
        }
        String minStr;
        if (m < 10) {
            minStr = ":0" + m;
        }
        else {
            minStr = ":" + m;
        }
        return String.valueOf(hStr) + minStr;
    }
    
    double refract(final double h) {
        final double K = 0.017453292519943295;
        return 1.02 / Math.tan(K * (h + 10.3 / (h + 5.11)));
    }
    
    public double moonParal(final double jd, final double h) {
        final double T = (jd - 2415020.0) / 36525.0;
        final double K = 0.017453292519943295;
        final double m = 296.104608 + 477000.0 * T + 198.849108 * T + 0.009192 * T * T;
        final double l = 270.434164 + 480960.0 * T + 307.883142 * T - 0.001133 * T * T;
        final double L = 279.696678 + 36000.0 * T + 0.768925 * T + 3.03E-4 * T * T;
        double P = 3423.0 + 187.0 * Math.cos(0.017453292519943295 * m) + 10.0 * Math.cos(0.03490658503988659 * m) + 34.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) - m)) + 28.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L))) + 3.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) + m));
        P /= 3600.0;
        final double r = 6378.14 / Math.sin(0.017453292519943295 * P);
        final double horParal = 8.794 / (r / this.AU);
        final double paral = Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        return Math.asin(paral) / 0.017453292519943295;
    }
    
    double EPS(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        final double t = T / 10.0;
        final double L = 280.4664567 + 360007.6982779 * t + 0.03032028 * t * t + Math.pow(t, 3.0) / 49931.0 - Math.pow(t, 4.0) / 15299.0 + Math.pow(t, 5.0) / 1988000.0;
        final double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * L) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        final double eps2 = eps0 + deltaEps;
        return eps2 + 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
    }
    
    public void paint(final Graphics g) {
        final int x0 = 40;
        final int y0 = 465;
        final int fx = 70;
        final int fy = 70;
        final int DX = fx * 11;
        final int DY = fy * 6;
        int pm;
        int X;
        if (this.RS == 1) {
            pm = 1;
            X = 0;
        }
        else {
            pm = -1;
            X = 650;
        }
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        String riseStr = this.makeHMString(this.caldat(3, this.sunRiseJD + this.locOffset / 24.0) + this.caldat(5, this.sunRiseJD + this.locOffset / 24.0) / 60.0);
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.datStr) + ",  eps= " + Math.round(1000.0 * this.EPS(this.sunRiseJD)) / 1000.0 + this.deg, x0, 40);
        String str = ",  UT ";
        if (this.locOffset < 0) {
            str = String.valueOf(str) + "-";
        }
        else {
            str = String.valueOf(str) + "+";
        }
        str = String.valueOf(str) + " " + Math.abs(this.locOffset) + " h,  ";
        g.drawString(String.valueOf(this.locStr) + str + "Horizon at " + this.angle + this.deg, x0, 53);
        if (this.SM == 1) {
            this.comp = new compute(this.sunRiseJD, this.latitude, this.longitude, 0);
            final double dec = this.comp.sunDecRA(1, this.sunRiseJD);
            double pa = Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * dec);
            pa = Math.acos(pa) / 0.017453292519943295;
            str = ",  parallactic angle " + Math.round(10.0 * pa) / 10.0 + this.deg;
            final EarthCompute earth = new EarthCompute(this.sunRiseJD);
            final double diam = 1919.26 * this.AU / (3660.0 * this.AU * earth.R());
            final double dTh = 240.0 * diam / Math.sqrt(Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * dec) - Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.latitude));
            str = String.valueOf(str) + ",  duration " + Math.round(10.0 * dTh) / 10.0 + " s";
        }
        else {
            this.moon = new theMoon(this.sunRiseJD, this.latitude, this.longitude);
            final double moonDec = this.moon.declin();
            double pa = Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * moonDec);
            pa = Math.acos(pa) / 0.017453292519943295;
            str = ",  parallactic angle " + Math.round(10.0 * pa) / 10.0 + this.deg;
            final MoonDistance md = new MoonDistance(this.sunRiseJD);
            final double r = 3.584734E8 / (60.0 * md.compute());
            final double diam = 2.0 * r;
            final double dTh = 240.0 * (diam / 60.0) / Math.sqrt(Math.cos(0.017453292519943295 * moonDec) * Math.cos(0.017453292519943295 * moonDec) - Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.latitude));
            str = String.valueOf(str) + ",  duration " + Math.round(10.0 * dTh) / 10.0 + " s";
        }
        g.drawString(String.valueOf(this.rsStr) + riseStr + str, x0, 66);
        double azRise;
        double hRise;
        double az0;
        if (this.SM == 1) {
            final EarthCompute earth2 = new EarthCompute(this.sunRiseJD);
            final double diam2 = 1919.26 * this.AU / (3660.0 * this.AU * earth2.R());
            this.comp = new compute(this.sunRiseJD, this.latitude, this.longitude, 0);
            azRise = this.comp.azimuth();
            hRise = this.comp.elev();
            az0 = (int)azRise;
        }
        else {
            this.moon = new theMoon(this.sunRiseJD, this.latitude, this.longitude);
            azRise = this.moon.azim();
            hRise = this.moon.elev();
            hRise -= this.moonParal(this.sunRiseJD, hRise);
            hRise += this.refract(hRise) / 60.0;
            az0 = (int)azRise;
        }
        g.setColor(Color.lightGray);
        int y2 = y0 + Math.round(fy);
        for (int i = -2; i <= 22; ++i) {
            final int x2 = x0 + (int)Math.round(pm * fx * 0.5 * i) + X;
            if (x2 > x0) {
                if (i % 2 == 0) {
                    g.setColor(Color.black);
                    g.drawString(new StringBuffer().append((int)(az0 + pm * i * 0.5)).append(this.deg).toString(), x2 - 5, y0 + fy + 15);
                    g.setColor(Color.gray);
                    g.drawLine(x2, y2, x2, y2 - DY);
                }
                else {
                    g.setColor(Color.lightGray);
                    g.drawLine(x2, y2, x2, y2 - DY);
                }
            }
        }
        for (int j = -2; j < 11; ++j) {
            y2 = y0 - (int)Math.round(fy * j * 0.5);
            if (j % 2 == 0) {
                g.setColor(Color.gray);
                g.drawLine(x0, y2, x0 + DX, y2);
                g.setColor(Color.black);
                g.drawString(new StringBuffer().append((int)(j * 0.5 + this.angle)).append(this.deg).toString(), x0 - 20, y2 + 5);
            }
            else {
                g.setColor(Color.lightGray);
                g.drawLine(x0, y2, x0 + DX, y2);
            }
        }
        double r;
        int R;
        if (this.SM == 1) {
            r = fx * 15.5 / 60.0;
            R = (int)Math.round(r);
        }
        else {
            final MoonDistance md2 = new MoonDistance(this.sunRiseJD);
            r = 3.584734E8 / (60.0 * md2.compute());
            R = (int)Math.round(fx * r / 60.0);
        }
        g.setColor(Color.red);
        y2 = y0;
        g.drawLine(x0, y2, x0 + DX, y2);
        int x2;
        if (this.RS == 1) {
            x2 = x0 + (int)Math.round(fx * (azRise - az0));
            y2 = y0 - (int)Math.round(fy * (hRise - this.angle));
        }
        else {
            x2 = x0 + (int)Math.round(fx * (azRise - az0)) + X;
            y2 = y0 - (int)Math.round(fy * (hRise - this.angle));
        }
        g.drawOval(x2 - R, y2 - R, 2 * R, 2 * R);
        g.drawLine(x2, y2, x2, y2 - R);
        if (this.SM == 1) {
            for (int k = -2; k <= 90; ++k) {
                final double jd = this.sunRiseJD + pm * k / 1440.0;
                this.comp = new compute(jd, this.latitude, this.longitude, 0);
                final double elev = this.comp.elev();
                final double az2 = this.comp.azimuth();
                g.setColor(Color.red);
                x2 = x0 + (int)Math.round(fx * (az2 - az0)) + X;
                y2 = y0 - (int)Math.round(fy * (elev - this.angle));
                if (k == 0) {
                    g.drawString(String.valueOf(this.rsStr) + riseStr + ",  Elev. " + Math.round(100.0 * elev) / 100.0 + this.deg + ",  Az. " + Math.round(100.0 * az2) / 100.0 + this.deg + ",  Decl. " + Math.round(100.0 * this.declin) / 100.0 + this.deg + ",  Refr. " + Math.round(this.refract(elev)) + "'", x0, 79);
                }
                if (k == 2) {
                    g.setColor(Color.blue);
                    g.drawOval(x2 - R, y2 - R, 2 * R, 2 * R);
                    g.drawLine(x2, y2, x2, y2 - R);
                    riseStr = this.makeHMString(this.caldat(3, this.sunRiseJD + this.locOffset / 24.0 + pm * 2.0 / 1440.0) + this.caldat(5, this.sunRiseJD + this.locOffset / 24.0 + pm * 2.0 / 1440.0) / 60.0);
                    g.drawString("     " + riseStr + ",  Elev. " + Math.round(100.0 * elev) / 100.0 + this.deg + ",  Az. " + Math.round(100.0 * az2) / 100.0 + this.deg, x0, 92);
                }
                if (k == 10) {
                    g.setColor(Color.blue);
                    g.drawOval(x2 - R, y2 - R, 2 * R, 2 * R);
                    g.drawLine(x2, y2, x2, y2 - R);
                    riseStr = this.makeHMString(this.caldat(3, this.sunRiseJD + this.locOffset / 24.0 + pm * 10.0 / 1440.0) + this.caldat(5, this.sunRiseJD + this.locOffset / 24.0 + pm * 10.0 / 1440.0) / 60.0);
                    g.drawString("     " + riseStr + ",  Elev. " + Math.round(100.0 * elev) / 100.0 + this.deg + ",  Az. " + Math.round(100.0 * az2) / 100.0 + this.deg, x0, 105);
                }
                g.setColor(Color.red);
                final double jd2 = jd + pm / 1440.0;
                this.comp = new compute(jd2, this.latitude, this.longitude, 0);
                final double elev2 = this.comp.elev();
                final double az3 = this.comp.azimuth();
                final int x3 = x0 + (int)Math.round(fx * (az3 - az0)) + X;
                final int y3 = y0 - (int)Math.round(fy * (elev2 - this.angle));
                final double winkel = 90.0 - Math.atan((elev2 - elev) / (az3 - az2)) / 0.017453292519943295;
                final int dy = (int)Math.round(r * Math.sin(0.017453292519943295 * winkel));
                final int dx = (int)Math.round(r * Math.cos(0.017453292519943295 * winkel));
                g.drawLine(x2 + dx, y2 + dy, x3 + dx, y3 + dy);
                g.drawLine(x2 - dx, y2 - dy, x3 - dx, y3 - dy);
                if (y2 < 150) {
                    break;
                }
            }
        }
        else {
            System.out.println(this.angle);
            for (int k = -2; k <= 60; ++k) {
                final double jd = this.sunRiseJD + pm * k / 1440.0;
                this.moon = new theMoon(jd, this.latitude, this.longitude);
                double elev = this.moon.elev();
                elev -= this.moonParal(jd, elev);
                elev += this.refract(elev) / 60.0;
                final double az2 = this.moon.azim();
                if (k == 0) {
                    g.drawString(String.valueOf(this.rsStr) + riseStr + ",  Elev. " + Math.round(100.0 * elev) / 100.0 + this.deg + ",  Az. " + Math.round(100.0 * az2) / 100.0 + this.deg + ",  Decl. " + Math.round(100.0 * this.declin) / 100.0 + this.deg + ",  HoPa. " + Math.round(60.0 * this.moonParal(jd, elev)) + "'" + ",  Refr. " + Math.round(10.0 * this.refract(elev)) / 10.0 + "'" + ",  Rad. " + Math.round(10.0 * r) / 10.0 + "'", x0, 79);
                }
                x2 = x0 + (int)Math.round(fx * (az2 - az0)) + X;
                y2 = y0 - (int)Math.round(fy * (elev - this.angle));
                if (k == 2) {
                    g.setColor(Color.blue);
                    g.drawOval(x2 - R, y2 - R, 2 * R, 2 * R);
                    g.drawLine(x2, y2, x2, y2 - R);
                    riseStr = this.makeHMString(this.caldat(3, this.sunRiseJD + this.locOffset / 24.0 + pm * 2.0 / 1440.0) + this.caldat(5, this.sunRiseJD + this.locOffset / 24.0 + pm * 2.0 / 1440.0) / 60.0);
                    g.drawString("    " + riseStr + ",  Elev. " + Math.round(100.0 * elev) / 100.0 + this.deg + ",  Az. " + Math.round(100.0 * az2) / 100.0 + this.deg, x0, 92);
                }
                if (k == 10) {
                    g.setColor(Color.blue);
                    g.drawOval(x2 - R, y2 - R, 2 * R, 2 * R);
                    g.drawLine(x2, y2, x2, y2 - R);
                    riseStr = this.makeHMString(this.caldat(3, this.sunRiseJD + this.locOffset / 24.0 + pm * 10.0 / 1440.0) + this.caldat(5, this.sunRiseJD + this.locOffset / 24.0 + pm * 10.0 / 1440.0) / 60.0);
                    g.drawString("    " + riseStr + ",  Elev. " + Math.round(100.0 * elev) / 100.0 + this.deg + ",  Az. " + Math.round(100.0 * az2) / 100.0 + this.deg, x0, 105);
                }
                g.setColor(Color.red);
                final double jd2 = jd + pm / 1440.0;
                this.moon = new theMoon(jd2, this.latitude, this.longitude);
                double elev2 = this.moon.elev();
                elev2 -= this.moonParal(jd, elev2);
                elev2 += this.refract(elev2) / 60.0;
                final double az3 = this.moon.azim();
                final int x3 = x0 + (int)Math.round(fx * (az3 - az0)) + X;
                final int y3 = y0 - (int)Math.round(fy * (elev2 - this.angle));
                final double winkel = 90.0 - Math.atan((elev2 - elev) / (az3 - az2)) / 0.017453292519943295;
                final int dy = (int)Math.round(R * Math.sin(0.017453292519943295 * winkel));
                final int dx = (int)Math.round(R * Math.cos(0.017453292519943295 * winkel));
                g.drawLine(x2 + dx, y2 + dy, x3 + dx, y3 + dy);
                g.drawLine(x2 - dx, y2 - dy, x3 - dx, y3 - dy);
                if (y2 < 150) {
                    break;
                }
            }
        }
        if (this.DemoOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("o n l i n e", 350, 300);
        }
        g.drawString("(c) J. Giesen  --  www.GeoAstro.de", this.size().width - 250, y0 - 10);
    }
}
