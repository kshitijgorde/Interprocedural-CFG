import java.awt.Event;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Checkbox;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hsd095 extends Applet
{
    String versStr;
    public Seloc loc;
    public Seloc time;
    Checkbox checkBox;
    Checkbox dstBox;
    Checkbox writeBox;
    boolean demo;
    boolean online;
    boolean isDST;
    boolean write;
    double usrLat;
    double usrLong;
    public double latitude;
    public double longitude;
    double homeLat;
    double homeLong;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    public Choice locChoice;
    public Choice timeChoice;
    Choice ewChoice;
    Choice nsChoice;
    Choice radiusChoice;
    public String locString;
    public String timeString;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    String usrStr;
    String userString;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    int timezoneoffset;
    String timezoneoffsetStr;
    String separator;
    int radius;
    String radiusStr;
    int offset;
    String[] monthArray;
    char deg;
    int[] analemmaX;
    int[] analemmaY;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        num += 787L;
        return (int)num;
    }
    
    public void init() {
        final Color background = new Color(255, 255, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.timezoneoffsetStr = this.getParameter("timezoneoffset");
        this.timezoneoffset = Integer.parseInt(this.timezoneoffsetStr);
        this.radiusStr = this.getParameter("radius");
        this.radius = Integer.parseInt(this.radiusStr);
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.timeString = String.valueOf(this.timezoneoffset);
        if (this.timezoneoffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT  " + this.timeString + " h";
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 10, 0, 0);
        final Label L11 = new Label();
        L11.setText("Time Zone");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
        gbc.insets = new Insets(5, 0, 0, 0);
        final Label L12 = new Label();
        L12.setText("Location");
        gbl.setConstraints(L12, gbc);
        this.add(L12);
        gbc.weightx = 10.0;
        final Label L13 = new Label();
        L13.setText("Latitude: xx.xx");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label();
        L14.setText("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label();
        L15.setText("Longitude: xx.xx");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label();
        L16.setText("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        final Label L17 = new Label();
        L17.setText("Maj. Semiaxis (pix.)");
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        final Label L18 = new Label();
        L18.setText("Daylight Saving Time");
        gbc.insets = new Insets(5, 0, 0, 15);
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        final Label L19 = new Label("Draw Analemma");
        gbc.insets = new Insets(5, 0, 0, 15);
        gbl.setConstraints(L19, gbc);
        this.add(L19);
        gbc.gridy = 1;
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 10, 0, 0);
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem(this.separator);
        (this.loc = new Seloc()).locMenu(this.locChoice);
        gbl.setConstraints(this.locChoice, gbc);
        this.add(this.locChoice);
        this.locChoice.select(this.locString);
        final Double latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = latDouble;
        this.homeLat = this.latitude;
        if (this.latitude < 0.0) {
            this.nsStr = "S";
        }
        else {
            this.nsStr = "N";
        }
        final Double longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = longDouble;
        this.homeLong = this.longitude;
        if (this.longitude < 0.0) {
            this.ewStr = "W";
        }
        else {
            this.ewStr = "E";
        }
        this.latStr = String.valueOf(Math.abs(this.latitude));
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 4), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 5), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        this.radiusChoice = new Choice();
        for (int i = 0; i < 17; ++i) {
            this.radiusChoice.addItem(String.valueOf(100 + i * 25));
        }
        this.radiusChoice.select(this.radiusStr);
        gbl.setConstraints(this.radiusChoice, gbc);
        this.add(this.radiusChoice);
        this.dstBox = new Checkbox();
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(this.dstBox, gbc);
        this.dstBox.setState(false);
        this.add(this.dstBox);
        this.writeBox = new Checkbox();
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(this.writeBox, gbc);
        this.writeBox.setState(true);
        this.add(this.writeBox);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        this.repaint();
    }
    
    public int getOffset(final double longitude) {
        int x;
        if (longitude >= -7.5) {
            x = (int)((longitude + 7.5) / 15.0);
        }
        else {
            x = -(int)(-(longitude - 7.5) / 15.0);
        }
        return x;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
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
    
    double sunDecRA(final int what, final double JD) {
        final double PI2 = 6.283185307179586;
        final double T = (JD - 2451545.0) / 36525.0;
        final double eps = 23.43929111 - 46.815 * T / 3600.0 - 5.9E-4 * T * T / 3600.0 + 0.001813 * T * T * T / 3600.0;
        final double cos_eps = Math.cos(6.283185307179586 * eps / 360.0);
        final double sin_eps = Math.sin(6.283185307179586 * eps / 360.0);
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = cos_eps * SL;
        final double Z = sin_eps * SL;
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
    
    public int daysInMonth(int m, final int y) {
        int n = 31;
        --m;
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
    
    public void paint(final Graphics g) {
        final double K = 0.017453292519943295;
        final int leftMargin = 30;
        final int topMargin = 40;
        final int xMitte = this.size().width / 2;
        final int yMitte = topMargin + (this.size().height - topMargin) / 2;
        int b = (int)Math.round(this.radius * Math.sin(K * this.latitude));
        b = Math.abs(b);
        int b2 = (int)Math.round((this.radius - 30) * Math.sin(K * this.latitude));
        b2 = Math.abs(b2);
        g.drawLine(xMitte, yMitte + b, xMitte, yMitte - b);
        g.drawString(this.locString, xMitte - this.radius - 20, yMitte - b + 15);
        g.drawString(String.valueOf(this.latStr) + this.deg + " " + this.nsStr + "   " + this.longStr + this.deg + " " + this.ewStr, xMitte - this.radius - 20, yMitte - b + 30);
        g.drawOval(xMitte - this.radius, yMitte - b, 2 * this.radius, 2 * b);
        g.drawOval(xMitte - (int)Math.round(1.1 * this.radius), yMitte - (int)Math.round(1.1 * b), (int)Math.round(2.2 * this.radius), (int)Math.round(2.2 * b));
        g.setColor(Color.red);
        final double delta = this.timezoneoffset * 15 - this.longitude;
        g.setFont(new Font("Chicago", 0, 12));
        System.out.println(String.valueOf(this.versStr) + "  - 2003-2008 J. Giesen  www.GeoAstro.de");
        System.out.println("Coordinates of Hour Points");
        int x0 = 0;
        int y0 = 0;
        int x2 = 0;
        int y2 = 0;
        for (int i = -36; i <= 36; ++i) {
            final double winkel = i * 15 / 4.0 - delta;
            final int x3 = xMitte + (int)Math.round(this.radius * Math.sin(K * winkel));
            final int y3 = yMitte - (int)Math.round(this.radius * Math.sin(K * this.latitude) * Math.cos(K * winkel));
            if (i % 4 == 0) {
                g.fillOval(x3 - 5, y3 - 5, 10, 10);
            }
            else {
                g.drawOval(x3 - 3, y3 - 3, 6, 6);
            }
            String str;
            if (this.isDST) {
                str = String.valueOf(13 + i / 4);
            }
            else {
                str = String.valueOf(12 + i / 4);
            }
            if (i == 0) {
                x0 = x3;
                y0 = y3;
            }
            if (i == 4) {
                x2 = x3;
                y2 = y3;
            }
            if (i % 4 == 0) {
                if (i == 0) {
                    g.drawString(str, x3 - 5, y3 + 20);
                    System.out.println("12  X = " + (int)Math.round(this.radius * Math.sin(K * winkel)) + "    Y = " + (int)Math.round(this.radius * Math.sin(K * this.latitude) * Math.cos(K * winkel)));
                }
                else {
                    System.out.println(String.valueOf(str) + "  X = " + (int)Math.round(this.radius * Math.sin(K * winkel)) + "    Y = " + (int)Math.round(this.radius * Math.sin(K * this.latitude) * Math.cos(K * winkel)));
                    if (Math.abs(y3 - yMitte) < 30) {
                        g.drawString(str, x3 - i, y3);
                    }
                    else {
                        if (x3 > xMitte && y3 < yMitte) {
                            g.drawString(str, x3 - i, y3 + 20);
                        }
                        if (x3 > xMitte && y3 > yMitte) {
                            g.drawString(str, x3 - i, y3 - 10);
                        }
                    }
                    if (Math.abs(y3 - yMitte) < 30) {
                        g.drawString(str, x3 - i, y3);
                    }
                    else {
                        if (x3 < xMitte && y3 < yMitte) {
                            g.drawString(str, x3 - i, y3 + 20);
                        }
                        if (x3 < xMitte && y3 > yMitte) {
                            g.drawString(str, x3 - i, y3 - 10);
                        }
                    }
                }
            }
        }
        final double abstand = Math.sqrt((x2 - x0) * (x2 - x0) + (y2 - y0) * (y2 - y0));
        g.setFont(new Font("Helv", 0, 10));
        for (int m = 1; m <= 6; ++m) {
            for (int t = 0; t <= 3; ++t) {
                final double jd = this.JD(1 + t * 7, m, 2002, 12.0);
                final double dec = this.sunDecRA(1, jd);
                final int z = (int)Math.round(this.radius * Math.tan(K * dec) * Math.cos(K * this.latitude));
                if (t == 0) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                if (m == 6) {
                    if (t == 0) {
                        g.drawLine(xMitte, yMitte - z, xMitte + (int)Math.round(20.0 * abstand / 60.0), yMitte - z);
                    }
                }
                else {
                    g.drawLine(xMitte, yMitte - z, xMitte + (int)Math.round(20.0 * abstand / 60.0), yMitte - z);
                }
                if (t == 0) {
                    g.drawString("1 " + this.monthArray[m - 1], xMitte + (int)Math.round(20.0 * abstand / 60.0) + 5, yMitte - z + 4);
                }
            }
        }
        for (int j = 7; j <= 12; ++j) {
            for (int t2 = 0; t2 <= 3; ++t2) {
                final double jd = this.JD(1 + t2 * 7, j, 2002, 12.0);
                final double dec = this.sunDecRA(1, jd);
                final int z = (int)Math.round(this.radius * Math.tan(K * dec) * Math.cos(K * this.latitude));
                if (t2 == 0) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawLine(xMitte - (int)Math.round(20.0 * abstand / 60.0), yMitte - z, xMitte, yMitte - z);
                if (t2 == 0) {
                    g.drawString(this.monthArray[j - 1] + " 1", xMitte - (int)Math.round(20.0 * abstand / 60.0) - 30, yMitte - z + 4);
                }
            }
        }
        double dec = -23.44;
        double Z = this.radius * Math.tan(K * dec) * Math.cos(K * this.latitude);
        int z = (int)Math.round(Z);
        double cosAZ = Math.sin(K * dec) / Math.cos(K * this.latitude);
        double AZ = Math.acos(cosAZ) / K;
        double steig = Math.tan(K * (90.0 - AZ));
        double X = Z / steig;
        int x3 = (int)Math.round(X);
        g.fillOval(xMitte + x3 - 3, yMitte - 3, 6, 6);
        int dx = this.radius / 2;
        int dy = (int)Math.round(dx * steig);
        g.drawLine(xMitte + x3, yMitte, xMitte + x3 + dx, yMitte + dy);
        g.drawLine(xMitte - x3, yMitte, xMitte - x3 - dx, yMitte + dy);
        dec = 23.44;
        Z = this.radius * Math.tan(K * dec) * Math.cos(K * this.latitude);
        z = (int)Math.round(Z);
        cosAZ = Math.sin(K * dec) / Math.cos(K * this.latitude);
        AZ = Math.acos(cosAZ) / K;
        steig = Math.tan(K * (90.0 - AZ));
        X = Z / steig;
        x3 = (int)Math.round(X);
        g.fillOval(xMitte - x3 - 3, yMitte - 3, 6, 6);
        dx = this.radius / 2;
        dy = (int)Math.round(dx * steig);
        g.drawLine(xMitte + x3, yMitte, xMitte + x3 + dx, yMitte + dy);
        g.drawLine(xMitte - x3, yMitte, xMitte - x3 - dx, yMitte + dy);
        g.drawLine(xMitte - x3, yMitte, xMitte - this.radius, yMitte);
        g.drawLine(xMitte + x3, yMitte, xMitte + this.radius, yMitte);
        final EOT myEOT = new EOT();
        String tStr = "";
        System.out.println("Equation of Time and Date Points");
        int index = 0;
        for (int k = 1; k <= 12; ++k) {
            for (int t3 = 1; t3 <= this.daysInMonth(k, 2002); ++t3) {
                final String str = myEOT.eot(t3, k, 2002, 12 + this.timezoneoffset);
                final double eqt = myEOT.value();
                if (t3 < 10) {
                    tStr = "0";
                }
                else {
                    tStr = "";
                }
                final double jd = this.JD(t3, k, 2002, 12 + this.timezoneoffset);
                dec = this.sunDecRA(1, jd);
                ++index;
                z = (int)Math.round(this.radius * Math.tan(K * dec) * Math.cos(K * this.latitude));
                this.analemmaX[index] = xMitte - (int)Math.round(eqt * abstand / 60.0);
                this.analemmaY[index] = yMitte - z;
                if (k < 10) {
                    System.out.println(" " + k + "/" + tStr + t3 + "  eot= " + str + "  Y = " + z);
                }
                else {
                    System.out.println(String.valueOf(k) + "/" + tStr + t3 + "  eot= " + str + "  Y = " + z);
                }
            }
        }
        g.setColor(Color.blue);
        if (this.write) {
            int Y1 = 0;
            int Y2 = 0;
            if (this.latitude >= 0.0) {
                Y1 = this.analemmaY[1] + 16;
                Y2 = this.analemmaY[1] + 24;
            }
            else {
                Y1 = this.analemmaY[182] - 20;
                Y2 = this.analemmaY[182] - 30;
            }
            for (int l = 2; l <= index; ++l) {
                g.drawLine(this.analemmaX[l - 1], this.analemmaY[l - 1], this.analemmaX[l], this.analemmaY[l]);
            }
            g.drawLine(xMitte - (int)Math.round(20.0 * abstand / 60.0), (Y1 + Y2) / 2, xMitte + (int)Math.round(20.0 * abstand / 60.0), (Y1 + Y2) / 2);
            g.drawLine(xMitte - (int)Math.round(5.0 * abstand / 60.0), Y1, xMitte - (int)Math.round(5.0 * abstand / 60.0), Y2);
            g.drawLine(xMitte - (int)Math.round(10.0 * abstand / 60.0), Y1, xMitte - (int)Math.round(10.0 * abstand / 60.0), Y2);
            g.drawLine(xMitte - (int)Math.round(15.0 * abstand / 60.0), Y1, xMitte - (int)Math.round(15.0 * abstand / 60.0), Y2);
            g.drawLine(xMitte + (int)Math.round(5.0 * abstand / 60.0), Y1, xMitte + (int)Math.round(5.0 * abstand / 60.0), Y2);
            g.drawLine(xMitte + (int)Math.round(10.0 * abstand / 60.0), Y1, xMitte + (int)Math.round(10.0 * abstand / 60.0), Y2);
            g.drawLine(xMitte + (int)Math.round(15.0 * abstand / 60.0), Y1, xMitte + (int)Math.round(15.0 * abstand / 60.0), Y2);
            g.drawLine(xMitte, Y1, xMitte, Y2);
            g.setFont(new Font("Helv", 0, 8));
            g.drawString("15", xMitte - (int)Math.round(15.0 * abstand / 60.0) - 5, Y2 + 20);
            g.drawString("15", xMitte + (int)Math.round(15.0 * abstand / 60.0) - 5, Y2 + 20);
            g.drawString("0", xMitte - 2, Y2 + 20);
        }
        g.clearRect(0, 0, this.size().width, topMargin + 10);
        g.setFont(new Font("Courier", 0, 10));
        final int links = 100;
        g.setColor(Color.black);
        g.clearRect(0, this.size().height - 20, this.size().width, 20);
        String str = "  -  Credits: Chris Sangwin, Chris Budd";
        g.drawString(String.valueOf(this.versStr) + "  -  © 2003-2008 J. Giesen  -  www.GeoAstro.de" + str, 20, this.size().height - 5);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        if (this.demo) {
            this.usrStr = "demo running";
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 100, 200);
            g.setFont(new Font("Chicago", 0, 36));
            g.drawString("Location Menu disabled", 50, 300);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof TextField) {
            if (this.demo) {
                return true;
            }
            this.str = "";
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLatDeg.setText(String.valueOf(Math.abs(oldLat)));
                        return true;
                    }
                }
                if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double latDouble = Double.valueOf(this.str);
                    this.usrLat = latDouble;
                }
                this.latStr = this.str;
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                this.fieldLatDeg.setText(this.str);
                if (Math.abs(this.latitude) > 90.0) {
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0));
                    this.fieldLatDeg.setText(this.latStr);
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.fieldLatDeg.nextFocus();
                this.fieldLongDeg.selectAll();
                this.repaint();
                return true;
            }
            if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(oldLong)));
                        return true;
                    }
                }
                if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double longDouble = Double.valueOf(this.str);
                    this.usrLong = longDouble;
                }
                this.longStr = this.str;
                final Double longDouble = Double.valueOf(this.str);
                this.longitude = longDouble;
                if (this.ewChoice.getSelectedItem().equals("W")) {
                    this.longitude = -Math.abs(this.longitude);
                    this.usrLong = this.longitude;
                }
                this.fieldLongDeg.setText(this.longStr);
                if (Math.abs(this.longitude) >= 180.0) {
                    this.longitude = oldLong;
                    this.longStr = String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0));
                    this.fieldLongDeg.setText(this.longStr);
                }
                else {
                    if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                        this.longStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Checkbox) {
            if (event.target == this.dstBox) {
                if (this.isDST) {
                    this.isDST = false;
                }
                else {
                    this.isDST = true;
                }
                this.dstBox.setState(this.isDST);
                this.repaint();
                return true;
            }
            if (event.target == this.writeBox) {
                if (this.write) {
                    this.write = false;
                }
                else {
                    this.write = true;
                }
                this.writeBox.setState(this.write);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.radiusChoice) {
                if (this.radiusChoice.getSelectedItem().equals("100")) {
                    this.radius = 100;
                }
                if (this.radiusChoice.getSelectedItem().equals("125")) {
                    this.radius = 125;
                }
                if (this.radiusChoice.getSelectedItem().equals("150")) {
                    this.radius = 150;
                }
                if (this.radiusChoice.getSelectedItem().equals("175")) {
                    this.radius = 175;
                }
                if (this.radiusChoice.getSelectedItem().equals("200")) {
                    this.radius = 200;
                }
                if (this.radiusChoice.getSelectedItem().equals("225")) {
                    this.radius = 225;
                }
                if (this.radiusChoice.getSelectedItem().equals("250")) {
                    this.radius = 250;
                }
                if (this.radiusChoice.getSelectedItem().equals("275")) {
                    this.radius = 275;
                }
                if (this.radiusChoice.getSelectedItem().equals("300")) {
                    this.radius = 300;
                }
                if (this.radiusChoice.getSelectedItem().equals("325")) {
                    this.radius = 325;
                }
                if (this.radiusChoice.getSelectedItem().equals("350")) {
                    this.radius = 350;
                }
                if (this.radiusChoice.getSelectedItem().equals("375")) {
                    this.radius = 375;
                }
                if (this.radiusChoice.getSelectedItem().equals("400")) {
                    this.radius = 400;
                }
                if (this.radiusChoice.getSelectedItem().equals("425")) {
                    this.radius = 425;
                }
                if (this.radiusChoice.getSelectedItem().equals("450")) {
                    this.radius = 450;
                }
                if (this.radiusChoice.getSelectedItem().equals("475")) {
                    this.radius = 475;
                }
                if (this.radiusChoice.getSelectedItem().equals("500")) {
                    this.radius = 500;
                }
                this.repaint();
            }
            if (event.target == this.locChoice) {
                if (this.demo) {
                    return true;
                }
                if (this.locChoice.getSelectedItem().equals(this.separator)) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                if (this.locChoice.getSelectedItem().equals("Home")) {
                    this.locString = this.homeString;
                    this.locChoice.select(this.locString);
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                }
                this.locString = this.locChoice.getSelectedItem();
                if (this.locChoice.getSelectedItem().equals(this.homeString)) {
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                    this.offset = Integer.parseInt(this.timezoneoffsetStr);
                    this.timezoneoffset = this.offset;
                    if (this.offset > 0) {
                        this.str = "UT  +" + this.offset + " h";
                    }
                    if (this.offset < 0) {
                        this.str = "UT  " + this.offset + " h";
                    }
                    if (this.offset == 0) {
                        this.str = "UT  +/- 0 h";
                    }
                    this.timeChoice.select(this.str);
                }
                else {
                    this.latitude = this.loc.getLatLong(this.locString, 1);
                    this.longitude = this.loc.getLatLong(this.locString, 2);
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    this.timezoneoffset = this.offset;
                    if (this.offset > 0) {
                        this.str = "UT  +" + this.offset + " h";
                    }
                    if (this.offset < 0) {
                        this.str = "UT  " + this.offset + " h";
                    }
                    if (this.offset == 0) {
                        this.str = "UT  +/- 0 h";
                    }
                    this.timeChoice.select(this.str);
                }
                if (this.locChoice.getSelectedItem().equals("User Input")) {
                    this.latitude = this.usrLat;
                    this.longitude = this.usrLong;
                }
                this.latStr = String.valueOf(Math.abs(this.latitude));
                this.fieldLatDeg.setText(this.latStr);
                this.longStr = String.valueOf(Math.abs(this.longitude));
                this.fieldLongDeg.setText(this.longStr);
                if (this.longitude <= 0.0) {
                    this.ewChoice.select("W");
                }
                else {
                    this.ewChoice.select("E");
                }
                if (this.latitude <= 0.0) {
                    this.nsChoice.select("S");
                    this.nsStr = "S";
                }
                else {
                    this.nsChoice.select("N");
                    this.nsStr = "N";
                }
                if (this.locString != "User Input") {
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                }
                this.repaint();
                return true;
            }
            else {
                if (event.target == this.ewChoice) {
                    if (!this.ewChoice.getSelectedItem().equals(this.ewStr) || !this.fieldLongDeg.getText().equals(this.longStr)) {
                        this.locChoice.select("User Input");
                        this.locString = "User Input";
                    }
                    this.ewStr = this.ewChoice.getSelectedItem();
                    if (this.longStr.length() > 0) {
                        final Double longDouble = Double.valueOf(this.longStr);
                        this.longitude = longDouble;
                    }
                    if (this.ewChoice.getSelectedItem().equals("W")) {
                        this.ewStr = "W";
                        this.longitude = -this.longitude;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.repaint();
                    return true;
                }
                if (event.target == this.nsChoice) {
                    if (!this.nsChoice.getSelectedItem().equals(this.nsStr) || !this.fieldLatDeg.getText().equals(this.latStr)) {
                        this.locChoice.select("User Input");
                        this.locString = "User Input";
                    }
                    this.nsStr = this.nsChoice.getSelectedItem();
                    if (this.latStr.length() > 0) {
                        final Double latDouble = Double.valueOf(this.latStr);
                        this.latitude = latDouble;
                    }
                    if (this.nsChoice.getSelectedItem().equals("S")) {
                        this.nsStr = "S";
                        this.latitude = -this.latitude;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.timezoneoffset = this.loc.getTimeZone(this.timeString);
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
                }
            }
        }
        return true;
    }
    
    public hsd095() {
        this.versStr = "Horizontal Sundial Applet 0.95";
        this.demo = true;
        this.online = false;
        this.isDST = false;
        this.write = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.usrStr = "demo";
        this.userString = "?";
        this.separator = "---------------";
        this.radius = 300;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.deg = '°';
        this.analemmaX = new int[366];
        this.analemmaY = new int[366];
    }
}
