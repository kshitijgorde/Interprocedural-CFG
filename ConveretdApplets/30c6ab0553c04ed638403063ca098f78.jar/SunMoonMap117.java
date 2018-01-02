import java.awt.image.ImageObserver;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.util.Date;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SunMoonMap117 extends Applet
{
    String versStr;
    public Suncanvas myCan;
    public Compute comp;
    public Mapcanvas mymapcanvas;
    int xMouse;
    int yMouse;
    int xHorMouse;
    int yHorMouse;
    boolean clicked;
    final int mapOben = 460;
    final int mapRechts = 490;
    boolean demo;
    Image map;
    Image bild;
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    public double latitude;
    public double longitude;
    double GHA;
    double dec;
    double hoehe;
    double azimut;
    int locOffset;
    int browserOffset;
    public String locString;
    public String timeString;
    String[] dayArray;
    String[] monthArray;
    int xL;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    boolean online;
    boolean console;
    String usrStr;
    String userString;
    boolean analemmaOK;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 1220;
    }
    
    public void refresh() {
        this.dat = new Date();
        this.repaint();
    }
    
    public void init() {
        this.setBackground(Color.lightGray);
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.map = this.getImage(this.getDocumentBase(), "applet/bigmap.gif"), 0);
        try {
            tracker.waitForID(0);
        }
        catch (Exception ex) {}
        this.dat = new Date();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.seconds = this.dat.getSeconds();
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = this.browserOffset;
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = " " + this.timeString;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbl.setConstraints(this.myCan = new Suncanvas(this.dat, this.latitude, this.longitude, this.locOffset, this.versStr, this.userString, this.analemmaOK), gbc);
        this.add(this.myCan);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbl.setConstraints(this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset), gbc);
        this.add(this.mymapcanvas);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            this.usrStr = this.email;
            this.userString = this.email;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.usrStr = this.email;
                this.userString = this.email;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.analemmaOK ^= true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics g) {
        final int unten = 380;
        final Color c = this.getBackground();
        g.setColor(c);
        this.myCan = new Suncanvas(this.dat, this.latitude, this.longitude, this.locOffset, this.versStr, this.userString, this.analemmaOK);
        this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
        Font f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        this.mymapcanvas.update(g);
        this.myCan.update(g);
        if (this.online) {
            this.userString = "running online";
        }
        if (this.demo) {
            f = g.getFont();
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 50, 150);
            g.setFont(f);
        }
    }
    
    public SunMoonMap117() {
        this.versStr = "SunMoon Map 1.17";
        this.clicked = false;
        this.demo = true;
        this.dayArray = new String[7];
        this.monthArray = new String[12];
        this.xL = 0;
        this.online = false;
        this.console = false;
        this.usrStr = "demo";
        this.analemmaOK = true;
    }
    
    class Mapcanvas extends Canvas
    {
        public final int mapOben = 0;
        public final int mapRechts = 490;
        final int xL = 0;
        final double xFaktor = 1.2555555555555555;
        final double yFaktor = 1.2444444444444445;
        int x0;
        int y0;
        int left;
        int mapUnten;
        int mapRight;
        final double K = 0.017453292519943295;
        Image myMap;
        Date dat;
        int day;
        int year;
        int month;
        int date;
        int hours;
        int minutes;
        int seconds;
        int locOffset;
        int browserOffset;
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
        
        public Mapcanvas(final Image map, final Date myDate, final double myLat, final double myLong, final boolean myClick, final int mylocOffset) {
            this.x0 = (int)Math.round(226.0);
            this.y0 = (int)Math.round(112.0);
            this.left = 370;
            this.mapUnten = 224;
            this.mapRight = 482;
            this.xMouse = 0;
            this.yMouse = 0;
            this.myMap = map;
            this.dat = myDate;
            this.longitude = myLong;
            this.latitude = myLat;
            this.clicked = myClick;
            this.locOffset = mylocOffset;
            this.repaint();
        }
        
        public void paint(final Graphics g) {
            final String str = "";
            g.drawImage(this.myMap, 0, 0, this);
            this.comp = new Compute();
            this.date = this.dat.getDate();
            this.month = this.dat.getMonth();
            this.year = this.dat.getYear();
            this.hours = this.dat.getHours();
            this.minutes = this.dat.getMinutes();
            this.seconds = this.dat.getSeconds();
            final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
            this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, STD);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
            g.setColor(Color.black);
            int x = 180 - (int)Math.round(this.GHA);
            if (x < 0) {
                x += 360;
            }
            if (x > 360) {
                x -= 360;
            }
            int F;
            if (this.dec > 0.0) {
                F = 1;
            }
            else {
                F = -1;
            }
            g.setXORMode(Color.white);
            final int a = this.y0 + F * 112 - 1;
            final double b = -180.0 + this.GHA;
            for (int i = 0; i < 451; ++i) {
                final int LL = (int)Math.round(b + i / 1.2555555555555555);
                final int yy = (int)Math.round(1.2444444444444445 * this.comp.computeLat(LL, this.dec));
                g.drawLine(i, this.y0 - yy, i, a);
            }
            g.setPaintMode();
            int xx = 0;
            g.setColor(Color.gray);
            g.drawLine(0, this.y0, 2 * this.x0 - 2, this.y0);
            g.drawLine(this.x0, 0, this.x0, 224);
            final int yy2 = (int)Math.round(this.y0 - 29.244444444444444);
            final int yy3 = (int)Math.round(this.y0 + 29.244444444444444);
            final int y = (int)Math.round(194.75555555555556);
            final int yy = (int)Math.round(29.244444444444444);
            for (int j = 0; j < 75; ++j) {
                g.drawLine(xx, yy2, xx + 2, yy2);
                g.drawLine(xx, yy3, xx + 2, yy3);
                g.drawLine(xx, y, xx + 2, y);
                g.drawLine(xx, yy, xx + 2, yy);
                xx += 6;
            }
            g.setColor(Color.black);
            g.drawRect(0, 0, 450, 223);
        }
    }
    
    class Suncanvas extends Canvas
    {
        String str;
        final double K = 0.017453292519943295;
        final int y90 = 114;
        final int y0 = 326;
        final int mapOben = 0;
        final int mapRechts = 490;
        final int xLHor = 60;
        final int unten = 380;
        int Radius;
        int moonRadius;
        String s;
        int min;
        double STD;
        double equation;
        double diff;
        double hRise;
        double hSet;
        double hoehe1;
        Date dat;
        public Compute comp;
        int browserOffset;
        int locOffset;
        int year;
        int month;
        int date;
        int hours;
        int minutes;
        int seconds;
        double latitude;
        double longitude;
        double dec;
        double GHA;
        double hoehe;
        double azimut;
        String eqtStr;
        String versStr;
        String gmtStr;
        String declinStr;
        double moonH;
        double moonAz;
        int hMoon;
        int xMoon;
        int xMoonMap;
        double moon_GSW;
        double moon_Dec;
        double diffL;
        double diffMS;
        double xHalbAchse;
        double illFrac;
        double moonTerm;
        String usrString;
        Moon myMoon;
        MoonDistance myMoonDistance;
        double moonAge;
        double sunDistance;
        double moonDistance;
        double zWinkel;
        double q;
        double hourAngle;
        double ww;
        double DEC;
        int[] analemmaX;
        int[] analemmaY;
        boolean analemmaOK;
        
        public Suncanvas(final Date myDate, final double myLat, final double myLong, final int myLocOffset, final String vers, final String userString, final boolean analem) {
            this.s = "";
            this.xHalbAchse = 0.0;
            this.analemmaX = new int[370];
            this.analemmaY = new int[370];
            this.versStr = vers;
            this.usrString = userString;
            this.analemmaOK = analem;
            this.latitude = myLat;
            this.longitude = myLong;
            this.dat = myDate;
            this.hours = this.dat.getHours();
            this.date = this.dat.getDate();
            this.month = this.dat.getMonth();
            this.minutes = this.dat.getMinutes();
            this.seconds = this.dat.getSeconds();
            this.year = this.dat.getYear();
            this.browserOffset = this.dat.getTimezoneOffset();
            this.browserOffset = -this.browserOffset / 60;
            this.locOffset = myLocOffset;
            this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
            this.comp = new Compute();
            this.myMoon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
            this.moon_GSW = this.myMoon.moonGSW();
            this.moon_Dec = this.myMoon.moonDEC();
            if (this.moon_GSW < 0.0) {
                this.moon_GSW += 360.0;
            }
            if (this.moon_GSW < 0.0) {
                this.moon_GSW += 360.0;
            }
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
            this.equation = this.eot(this.date, this.month + 1, this.year + 1900, this.STD);
            this.diff = Math.abs(this.equation - (int)this.equation);
            this.min = (int)Math.round(this.diff * 60.0);
            if (this.min == 60) {
                this.min = 0;
                if (this.equation >= 0.0) {
                    ++this.equation;
                }
                else {
                    --this.equation;
                }
            }
            if (this.min > 9) {
                this.str = ":";
            }
            else {
                this.str = ":0";
            }
            this.eqtStr = (int)this.equation + this.str + this.min + " min";
            String decNS;
            if (this.DEC > 0.0) {
                decNS = " N";
            }
            else {
                decNS = " S";
            }
            this.diff = Math.abs(this.DEC) - (int)Math.abs(this.DEC);
            this.min = (int)Math.round(this.diff * 60.0);
            this.s = String.valueOf(this.min);
            if (this.min < 10) {
                this.s = "0" + this.s;
            }
            this.declinStr = (int)Math.abs(this.DEC) + "° " + this.s + "'" + decNS;
            this.diffMS = this.moon_GSW - this.GHA;
            this.diffL = this.moon_GSW - this.GHA;
            if (this.diffL < 0.0) {
                this.diffL += 360.0;
            }
            this.moonTerm = this.diffL;
            this.moonAge = (-this.diffL + 360.0) / 12.19075;
            this.sunDistance = this.myMoon.radius();
            double winkel = 1392000.0 / (this.sunDistance * 1.4959787E8);
            winkel = 180.0 * Math.atan(winkel) / 3.141592653589793;
            final double sunDiameter = 60.0 * winkel;
            this.Radius = (int)Math.round(20.0 * winkel);
            double moonDiameter = 7.169468E8 / this.moonDistance;
            final double horParal = 8.794 / (this.moonDistance / 1.4959787E8);
            moonDiameter *= 1.0 + Math.sin(0.017453292519943295 * this.moonH) * Math.sin(0.017453292519943295 * horParal / 3600.0);
            moonDiameter /= 60.0;
            this.moonRadius = (int)Math.round(20.0 * winkel);
            this.repaint();
        }
        
        public double sunL(final double T) {
            double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
            final double tau = T / 10.0;
            L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
            L %= 360.0;
            if (L < 0.0) {
                L += 360.0;
            }
            return L;
        }
        
        public double deltaPSI(final double T) {
            final double K = 0.017453292519943295;
            final double LS = this.sunL(T);
            double LM = 218.3165 + 481267.8813 * T;
            LM %= 360.0;
            if (LM < 0.0) {
                LM += 360.0;
            }
            final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
            double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
            deltaPsi /= 3600.0;
            return deltaPsi;
        }
        
        public double EPS(final double T) {
            final double K = 0.017453292519943295;
            final double LS = this.sunL(T);
            final double LM = 218.3165 + 481267.8813 * T;
            final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
            final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
            final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
            return eps0 + deltaEps;
        }
        
        public double eot(final int date, final int month, final int year, final double UT) {
            final double K = 0.017453292519943295;
            final double T = (this.comp.JD(date, month, year, UT) - 2451545.0) / 36525.0;
            final double eps = this.EPS(T);
            final double RA = this.RightAscension(T);
            final double LS = this.sunL(T);
            final double deltaPsi = this.deltaPSI(T);
            double E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
            if (E > 5.0) {
                E -= 360.0;
            }
            E *= 4.0;
            return E;
        }
        
        public double RightAscension(final double T) {
            final double K = 0.017453292519943295;
            final double L = this.sunL(T);
            double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
            M %= 360.0;
            if (M < 0.0) {
                M += 360.0;
            }
            double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M);
            C += (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M);
            C += 2.9E-4 * Math.sin(0.05235987755982989 * M);
            final double theta = L + C;
            double eps = this.EPS(T);
            eps += 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
            final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
            double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda));
            RA /= 0.017453292519943295;
            if (RA < 0.0) {
                RA += 360.0;
            }
            double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda));
            delta /= 0.017453292519943295;
            this.DEC = delta;
            return RA;
        }
        
        public void paint(final Graphics g) {
            g.setFont(new Font("Courier", 0, 14));
            g.drawString("Equation of Time  " + this.eqtStr + "   Declination  " + this.declinStr, 15, 260);
            final String dstr = this.dat.toString();
            g.drawString(dstr, 110, 240);
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString(String.valueOf(this.versStr) + "  -  © 1998-2008 J. Giesen  -  www.GeoAstro.de   Map © Apple Inc.", 10, 280);
            final int moonMapRadius = (int)Math.round(this.moonRadius * 0.8);
            final double xFaktor = 1.2555555555555555;
            final double yFaktor = 1.2444444444444445;
            final int x0Map = (int)Math.round(226.0);
            final int y0Map = (int)Math.round(112.0);
            this.xMoonMap = SunMoonMap117.this.xL + x0Map - (int)Math.round(1.2555555555555555 * this.moon_GSW);
            if (this.xMoonMap < SunMoonMap117.this.xL) {
                this.xMoonMap = SunMoonMap117.this.xL + x0Map - (int)Math.round(1.2555555555555555 * (this.moon_GSW - 360.0));
            }
            final int yMoonMap = y0Map - (int)Math.round(1.2444444444444445 * this.moon_Dec);
            g.setColor(Color.white);
            g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
            g.setColor(Color.black);
            g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
            int xSunMap = SunMoonMap117.this.xL + x0Map - (int)Math.round(1.2555555555555555 * this.GHA);
            if (xSunMap < SunMoonMap117.this.xL) {
                xSunMap = SunMoonMap117.this.xL + x0Map - (int)Math.round(1.2555555555555555 * (this.GHA - 360.0));
            }
            int startAngle;
            if (this.xMoonMap < xSunMap) {
                startAngle = 270;
            }
            else {
                startAngle = 90;
            }
            g.setColor(Color.black);
            double dLun = 2.0 * moonMapRadius * this.diffL / 180.0;
            if (this.diffL > 180.0) {
                dLun = 2 * moonMapRadius * (this.diffL - 180.0) / 180.0;
            }
            final double xHalbAchse = Math.abs(moonMapRadius - dLun);
            final int xHA = (int)Math.round(xHalbAchse);
            if (this.moonAge <= 7.3825) {
                g.setColor(Color.white);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, startAngle, 180);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, startAngle + 180, 180);
                g.setColor(Color.white);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            if (this.moonAge > 7.3825 && this.moonAge <= 14.765) {
                g.setColor(Color.black);
                g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.white);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, 90, 180);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, 270, 180);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            if (this.moonAge > 14.765 && this.moonAge < 22.1475) {
                g.setColor(Color.black);
                g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.white);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, 270, 180);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, 90, 180);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            if (this.moonAge > 22.1475 && this.moonAge < 29.53) {
                g.setColor(Color.white);
                g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, 90, 180);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, 270, 180);
                g.setColor(Color.white);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            g.setColor(Color.red);
            final int mapRadius = (int)Math.round(0.8 * this.Radius);
            final int ySunMap = y0Map - (int)Math.round(this.DEC * 1.2444444444444445);
            g.setColor(Color.yellow);
            g.fillOval(xSunMap - mapRadius, ySunMap - mapRadius, 2 * mapRadius, 2 * mapRadius);
            g.setColor(Color.red);
            g.drawOval(xSunMap - mapRadius, ySunMap - mapRadius, 2 * mapRadius, 2 * mapRadius);
            if (this.analemmaOK) {
                int k = 0;
                SunMoonMap117.this.xL = 0;
                for (int m = 0; m < 12; ++m) {
                    for (int d = 1; d <= this.comp.daysInMonth(m, this.year); ++d) {
                        final double decAna = this.comp.sunDecRA(1, this.comp.JD(d, m + 1, this.year + 1900, this.STD));
                        this.analemmaY[k] = y0Map - (int)Math.round(decAna * 1.2444444444444445);
                        final double ghaAna = this.comp.computeGHA(d, m + 1, this.year + 1900, this.STD);
                        int X = x0Map - (int)Math.round(1.2555555555555555 * ghaAna);
                        if (X < SunMoonMap117.this.xL) {
                            X = SunMoonMap117.this.xL + x0Map - (int)Math.round(1.2555555555555555 * (ghaAna - 360.0));
                        }
                        this.analemmaX[k] = X;
                        ++k;
                    }
                }
                g.setColor(Color.red);
                for (int i = 0; i < k - 1; ++i) {
                    g.drawLine(this.analemmaX[i], this.analemmaY[i], this.analemmaX[i + 1], this.analemmaY[i + 1]);
                }
            }
        }
    }
    
    class Compute
    {
        public double frac(double X) {
            X -= (int)X;
            if (X < 0.0) {
                ++X;
            }
            return X;
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
        
        double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
            final double K = 0.017453292519943295;
            double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
            if (tau < 0.0) {
                tau += 360.0;
            }
            final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
            return Math.asin(sinH) / 0.017453292519943295;
        }
        
        double sun_GHA(final double JD, final double RA) {
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
        
        public double computeLat(final int longitude, final double dec) {
            final double K = 0.017453292519943295;
            return Math.atan(-Math.cos(longitude * K) / Math.tan(dec * K)) / K;
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
        
        public double computeGHA(final int T, final int M, final int J, final double STD) {
            final double K = 0.017453292519943295;
            long N = 365 * J + T + 31 * M - 46;
            if (M < 3) {
                N += (J - 1) / 4;
            }
            else {
                N = N - (int)(0.4 * M + 2.3) + J / 4;
            }
            final double P = STD / 24.0;
            double X = (P + N - 722449.0) * 0.98564734 + 279.306;
            X *= K;
            double XX = -104.55 * Math.sin(X) - 429.266 * Math.cos(X) + 595.63 * Math.sin(2.0 * X) - 2.283 * Math.cos(2.0 * X);
            XX = XX + 4.6 * Math.sin(3.0 * X) + 18.7333 * Math.cos(3.0 * X);
            XX = XX - 13.2 * Math.sin(4.0 * X) - Math.cos(5.0 * X) - Math.sin(5.0 * X) / 3.0 + 0.5 * Math.sin(6.0 * X) + 0.231;
            XX = XX / 240.0 + 360.0 * (P + 0.5);
            if (XX > 360.0) {
                XX -= 360.0;
            }
            return XX;
        }
        
        public double computeDeclination(final int T, final int M, final int J, final double STD) {
            final double K = 0.017453292519943295;
            long N = 365 * J + T + 31 * M - 46;
            if (M < 3) {
                N += (J - 1) / 4;
            }
            else {
                N = N - (int)(0.4 * M + 2.3) + J / 4;
            }
            double X = (N - 693960L) / 1461.0;
            X = (X - (int)X) * 1440.02509 + (int)X * 0.0307572;
            X = X + STD / 24.0 * 0.9856645 + 356.6498973;
            X += 1.91233 * Math.sin(0.9999825 * X * 0.017453292519943295);
            X = (X + Math.sin(1.999965 * X * 0.017453292519943295) / 50.0 + 282.55462) / 360.0;
            X = (X - (int)X) * 360.0;
            final double t = (this.JD(T, M, J, STD) - 2451545.0) / 36525.0;
            final double Ekliptik = 23.43929111 - (46.815 + (5.9E-4 - 0.001813 * t) * t) * t / 3600.0;
            X = Math.sin(X * 0.017453292519943295) * Math.sin(0.017453292519943295 * Ekliptik);
            return Math.atan(X / Math.sqrt(1.0 - X * X)) / 0.017453292519943295 + 7.5E-4;
        }
        
        public double computeHeight(final double dec, final double latitude, final double longitude, final double GHA) {
            final double K = 0.017453292519943295;
            final double lat_K = latitude * 0.017453292519943295;
            final double dec_K = dec * 0.017453292519943295;
            final double sinHeight = Math.sin(dec_K) * Math.sin(lat_K) + Math.cos(dec_K) * Math.cos(lat_K) * Math.cos(0.017453292519943295 * (GHA + longitude));
            return Math.asin(sinHeight) / 0.017453292519943295;
        }
        
        public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
            final double K = 0.017453292519943295;
            final double lat_K = latitude * 0.017453292519943295;
            final double hoehe_K = hoehe * 0.017453292519943295;
            final double nenner = Math.cos(hoehe_K) * Math.cos(lat_K);
            if (Math.abs(nenner) > 1.0E-5) {
                final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(lat_K) * Math.sin(hoehe_K)) / nenner;
                double Az = 1.5707963267948966 - Math.asin(cosAz);
                Az /= 0.017453292519943295;
                if (Math.sin(0.017453292519943295 * (GHA + longitude)) <= 0.0) {
                    Az = Az;
                }
                if (Math.sin(0.017453292519943295 * (GHA + longitude)) > 0.0) {
                    Az = 360.0 - Az;
                }
                return Az;
            }
            return 0.0;
        }
    }
    
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
        
        public Moon(final Date myDate, final double myLat, final double myLong, final double myLocOffset) {
            this.iRise = 100;
            this.iSet = 100;
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
            this.locOffset = myLocOffset;
            this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
            if (this.UT < 0.0) {
                this.UT += 24.0;
                --this.date;
            }
            if (this.UT < 24.0 && this.UT + this.locOffset > 24.0) {
                this.date = this.date;
            }
            this.computeMoon(this.T = (this.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0);
            this.moon_GHA = 15.0 * (this.LMST(this.JD(this.date, this.month, this.year, this.UT) - 2400000.5, 0.0) - this.RA);
            if (this.moon_GHA < 0.0) {
                this.moon_GHA += 360.0;
            }
            if (this.moon_GHA > 360.0) {
                this.moon_GHA -= 360.0;
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
        
        public double JD(final int date, int month, int year, double STD) {
            if (STD < 0.0) {
                STD += 24.0;
            }
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
            this.GMST = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
            return 24.0 * this.frac(this.GMST / 24.0);
        }
        
        double moonDEC() {
            return this.DEC;
        }
        
        double moonGSW() {
            return this.moon_GHA;
        }
        
        double radius() {
            final double K = 0.017453292519943295;
            final double JulDat = this.JD(this.date, this.month, this.year, this.UT);
            final double T = (JulDat - 2415020.0) / 36525.0;
            double G = 358.4758333 + 35999.0 * T + (179.1 * T - 0.54 * T * T) / 3600.0;
            G *= 0.017453292519943295;
            final double R = 3.057E-5 - 1.5E-7 * T + (-0.00727412 + 1.814E-5 * T) * Math.cos(G) + (-9.138E-5 + 4.6E-7 * T) * Math.cos(2.0 * G) - 1.45E-6 * Math.cos(3.0 * G);
            return Math.exp(R * Math.log(10.0));
        }
    }
    
    class MoonDistance
    {
        int date;
        int month;
        int year;
        double JD;
        double T;
        
        public double Jul_Date(final int date, int month, int year, double UT) {
            if (UT < 0.0) {
                UT += 24.0;
            }
            double A = 10000.0 * year + 100.0 * month + date;
            if (month <= 2) {
                month += 12;
                --year;
            }
            final double B = year / 400 - year / 100 + year / 4;
            A = 365.0 * year - 679004.0;
            final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + UT / 24.0;
            return MJD + 2400000.5;
        }
        
        MoonDistance(final int date, final int month, final int year, final double UT) {
            this.date = date;
            this.month = month;
            this.year = year;
            this.JD = this.Jul_Date(date, month, year + 1900, UT);
            this.T = (this.JD - 2415020.0) / 36525.0;
            this.T = this.T;
        }
        
        double compute() {
            final double K = 0.017453292519943295;
            final double m = 296.104608 + 477000.0 * this.T + 198.849108 * this.T + 0.009192 * this.T * this.T;
            final double l = 270.434164 + 480960.0 * this.T + 307.883142 * this.T - 0.001133 * this.T * this.T;
            final double L = 279.696678 + 36000.0 * this.T + 0.768925 * this.T + 3.03E-4 * this.T * this.T;
            double P = 3423.0 + 187.0 * Math.cos(0.017453292519943295 * m) + 10.0 * Math.cos(0.03490658503988659 * m) + 34.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) - m)) + 28.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L))) + 3.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) + m));
            P /= 3600.0;
            final double r = 6378.14 / Math.sin(0.017453292519943295 * P);
            return r;
        }
    }
}
