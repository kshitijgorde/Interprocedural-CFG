import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.TextField;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sunpol17 extends Applet
{
    Date dat;
    compute comp;
    Thread myThread;
    TextField latField;
    TextField longField;
    double latitude;
    double longitude;
    String latStr;
    String longStr;
    Double latDouble;
    Double longDouble;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    int seconds;
    Checkbox box;
    Checkbox anaBox;
    BorderLayout bl;
    boolean isZoom;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    String versStr;
    boolean console;
    int locOffset;
    String usrStr;
    boolean demo;
    String userString;
    boolean online;
    boolean licensed;
    public String email;
    public String param;
    public String wwwStr;
    public String str;
    int[] analemmaX;
    int[] analemmaY;
    boolean analemmaOK;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 7000;
    }
    
    public void init() {
        this.versStr = "SunPol 1.7";
        this.setBackground(Color.white);
        this.userString = this.getParameter("email");
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.dat = new Date();
        this.date = this.dat.getDate();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.seconds = this.dat.getSeconds();
        this.latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = this.latDouble;
        this.longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = this.longDouble;
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.locOffset);
        this.add("North", this.box = new Checkbox("zoom"));
        this.box.setState(true);
        this.add(this.anaBox = new Checkbox("Analemma"));
        this.anaBox.setState(true);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        System.out.println(this.formula(this.email, this.email.length()));
        final int xy = this.formula(this.wwwStr, 27);
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 21) == this.formula("http://www.j-giesen.de", 21) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || (Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) && Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) - 10)) {
                ok = false;
            }
            else {
                ok = true;
                this.licensed = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        if (m == 1) {
            n = 28;
            if (y % 4 == 0) {
                n = 29;
            }
            if (y % 100 == 0) {
                n = 28;
            }
            if (y % 400 == 0) {
                n = 29;
            }
        }
        return n;
    }
    
    double Jul_Date(final int date, int month, int year, final double ut) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        double B;
        if (A <= 1.58210041E7) {
            B = -2 + (year + 4716) / 4 - 1179;
        }
        else {
            B = year / 400 - year / 100 + year / 4;
        }
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + ut / 24.0;
        return MJD + 2400000.5;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    double sunDecRA(final int what, final double JD) {
        final double PI2 = 6.283185307179586;
        final double cos_eps = 0.917482;
        final double sin_eps = 0.397778;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482 * SL;
        final double Z = 0.397778 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan(Z / R);
        double RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (RA < 0.0) {
            RA += 24.0;
        }
        if (what == 1) {
            return DEC;
        }
        return RA;
    }
    
    double sun_GHA(final int date, final int month, final int year, final double ut, final double RA) {
        final double JD = this.Jul_Date(date, month, year, ut);
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
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
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.box) {
                if (this.box.getState()) {
                    this.isZoom = false;
                }
                else {
                    this.isZoom = true;
                }
                this.repaint();
                return true;
            }
            if (event.target == this.anaBox) {
                this.analemmaOK ^= true;
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        final int F = 9;
        final int xL = 16;
        final int h = 12;
        String str = "";
        final int margin = 20;
        final double K = 0.017453292519943295;
        String elevStr = "";
        String azimStr = "";
        final int rSun = 2;
        final int rCurrentSun = 8;
        g.clipRect(0, 0, this.size().width, this.size().height);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        int xx = this.size().width;
        int yy = this.size().height;
        if (xx > yy) {
            xx = yy;
        }
        else {
            yy = xx;
        }
        final int xm = xx / 2;
        final int ym = yy / 2;
        Font f = new Font("Courier", 0, 10);
        if (this.demo) {
            f = g.getFont();
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("D E M O", 170, 150);
            g.setFont(f);
        }
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.locOffset);
        int r90;
        int circleNum;
        int R;
        if (!this.isZoom) {
            r90 = xm - 2 * margin;
            circleNum = 9;
            R = r90;
        }
        else {
            r90 = xm / 2 - margin;
            circleNum = 18;
            R = 2 * r90;
        }
        if (this.isZoom) {
            g.setColor(Color.lightGray);
            g.fillOval(xm - R - 1, ym - R - 1, 2 * R + 2, 2 * R + 2);
            g.setColor(Color.white);
            g.fillOval(xm - r90 - 1, ym - r90 - 1, 2 * r90 + 2, 2 * r90 + 2);
            g.setColor(Color.black);
            g.drawOval(xm - R, ym - R, 2 * R, 2 * R);
            g.drawOval(xm - R - 1, ym - R - 1, 2 * R + 2, 2 * R + 2);
        }
        else {
            g.setColor(Color.black);
            g.drawOval(xm - r90, ym - r90, 2 * r90, 2 * r90);
            g.drawOval(xm - r90 - 1, ym - r90 - 1, 2 * r90 + 2, 2 * r90 + 2);
        }
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        if (this.isZoom) {
            g.drawString("N", xm - 3, ym - R - 5);
            g.drawString("S", xm - 3, ym + R - 5);
            g.drawString("E", xm + R - 10, ym);
            g.drawString("W", xm - R + 5, ym);
        }
        else {
            g.drawString("N", xm - 3, ym - r90 - 5);
            g.drawString("S", xm - 3, ym + r90 - 5);
            g.drawString("E", xm + R - 10, ym);
            g.drawString("W", xm - R + 5, ym);
        }
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.gray);
        for (int i = 1; i < circleNum; ++i) {
            final int r91 = (int)Math.round(i * r90 / 9.0);
            g.drawOval(xm - r91, ym - r91, 2 * r91, 2 * r91);
            if (i < 9) {
                g.drawString(String.valueOf(90 - i * 10), xm, ym - r91);
            }
            if (i > 9 && i % 3 == 0) {
                g.drawString(String.valueOf(90 - i * 10), xm, ym - r91);
            }
        }
        if (this.isZoom) {
            final int r91 = (int)Math.round(9 * r90 / 9.0);
            g.setColor(Color.red);
            g.drawOval(xm - r91, ym - r91, 2 * r91, 2 * r91);
            g.setColor(Color.gray);
        }
        for (int j = 0; j < 12; ++j) {
            final int x1 = xm + (int)Math.round(R * Math.sin(j * 30 * 0.017453292519943295));
            final int y1 = ym - (int)Math.round(R * Math.cos(j * 30 * 0.017453292519943295));
            g.drawLine(xm, ym, x1, y1);
        }
        for (int k = 1; k < 5; ++k) {
            final int x1 = xm + (int)Math.round(R * Math.sin(k * 30 * 0.017453292519943295));
            final int y1 = ym - (int)Math.round(R * Math.cos(k * 30 * 0.017453292519943295));
            g.drawString(String.valueOf(k * 30), x1 + 7, y1 + 3);
        }
        for (int l = 8; l < 12; ++l) {
            final int x1 = xm + (int)Math.round(R * Math.sin(l * 30 * 0.017453292519943295));
            final int y1 = ym - (int)Math.round(R * Math.cos(l * 30 * 0.017453292519943295));
            g.drawString(String.valueOf(l * 30), x1 - 25, y1);
        }
        int m = 5;
        int x1 = xm + (int)Math.round(R * Math.sin(m * 30 * 0.017453292519943295));
        int y1 = ym - (int)Math.round(R * Math.cos(m * 30 * 0.017453292519943295));
        g.drawString(String.valueOf(m * 30), x1 + 10, y1 + 5);
        m = 6;
        x1 = xm + (int)Math.round(R * Math.sin(m * 30 * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(m * 30 * 0.017453292519943295));
        g.drawString(String.valueOf(m * 30), x1 - 10, y1 + 15);
        m = 7;
        x1 = xm + (int)Math.round(R * Math.sin(m * 30 * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(m * 30 * 0.017453292519943295));
        g.drawString(String.valueOf(m * 30), x1 - 25, y1 + 5);
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(String.valueOf(this.homeString) + "  --  " + this.dat.toString(), 20, 35);
        g.setColor(Color.blue);
        g.setFont(new Font("Helvetica", 0, 10));
        double elev = this.comp.elev();
        double azim = this.comp.azimuth();
        elevStr = String.valueOf(Math.round(100.0 * elev) / 100.0);
        azimStr = String.valueOf(azim);
        azimStr = azimStr.substring(0, azimStr.indexOf(".") + 2);
        g.drawString("Elev. = " + elevStr, xx - 160, 35);
        g.drawString("Azim. = " + azimStr, xx - 90, 35);
        str = "Lat.    = " + Math.abs(this.latitude);
        if (this.latitude > 0.0) {
            str = String.valueOf(str) + " N";
        }
        else {
            str = String.valueOf(str) + " S";
        }
        g.drawString(str, 20, 50);
        str = "Long.= " + Math.abs(this.longitude);
        if (this.longitude > 0.0) {
            str = String.valueOf(str) + " W";
        }
        else {
            str = String.valueOf(str) + " E";
        }
        g.drawString(str, 20, 65);
        x1 = xm + (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.sin(azim * 0.017453292519943295));
        y1 = ym - (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.cos(azim * 0.017453292519943295));
        g.setColor(Color.yellow);
        g.fillOval(x1 - rCurrentSun, y1 - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
        g.setColor(Color.red);
        g.drawOval(x1 - rCurrentSun, y1 - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
        g.setColor(Color.blue);
        g.setFont(new Font("Courier", 0, 10));
        double azRise = Math.sin(0.017453292519943295 * this.comp.declin());
        azRise /= Math.cos(0.017453292519943295 * this.latitude);
        azRise = Math.acos(azRise) / 0.017453292519943295;
        x1 = xm + (int)Math.round(R * Math.sin(azRise * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(azRise * 0.017453292519943295));
        g.setColor(Color.red);
        g.drawLine(xm, ym, x1, y1);
        g.drawString("rise", x1 + 10, y1 + 5);
        x1 = xm + (int)Math.round(R * Math.sin((360.0 - azRise) * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos((360.0 - azRise) * 0.017453292519943295));
        g.drawLine(xm, ym, x1, y1);
        g.drawString("set", x1 - 25, y1 + 5);
        g.setColor(Color.blue);
        final Date myDat = new Date();
        myDat.setDate(this.date);
        myDat.setMonth(this.month);
        myDat.setYear(this.year);
        for (int i2 = 0; i2 < 48; ++i2) {
            myDat.setHours(i2 / 2);
            myDat.setMinutes(i2 * 30 % 60);
            myDat.setSeconds(0);
            this.comp = new compute(myDat, this.latitude, this.longitude, this.locOffset);
            elev = this.comp.elev();
            azim = this.comp.azimuth();
            x1 = xm + (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.sin(azim * 0.017453292519943295));
            y1 = ym - (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.cos(azim * 0.017453292519943295));
            if (!this.isZoom) {
                if ((x1 - xm) * (x1 - xm) + (y1 - ym) * (y1 - ym) < r90 * r90) {
                    if (i2 % 2 == 0) {
                        g.drawOval(x1 - rSun, y1 - rSun, 2 * rSun, 2 * rSun);
                    }
                    else {
                        g.drawOval(x1 - 1, y1 - 1, 2, 2);
                    }
                    if (i2 % 2 == 0) {
                        g.drawString(String.valueOf(i2 / 2), x1 + 6, y1 + 6);
                    }
                }
            }
            else if (i2 % 2 == 0) {
                g.drawOval(x1 - rSun, y1 - rSun, 2 * rSun, 2 * rSun);
                g.drawString(String.valueOf(i2 / 2), x1 + 6, y1 + 6);
            }
        }
        if (this.analemmaOK) {
            g.setColor(Color.red);
            int k2 = 0;
            final double UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int d = 1; d <= this.daysInMonth(m2, this.year); ++d) {
                    final double jd = this.Jul_Date(d, m2 + 1, this.year + 1900, UT);
                    final double decAna = this.sunDecRA(1, jd);
                    final double ra = this.sunDecRA(2, jd);
                    double ghaAna = this.sun_GHA(d, m2 + 1, this.year + 1900, UT, ra);
                    ghaAna %= 360.0;
                    final double hAna = this.sun_elev(jd, this.latitude, this.longitude, decAna, ra);
                    final double azAna = this.comp.computeAzimut(decAna, this.latitude, this.longitude, ghaAna, hAna);
                    this.analemmaX[k2] = xm + (int)Math.round((90.0 - hAna) * r90 / 90.0 * Math.sin(azAna * 0.017453292519943295));
                    this.analemmaY[k2] = ym - (int)Math.round((90.0 - hAna) * r90 / 90.0 * Math.cos(azAna * 0.017453292519943295));
                    ++k2;
                }
            }
            for (int i3 = 0; i3 < k2 - 1; ++i3) {
                if ((this.analemmaX[i3] - xm) * (this.analemmaX[i3] - xm) + (this.analemmaY[i3] - ym) * (this.analemmaY[i3] - ym) < r90 * r90 || this.isZoom) {
                    g.drawLine(this.analemmaX[i3], this.analemmaY[i3], this.analemmaX[i3 + 1], this.analemmaY[i3 + 1]);
                }
            }
        }
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " --  © 1999-2007 Juergen Giesen -- www.GeoAstro.de", 20, yy - 5);
    }
    
    public sunpol17() {
        this.isZoom = false;
        this.console = true;
        this.demo = true;
        this.online = false;
        this.licensed = false;
        this.analemmaX = new int[370];
        this.analemmaY = new int[370];
        this.analemmaOK = true;
    }
}
